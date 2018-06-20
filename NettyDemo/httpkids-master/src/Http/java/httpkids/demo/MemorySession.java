package httpkids.demo;

import java.util.HashMap;
import java.util.Map;

public class MemorySession {

	private Map<String, User> users = new HashMap<>();

	public User getUser(String sid) {
		return users.get(sid);
	}

	public void setUser(String sid, User user) {
		this.users.put(sid, user);
	}

}
