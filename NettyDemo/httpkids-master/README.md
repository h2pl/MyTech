httpkids
--
Web framework based on netty for kids, java10 required!

Features & Disadvantages
--
1. Extremely lightweight compared with other web frameworks.
2. Extremely easy to use, Extermly low cost for learning.
3. Extremely easy to understand, With approximately 1200 lines of code.
4. No support for https yet.
5. No support for URL with restful style.

HelloWorld
--
```java
import httpkids.server.KidsRequestDispatcher;
import httpkids.server.Router;
import httpkids.server.internal.HttpServer;

public class HelloWorld {

    public static void main(String[] args) {
        var rd = new KidsRequestDispatcher("/kids", new Router((ctx, req) -> {
            ctx.html("Hello, World");
        }));
        new HttpServer("localhost", 8080, 2, 16, rd).start();
    }

}

http://localhost:8080/kids
```


FullStack
--
```java
import java.util.HashMap;

import httpkids.server.KidsRequestDispatcher;
import httpkids.server.Router;
import httpkids.server.internal.HttpServer;

public class HelloWorld {

	public static void main(String[] args) {
		var router = new Router((ctx, req) -> {
			ctx.html("Hello, World");  // 纯文本html
		})
		.handler("/hello.json", (ctx, req) -> {
			ctx.json(new String[] { "Hello", "World" });  // JSON API
		})
		.handler("/hello", (ctx, req) -> {
			var res = new HashMap<String, Object>();
			res.put("req", req);
			ctx.render("playground.ftl", res); // 模版渲染
		})
		.handler("/world", (ctx, req) -> {
			ctx.redirect("/hello");  // 302跳转
		})
		.handler("/error", (ctx, req) -> {
			ctx.abort(500, "wtf");  // 异常
		})
		.resource("/pub", "/static")  // 静态资源
		.child("/user", () -> {  // 路由嵌套
			return new Router((ctx, req) -> {
				ctx.html("Hello, World");
			})
			.handler("/hello.json", (ctx, req) -> {
				ctx.json(new String[] { "Hello", "World" });
			})
			.filter((ctx, req, before) -> {  // 过滤器、拦截器
				if (before) {
					System.out.printf("before %s\n", req.path());
				} else {
					System.out.printf("after %s\n", req.path());
				}
				return true;
			});
		});

		var rd = new KidsRequestDispatcher("/kids", router); // 请求派发器
		rd.templateRoot("/tpl"); // 模版classpath根目录
		rd.exception(500, (ctx, e) -> { // 异常处理
			ctx.html("what the fuck it is", 500);
		})
		.exception((ctx, e) -> {  // 通用异常处理
			ctx.html("mother fucker!", e.getStatus().code());
		});

		var server = new HttpServer("localhost", 8080, 2, 16, rd);
		server.start();
		
		Runtime.getRuntime().addShutdownHook(new Thread() {

			public void run() {
				server.stop(); // 优雅停机
			}

		});		
	}

}

http://localhost:8080/kids
http://localhost:8080/kids/hello
http://localhost:8080/kids/hello.json
http://localhost:8080/kids/world
http://localhost:8080/kids/error
http://localhost:8080/kids/pub/bootstrap.min.css
http://localhost:8080/kids/user
http://localhost:8080/kids/user/hello
```

Discussion
--
关注公众号「码洞」，我们一起来聊聊这个框架

