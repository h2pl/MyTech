package httpkids.demo;

import java.util.HashMap;

import httpkids.server.IRouteable;
import httpkids.server.KidsContext;
import httpkids.server.KidsRequest;
import httpkids.server.Router;

public class UserHandler implements IRouteable {

	public void home(KidsContext ctx, KidsRequest req) {
		User user = req.attr("user");
		user.incrCounter();
		var params = new HashMap<String, Object>();
		params.put("user", req.attr("user"));
		ctx.render("home.ftl", params);
	}

	public void getCounter(KidsContext ctx, KidsRequest req) {
		var res = new HashMap<String, Object>();
		User user = req.attr("user");
		res.put("counter", user.getCounter());
		ctx.json(res);
	}

	@Override
	public Router route() {
		Router router = new Router();
		router.handler("/", "GET", this::home);
		router.handler("/counter.json", "GET", this::getCounter);
		return router;
	}

}
