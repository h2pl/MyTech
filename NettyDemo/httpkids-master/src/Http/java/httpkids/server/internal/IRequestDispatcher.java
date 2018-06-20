package httpkids.server.internal;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public interface IRequestDispatcher {

	public void dispatch(ChannelHandlerContext ctx, FullHttpRequest req);

}
