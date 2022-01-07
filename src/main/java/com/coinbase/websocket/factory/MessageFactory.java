package com.coinbase.websocket.factory;

import com.coinbase.websocket.message.HeartBeat;
import com.coinbase.websocket.message.L2UpdateMessage;
import com.coinbase.websocket.message.TickerMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.time.Instant;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = HeartBeat.class, name = "heartbeat"),
        @JsonSubTypes.Type(value = TickerMessage.class, name = "ticker"),
        @JsonSubTypes.Type(value = L2UpdateMessage.class, name = "l2update"),
})
public abstract class MessageFactory {

    private String type;
    private Long sequence;
    private Instant time;
    private String productId;
}
