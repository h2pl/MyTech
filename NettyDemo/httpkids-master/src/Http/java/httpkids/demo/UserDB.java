package httpkids.demo;

import java.util.HashMap;
import java.util.Map;

public class UserDB {

	private Map<String, String> rows = new HashMap<>();
	{
		rows.put("Linus", "linux");
		rows.put("Neumann", "math");
		rows.put("Turing", "gay");
	}

	public boolean checkAccess(String name, String passwd) {
		return rows.containsKey(name) && rows.get(name).equals(passwd);
	}

}
