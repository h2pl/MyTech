package httpkids.server;

import io.netty.handler.codec.http.HttpResponseStatus;

public class AbortException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private HttpResponseStatus status;
	private String content;

	public AbortException(int statusCode) {
		this(HttpResponseStatus.valueOf(statusCode));
	}

	public AbortException(HttpResponseStatus status) {
		this(status, null, null);
	}

	public AbortException(HttpResponseStatus status, Throwable t) {
		this(status, null, null);
	}

	public AbortException(HttpResponseStatus status, String content) {
		this(status, content, null);
	}

	public AbortException(HttpResponseStatus status, String content, Throwable t) {
		super(t);
		this.status = status;
		this.content = status.reasonPhrase();
		if (content != null) {
			this.content = content;
		}
	}

	public HttpResponseStatus getStatus() {
		return status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
