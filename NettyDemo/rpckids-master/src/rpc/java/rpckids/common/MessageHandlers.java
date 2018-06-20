package rpckids.common;

import java.util.HashMap;
import java.util.Map;

public class MessageHandlers {

	private Map<String, IMessageHandler<?>> handlers = new HashMap<>();
	private IMessageHandler<MessageInput> defaultHandler;

	public void register(String type, IMessageHandler<?> handler) {
		handlers.put(type, handler);
	}

	public MessageHandlers defaultHandler(IMessageHandler<MessageInput> defaultHandler) {
		this.defaultHandler = defaultHandler;
		return this;
	}

	public IMessageHandler<MessageInput> defaultHandler() {
		return defaultHandler;
	}

	public IMessageHandler<?> get(String type) {
		IMessageHandler<?> handler = handlers.get(type);
		return handler;
	}

}
