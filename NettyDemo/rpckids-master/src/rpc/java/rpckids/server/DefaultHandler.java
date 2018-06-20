package rpckids.server;



import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rpckids.common.IMessageHandler;
import rpckids.common.MessageInput;

public class DefaultHandler implements IMessageHandler<MessageInput> {

	private final static Logger LOG = LoggerFactory.getLogger(DefaultHandler.class);

	@Override
	public void handle(ChannelHandlerContext ctx, String requesetId, MessageInput input) {
		LOG.error("unrecognized message type {} comes", input.getType());
		ctx.close();
	}

}
