package httpkids.server;

@FunctionalInterface
public interface IRequestHandler {

	public void handle(KidsContext ctx, KidsRequest req);

}
