package com.coinbase.websocket.message;

import com.coinbase.websocket.factory.MessageFactory;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
public class TickerMessage extends MessageFactory {

    private Long tradeId;
    private Long sequence;
    private Instant time;
    private String productId;
    private BigDecimal price;
    private String side;
    private BigDecimal lastSize;
    private BigDecimal bestBid;
    private BigDecimal bestAsk;

    public TickerMessage() {
        setType("ticker");
    }
}
