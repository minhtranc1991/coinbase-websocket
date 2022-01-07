package com.coinbase.websocket.client;

import java.net.URI;
import java.util.List;
import javax.websocket.*;

import com.coinbase.websocket.factory.OrderBookMessage;
import org.apache.log4j.Logger;

@ClientEndpoint
public class WebsocketClientEndpoint {

    private static final Logger logger = Logger.getLogger(WebsocketClientEndpoint.class);

    Session userSession = null;
    private MessageHandler messageHandler;

    public WebsocketClientEndpoint(URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.setDefaultMaxTextMessageBufferSize(1000000);
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            logger.error("Exception: " + e);
        }
    }

    @OnOpen
    public void onOpen(Session userSession) {
        logger.info("Opening websocket");
        this.userSession = userSession;
    }

    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        logger.info("Closing websocket");
        this.userSession = null;
    }

    @OnMessage
    public void onMessage(String message) {
        if (this.messageHandler != null) {
            this.messageHandler.handleMessage(message);
        }
    }

    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }

    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }

    public interface MessageHandler {
        public void handleMessage(String message);

        List<OrderBookMessage> getQueuedMessages(Long sequenceId);
    }
}
