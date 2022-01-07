package com.coinbase.websocket.message;

import com.coinbase.websocket.factory.MessageFactory;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HeartBeat extends MessageFactory {

    private Long lastTradeId;

    public HeartBeat() {
        setType("heartbeat");
    }
}
