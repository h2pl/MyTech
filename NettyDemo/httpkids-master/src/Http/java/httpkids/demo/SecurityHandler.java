package httpkids.demo;

import java.util.UUID;

import httpkids.server.IRouteable;
import httpkids.server.KidsContext;
import httpkids.server.KidsRequest;
import httpkids.server.Router;

public class SecurityHandler implements IRouteable {

	private UserDB db;
	private MemorySession session;

	public SecurityHandler(UserDB db, MemorySession session) {
		this.db = db;
		this.session = session;
	}

	public void getLoginPage(KidsContext ctx, KidsRequest req) {
		ctx.render("login.ftl");
	}

	public void login(KidsContext ctx, KidsRequest req) {
		String name = req.mixedParam("name");
		String passwd = req.mixedParam("passwd");
		if (!db.checkAccess(name, passwd)) {
			ctx.abort(401, "用户名密码错误");
		}
		User user = new User(name);
		String sid = UUID.randomUUID().toString();
		session.setUser(sid, user);
		ctx.addCookie("kids_sid", sid);
		ctx.redirect("/user");
	}

	@Override
	public Router route() {
		Router router = new Router();
		router.handler("/login", "GET", this::getLoginPage);
		router.handler("/login", "POST", this::login);
		return router;
	}

}
