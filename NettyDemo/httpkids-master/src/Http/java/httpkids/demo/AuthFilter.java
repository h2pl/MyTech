package httpkids.demo;

import httpkids.server.IRequestFilter;
import httpkids.server.KidsContext;
import httpkids.server.KidsRequest;

public class AuthFilter implements IRequestFilter {

	private MemorySession session;

	public AuthFilter(MemorySession session) {
		this.session = session;
	}

	@Override
	public boolean filter(KidsContext ctx, KidsRequest req, boolean beforeOrAfter) {
		if (!beforeOrAfter) {
			return true;
		}
		String sid = req.cookie("kids_sid");
		if (sid != null) {
			var user = session.getUser(sid);
			if (user != null) {
				req.attr("user", user);
				return true;
			}
		}

		ctx.redirect("/security/login");
		return false;
	}

}
