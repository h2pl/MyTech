package httpkids.server;

@FunctionalInterface
public interface IExceptionHandler {

	public void handle(KidsContext ctx, AbortException e);

}
