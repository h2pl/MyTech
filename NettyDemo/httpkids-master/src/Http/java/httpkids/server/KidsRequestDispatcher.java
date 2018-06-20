package httpkids.server;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import httpkids.server.internal.IRequestDispatcher;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;

public class KidsRequestDispatcher implements IRequestDispatcher {
	private final static Logger LOG = LoggerFactory.getLogger(KidsRequestDispatcher.class);

	private String contextRoot;
	private Router router;
	private Map<Integer, IExceptionHandler> exceptionHandlers = new HashMap<>();
	private IExceptionHandler defaultExceptionHandler = new DefaultExceptionHandler();

	private ITemplateEngine templateEngine = new ITemplateEngine() {
	};

	static class DefaultExceptionHandler implements IExceptionHandler {

		@Override
		public void handle(KidsContext ctx, AbortException e) {
			if (e.getStatus().code() == 500) {
				LOG.error("Internal Server Error", e);
			}
			ctx.error(e.getContent(), e.getStatus().code());
		}

	}

	public KidsRequestDispatcher(Router router) {
		this("/", router);
	}

	public KidsRequestDispatcher(String contextRoot, Router router) {
		this.contextRoot = KidsUtils.normalize(contextRoot);
		this.router = router;
	}

	public KidsRequestDispatcher templateRoot(String templateRoot) {
		this.templateEngine = new FreemarkerEngine(templateRoot);
		return this;
	}

	public String root() {
		return contextRoot;
	}

	public KidsRequestDispatcher exception(int code, IExceptionHandler handler) {
		this.exceptionHandlers.put(code, handler);
		return this;
	}

	public KidsRequestDispatcher exception(IExceptionHandler handler) {
		this.defaultExceptionHandler = handler;
		return this;
	}

	public void dispatch(ChannelHandlerContext channelCtx, FullHttpRequest req) {
		var ctx = new KidsContext(channelCtx, contextRoot, templateEngine);
		try {
			this.handleImpl(ctx, new KidsRequest(req));
		} catch (AbortException e) {
			this.handleException(ctx, e);
		} catch (Exception e) {
			this.handleException(ctx, new AbortException(HttpResponseStatus.INTERNAL_SERVER_ERROR, e));
		} finally {
			req.release();
		}
	}

	private void handleException(KidsContext ctx, AbortException e) {
		var handler = this.exceptionHandlers.getOrDefault(e.getStatus().code(), defaultExceptionHandler);
		try {
			handler.handle(ctx, e);
		} catch (Exception ex) {
			this.defaultExceptionHandler.handle(ctx, new AbortException(HttpResponseStatus.INTERNAL_SERVER_ERROR, ex));
		}
	}

	private void handleImpl(KidsContext ctx, KidsRequest req) throws Exception {
		if (req.decoderResult().isFailure()) {
			ctx.abort(400, "http protocol decode failed");
		}
		if (req.relativeUri().contains("./") || req.relativeUri().contains(".\\")) {
			ctx.abort(400, "unsecure url not allowed");
		}
		if (!req.relativeUri().startsWith(contextRoot)) {
			throw new AbortException(HttpResponseStatus.NOT_FOUND);
		}
		req.popRootUri(contextRoot);
		router.handle(ctx, req);
	}

}
