package httpkids.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import org.apache.tika.Tika;

import io.netty.buffer.ByteBuf;
import io.netty.channel.DefaultFileRegion;
import io.netty.handler.codec.DateFormatter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;

public class StaticRequestHandler implements IRequestHandler {

	private String staticRoot;
	private boolean classpath;

	private final static int DEFAULT_CACHE_EXPIRE = 3600;

	private Tika tika = new Tika();
	private int cacheDuration = DEFAULT_CACHE_EXPIRE;

	public StaticRequestHandler() {
		this("/");
	}

	public StaticRequestHandler(String staticRoot) {
		this(staticRoot, true, DEFAULT_CACHE_EXPIRE);
	}

	public StaticRequestHandler(String staticRoot, boolean classpath) {
		this(staticRoot, classpath, DEFAULT_CACHE_EXPIRE);
	}

	public StaticRequestHandler(String staticRoot, boolean classpath, int cacheDuration) {
		this.classpath = classpath;
		if (classpath) {
			// 使用classpath里面的资源
			this.staticRoot = KidsUtils.normalize(staticRoot);
		} else {
			// 使用文件系统的资源
			this.staticRoot = staticRoot;
			if (!Files.isDirectory(Paths.get(staticRoot))) {
				throw new IllegalArgumentException("static root must be valid directory");
			}
		}
		this.cacheDuration = cacheDuration;
	}

	@Override
	public void handle(KidsContext ctx, KidsRequest req) {
		var path = req.relativeUri();
		path = Paths.get(this.staticRoot, path).toString();
		if (classpath) {
			sendResource(ctx, path);
		} else {
			sendFile(ctx, path, req.header(HttpHeaderNames.IF_MODIFIED_SINCE));
		}
	}

	public void sendResource(KidsContext ctx, String path) {
		InputStream is = null;
		byte[] bytes = null;
		try {
			is = this.getClass().getResourceAsStream(path);
			if (is == null) {
				throw new AbortException(HttpResponseStatus.NOT_FOUND);
			}
			bytes = is.readAllBytes();
		} catch (IOException e) {
			throw new AbortException(HttpResponseStatus.INTERNAL_SERVER_ERROR, "read file error");
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}

		HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
		response.headers().add(HttpHeaderNames.CONTENT_LENGTH, bytes.length);

		String contentType = tika.detect(bytes);
		if (contentType.equals("text/plain")) {
			contentType = tika.detect(path);
		}
		response.headers().add(HttpHeaderNames.CONTENT_TYPE, contentType);

		ByteBuf buf = ctx.alloc().buffer();
		buf.writeBytes(bytes);
		ctx.send(response, new DefaultHttpContent(buf), LastHttpContent.EMPTY_LAST_CONTENT);
	}

	public void sendFile(KidsContext ctx, String path, String ifModifiedSince) {
		var file = new File(path);
		if (!file.exists()) {
			throw new AbortException(HttpResponseStatus.NOT_FOUND);
		}
		if (!file.isFile()) {
			throw new AbortException(HttpResponseStatus.FORBIDDEN, "not a valid file");
		}
		if (!file.canRead()) {
			throw new AbortException(HttpResponseStatus.FORBIDDEN, "file not allowed to read");
		}
		long lastModified = file.lastModified() / 1000;

		long ifModifiedSinceTs = 0;
		if (ifModifiedSince != null) {
			ifModifiedSinceTs = DateFormatter.parseHttpDate(ifModifiedSince).getTime() / 1000;
		}

		if (ifModifiedSinceTs >= lastModified) {
			DefaultFullHttpResponse notModified = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
					HttpResponseStatus.NOT_MODIFIED);
			notModified.headers().add(HttpHeaderNames.DATE, DateFormatter.format(new Date()));
			ctx.send(notModified);
			return;
		}

		HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
		response.headers().add(HttpHeaderNames.CONTENT_LENGTH, file.length());

		try {
			String contentType = tika.detect(file);
			if (contentType.equals("text/plain")) {
				contentType = tika.detect(file.getName());
			}
			response.headers().add(HttpHeaderNames.CONTENT_TYPE, contentType);
		} catch (IOException e) {
			throw new AbortException(HttpResponseStatus.INTERNAL_SERVER_ERROR, "detect file type error");
		}

		Calendar cald = Calendar.getInstance();
		cald.add(Calendar.SECOND, cacheDuration);
		response.headers().add(HttpHeaderNames.EXPIRES, DateFormatter.format(cald.getTime()));
		response.headers().add(HttpHeaderNames.CACHE_CONTROL, "private, max-age=" + cacheDuration);
		response.headers().add(HttpHeaderNames.LAST_MODIFIED, DateFormatter.format(new Date(file.lastModified())));

		ctx.send(response, new DefaultFileRegion(file, 0, file.length()), LastHttpContent.EMPTY_LAST_CONTENT);
	}

}
