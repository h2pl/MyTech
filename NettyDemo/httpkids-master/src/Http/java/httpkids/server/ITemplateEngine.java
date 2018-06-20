package httpkids.server;

import java.util.Map;

import io.netty.handler.codec.http.HttpResponseStatus;

public interface ITemplateEngine {

	public default String render(String path, Map<String, Object> context) {
		throw new AbortException(HttpResponseStatus.INTERNAL_SERVER_ERROR, "template root not provided");
	}

}
