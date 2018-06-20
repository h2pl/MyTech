package httpkids.server;

import java.nio.charset.Charset;

public class KidsUtils {

	public static Charset UTF8 = Charset.forName("utf8");

	public static String normalize(String uri) {
		// "/" => "/"
		// "" => "/"
		// "/a/b/" => "/a/b"
		// "a/b/" => "/a/b"
		uri = uri.trim();
		// 删除后缀的/
		while (uri.endsWith("/")) {
			uri = uri.substring(0, uri.length() - 1);
		}
		// 删除前缀的/
		while (uri.startsWith("/")) {
			uri = uri.substring(1);
		}
		// 前缀补充/
		if (!uri.startsWith("/")) {
			uri = "/" + uri;
		}
		return uri;
	}
}
