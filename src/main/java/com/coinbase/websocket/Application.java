package com.coinbase.websocket;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import com.coinbase.websocket.client.WebsocketClientEndpoint;
import com.coinbase.websocket.factory.OrderBookMessage;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class Application implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private static final String URL = "wss://ws-feed.exchange.coinbase.com";
    private static final String JSON_SUBSCRIBE_MESSAGE = "{\n" +
            "    \"type\": \"subscribe\",\n" +
            "    \"product_ids\": [\n" +
            "        \"ETH-USD\"\n" +
            "    ],\n" +
            "    \"channels\": [\n" +
            "        \"level2\"\n" +
            "    ]\n" +
            "}";

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        websocketConnect();
    }

    public static void websocketConnect() {
        try {
            //Open websocket
            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI(URL));

            //Add listener
            clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
                public void handleMessage(String message) {
                    logger.info(message);
                }

                @Override
                public List<OrderBookMessage> getQueuedMessages(Long sequenceId) {
                    return Collections.emptyList();
                }
            });

            //Send message to websocket
            clientEndPoint.sendMessage(JSON_SUBSCRIBE_MESSAGE);

        } catch (URISyntaxException ex) {
            logger.error("Exception: " + ex.getMessage());
        }
    }
}
