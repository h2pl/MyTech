package httpkids.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData.HttpDataType;
import io.netty.handler.codec.http.multipart.MixedAttribute;
import io.netty.util.AsciiString;

public class KidsRequest {

	private String relativeUri;
	private FullHttpRequest req;
	private Map<String, List<String>> headers;
	private Set<Cookie> cookies;
	private Map<String, List<String>> forms;
	private Map<String, MixedAttribute> files;
	private QueryStringDecoder urlDecoder;
	private HttpPostRequestDecoder bodyDecoder;

	private Map<String, Object> attributes;
	private List<IRequestFilter> filters;

	public KidsRequest(FullHttpRequest req) {
		this.req = req;
		this.attributes = new HashMap<>();
		this.filters = new ArrayList<>();
		this.relativeUri = KidsUtils.normalize(path());
	}

	public String relativeUri() {
		return this.relativeUri;
	}

	public String peekUriPrefix() {
		if (this.relativeUri.equals("/")) {
			return "/";
		}
		int idx = 1;
		while (idx < this.relativeUri.length()) {
			if (this.relativeUri.charAt(idx) == '/') {
				break;
			}
			idx++;
		}
		return this.relativeUri.substring(0, idx);
	}

	public void popUriPrefix() {
		if (this.relativeUri.equals("/")) {
			return;
		}
		int idx = 1;
		while (idx < this.relativeUri.length()) {
			if (this.relativeUri.charAt(idx) == '/') {
				break;
			}
			idx++;
		}
		if (idx == this.relativeUri.length()) {
			this.relativeUri = "/";
		} else {
			this.relativeUri = this.relativeUri.substring(idx);
		}
	}

	public void popRootUri(String root) {
		if (root.equals("/")) {
			return;
		}
		if (this.relativeUri.equals(root)) {
			this.relativeUri = "/";
			return;
		}
		this.relativeUri = this.relativeUri.substring(root.length());
	}

	public String method() {
		return req.method().name();
	}

	public String uri() {
		return req.uri();
	}

	public String protocolVersion() {
		return req.protocolVersion().protocolName();
	}

	public ByteBuf content() {
		return req.content();
	}

	public FullHttpRequest req() {
		return req;
	}

	public DecoderResult decoderResult() {
		return req.decoderResult();
	}

	public String header(String name) {
		var values = headers(name);
		return values.isEmpty() ? null : values.get(0);
	}

	public String header(AsciiString name) {
		return header(name.toString());
	}

	public List<String> headers(String name) {
		var values = allHeaders().get(name.toLowerCase());
		return values == null ? Collections.emptyList() : values;
	}

	public Map<String, List<String>> allHeaders() {
		if (headers == null) {
			headers = new HashMap<>();
			req.headers().forEach(entry -> {
				headers.put(entry.getKey().toLowerCase(), req.headers().getAll(entry.getKey()));
			});
		}
		return headers;
	}

	public Set<Cookie> cookies() {
		if (cookies == null) {
			String cookie = req.headers().get(HttpHeaderNames.COOKIE);
			if (cookie == null) {
				cookies = Collections.emptySet();
			} else {
				cookies = ServerCookieDecoder.LAX.decode(cookie);
			}
		}
		return cookies;
	}

	public String cookie(String name) {
		for (Cookie cookie : cookies()) {
			if (cookie.name().equalsIgnoreCase(name)) {
				return cookie.value();
			}
		}
		return null;
	}

	private QueryStringDecoder urlDecoder() {
		if (urlDecoder == null) {
			urlDecoder = new QueryStringDecoder(req.uri());
		}
		return urlDecoder;
	}

	public Map<String, List<String>> allParams() {
		return urlDecoder().parameters();
	}

	public List<String> params(String name) {
		var values = allParams().get(name);
		if (values == null) {
			values = Collections.emptyList();
		}
		return values;
	}

	public String param(String name) {
		var all = params(name);
		return all.isEmpty() ? null : all.get(0);
	}

	public String path() {
		return urlDecoder().path();
	}

	public HttpPostRequestDecoder bodyDecoder() {
		if (this.bodyDecoder == null) {
			this.bodyDecoder = new HttpPostRequestDecoder(req);
		}
		return this.bodyDecoder;
	}

	public boolean multipart() {
		return bodyDecoder().isMultipart();
	}

	public Map<String, List<String>> allForms() {
		if (forms == null) {
			forms = new HashMap<>();
			var datas = bodyDecoder().getBodyHttpDatas();
			datas.forEach(data -> {
				var attr = (MixedAttribute) data;
				if (attr.getHttpDataType() == HttpDataType.Attribute
						|| attr.getHttpDataType() == HttpDataType.InternalAttribute) {
					var values = forms.get(attr.getName());
					if (values == null) {
						values = new ArrayList<>(1);
						forms.put(attr.getName(), values);
					}
					try {
						values.add(attr.getValue());
					} catch (IOException e) {
					}
				}
			});
		}
		return forms;
	}

	public List<String> forms(String name) {
		var values = allForms().get(name);
		if (values == null) {
			values = Collections.emptyList();
		}
		return values;
	}

	public String form(String name) {
		var values = forms(name);
		if (values.isEmpty()) {
			return null;
		}
		return values.get(0);
	}

	public String mixedParam(String name) {
		var value = this.param(name);
		if (value == null) {
			value = this.form(name);
		}
		return value;
	}

	public List<String> mixedParams(String name) {
		var mixed = new ArrayList<String>();
		var params = this.params(name);
		var forms = this.forms(name);
		mixed.addAll(params);
		mixed.addAll(forms);
		return mixed;
	}

	public Map<String, MixedAttribute> allFiles() {
		if (files == null) {
			files = new HashMap<>();
			var datas = bodyDecoder().getBodyHttpDatas();
			datas.forEach(data -> {
				var attr = (MixedAttribute) data;
				if (attr.getHttpDataType() == HttpDataType.FileUpload) {
					files.put(attr.getName(), attr);
				}
			});
		}
		return files;
	}

	public MixedAttribute file(String name) {
		return allFiles().get(name);
	}

	public <T> T jsonForm(Class<T> clazz) {
		byte[] content = new byte[req.content().readableBytes()];
		req.content().readBytes(content);
		return JSON.parseObject(content, clazz);
	}

	public <T> List<T> jsonArrayForm(Class<T> clazz) {
		byte[] content = new byte[req.content().readableBytes()];
		req.content().readBytes(content);
		return JSON.parseArray(new String(content, KidsUtils.UTF8), clazz);
	}

	public String dataForm() {
		byte[] content = new byte[req.content().readableBytes()];
		req.content().readBytes(content);
		return new String(content, KidsUtils.UTF8);
	}

	@SuppressWarnings("unchecked")
	public <T> T attr(String name) {
		return (T) (attributes.get(name));
	}

	public KidsRequest attr(String name, Object value) {
		attributes.put(name, value);
		return this;
	}

	public KidsRequest filter(IRequestFilter filter) {
		this.filters.add(filter);
		return this;
	}

	public List<IRequestFilter> filters() {
		return this.filters;
	}
}
