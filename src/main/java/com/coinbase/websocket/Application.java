package com.coinbase.websocket;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class Application implements ApplicationListener<ApplicationReadyEvent> {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private static final String URL = "wss://ws-feed.exchange.coinbase.com";
    private static final String JSON_SUBSCRIBE_MESSAGE = "{\n" +
            "    \"type\": \"subscribe\",\n" +
            "    \"product_ids\": [\n" +
            "        \"ETH-USD\",\n" +
            "        \"ETH-EUR\"\n" +
            "    ],\n" +
            "    \"channels\": [\n" +
            "        \"level2\",\n" +
//            "        \"heartbeat\",\n" +
            "        {\n" +
            "            \"name\": \"ticker\",\n" +
            "            \"product_ids\": [\n" +
            "                \"ETH-BTC\",\n" +
            "                \"ETH-USD\"\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        websocketConnect();
    }

    public static void websocketConnect() {
        try {
            // open websocket
            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI(URL));

            // add listener
            clientEndPoint.addMessageHandler(System.out::println);

            // send message to websocket
            clientEndPoint.sendMessage(JSON_SUBSCRIBE_MESSAGE);

            // wait 5 seconds for messages from websocket
//            Thread.sleep(5000);

//        } catch (InterruptedException e) {
//            System.err.println("Interrupted!" + e);
        } catch (URISyntaxException ex) {
            System.err.println("Exception: " + ex.getMessage());
        }
    }
}
