package rpckids.client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import rpckids.common.MessageDecoder;
import rpckids.common.MessageEncoder;
import rpckids.common.MessageOutput;
import rpckids.common.MessageRegistry;
import rpckids.common.RequestId;

public class RPCClient {
	private final static Logger LOG = LoggerFactory.getLogger(RPCClient.class);

	private String ip;
	private int port;
	private Bootstrap bootstrap;
	private EventLoopGroup group;
	private MessageCollector collector;
	private boolean started;
	private boolean stopped;

	private MessageRegistry registry = new MessageRegistry();

	public RPCClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
		this.init();
	}

	public RPCClient rpc(String type, Class<?> reqClass) {
		registry.register(type, reqClass);
		return this;
	}

	public <T> RpcFuture<T> sendAsync(String type, Object payload) {
		if (!started) {
			connect();
			started = true;
		}
		String requestId = RequestId.next();
		MessageOutput output = new MessageOutput(requestId, type, payload);
		return collector.send(output);
	}

	public <T> T send(String type, Object payload) {
		RpcFuture<T> future = sendAsync(type, payload);
		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			throw new RPCException(e);
		}
	}

	public void init() {
		bootstrap = new Bootstrap();
		group = new NioEventLoopGroup(1);
		bootstrap.group(group);
		MessageEncoder encoder = new MessageEncoder();
		collector = new MessageCollector(registry, this);
		bootstrap.channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ChannelPipeline pipe = ch.pipeline();
				pipe.addLast(new ReadTimeoutHandler(60));
				pipe.addLast(new MessageDecoder());
				pipe.addLast(encoder);
				pipe.addLast(collector);
			}

		});
		bootstrap.option(ChannelOption.TCP_NODELAY, true).option(ChannelOption.SO_KEEPALIVE, true);
	}

	public void connect() {
		bootstrap.connect(ip, port).syncUninterruptibly();
	}

	public void reconnect() {
		if (stopped) {
			return;
		}
		bootstrap.connect(ip, port).addListener(future -> {
			if (future.isSuccess()) {
				return;
			}
			if (!stopped) {
				group.schedule(() -> {
					reconnect();
				}, 1, TimeUnit.SECONDS);
			}
			LOG.error("connect {}:{} failure", ip, port, future.cause());
		});
	}

	public void close() {
		stopped = true;
		collector.close();
		group.shutdownGracefully(0, 5000, TimeUnit.SECONDS);
	}

}
