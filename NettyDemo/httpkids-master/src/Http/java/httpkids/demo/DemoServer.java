package httpkids.demo;

import httpkids.server.KidsRequestDispatcher;
import httpkids.server.Router;
import httpkids.server.internal.HttpServer;

public class DemoServer {

	public static void main(String[] args) {
		UserDB db = new UserDB();
		MemorySession session = new MemorySession();

		var router = new Router((ctx, req) -> {
			ctx.redirect("/user");
		});
		router.resource("/pub", "/static");
		router.child("/security", new SecurityHandler(db, session));
		router.child("/play", new PlayHandler());
		router.child("/user", new UserHandler().route().filter(new AuthFilter(session)));

		var rd = new KidsRequestDispatcher("/kids", router);
		rd.templateRoot("/tpl");

		var server = new HttpServer("localhost", 8080, 2, 16, rd);
		server.start();

		Runtime.getRuntime().addShutdownHook(new Thread() {

			public void run() {
				server.stop();
			}

		});
	}

}
