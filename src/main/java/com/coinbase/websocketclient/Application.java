package com.coinbase.websocketclient;

import java.net.URI;
import java.net.URISyntaxException;

public class Application {

    private static final String URI = "wss://ws-feed.exchange.coinbase.com";
    private static final String jsonSubscribeMessage = "{\n" +
            "    \"type\": \"subscribe\",\n" +
            "    \"product_ids\": [\n" +
            "        \"ETH-USD\",\n" +
            "        \"ETH-EUR\"\n" +
            "    ],\n" +
            "    \"channels\": [\n" +
            "        \"level2\",\n" +
            "        \"heartbeat\",\n" +
            "        {\n" +
            "            \"name\": \"ticker\",\n" +
            "            \"product_ids\": [\n" +
            "                \"ETH-BTC\",\n" +
            "                \"ETH-USD\"\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    public static void main(String[] args) {
        try {
            // open websocket
            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI(URI));

            // add listener
            clientEndPoint.addMessageHandler(System.out::println);

            // send message to websocket
            clientEndPoint.sendMessage(jsonSubscribeMessage);

            // wait 5 seconds for messages from websocket
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            System.err.println("Interrupted!" + e);
        } catch (URISyntaxException ex) {
            System.err.println("Exception: " + ex.getMessage());
        }
    }
}
