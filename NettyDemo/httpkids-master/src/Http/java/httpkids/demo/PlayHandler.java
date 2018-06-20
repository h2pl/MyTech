package httpkids.demo;

import java.util.HashMap;

import httpkids.server.IRouteable;
import httpkids.server.KidsContext;
import httpkids.server.KidsRequest;
import httpkids.server.Router;

public class PlayHandler implements IRouteable {

	public void play(KidsContext ctx, KidsRequest req) {
		var params = new HashMap<String, Object>();
		params.put("req", req);
		ctx.render("playground.ftl", params);
	}

	@Override
	public Router route() {
		Router router = new Router();
		router.handler("/", this::play);
		return router;
	}

}
