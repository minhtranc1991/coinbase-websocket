package com.coinbase.websocket.message;

import com.coinbase.websocket.factory.MessageFactory;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class L2UpdateMessage extends MessageFactory {

    private String[][] changes;

    public L2UpdateMessage() {
        setType("l2update");
    }
}
