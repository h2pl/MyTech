rpckids
--
RPC framework based on netty for kids

Feature
--
1. Extremely lightweight compared with other rpc frameworks.
2. Extremely easy to use, Extermly low cost for learning.
3. Extremely easy to understand, With approximately 800 lines of code.
4. Base on JSON protocol

Hello Server
--
```java
import java.util.ArrayList;
import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import rpckids.server.IMessageHandler;
import rpckids.server.MessageOutput;
import rpckids.server.RPCServer;

class FibRequestHandler implements IMessageHandler<Integer> {

    private List<Long> fibs = new ArrayList<>();

    {
        fibs.add(1L); // fib(0) = 1
        fibs.add(1L); // fib(1) = 1
    }

    @Override
    public void handle(ChannelHandlerContext ctx, String requestId, Integer n) {
        for (int i = fibs.size(); i < n + 1; i++) {
            long value = fibs.get(i - 2) + fibs.get(i - 1);
            fibs.add(value);
        }
        ctx.writeAndFlush(new MessageOutput(requestId, "fib_res", fibs.get(n)));
    }

}

public class DemoServer {

    public static void main(String[] args) {
        RPCServer server = new RPCServer("localhost", 8888, 2, 16);
        server.service("fib", Integer.class, new FibRequestHandler());
        server.start();
    }

}
```

Hello Client
--
```java
import rpckids.client.RPCClient;

public class DemoClient {

    private RPCClient client;

    public DemoClient(RPCClient client) {
        this.client = client;
        this.client.rpc("fib_res", Long.class).rpc("exp_res", ExpResponse.class);
    }

    public long fib(int n) {
        return (Long) client.send("fib", n);
    }

    public static void main(String[] args) {
        RPCClient client = new RPCClient("localhost", 8888);
        DemoClient demo = new DemoClient(client);
        for (int i = 0; i < 20; i++) {
            System.out.printf("fib(%d) = %d\n", i, demo.fib(i));
        }
    }

}
```

Discussion
--
关注公众号「码洞」，我们一起来聊聊这个框架
