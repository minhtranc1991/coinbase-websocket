package com.coinbase.websocket.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderBookMessage extends MessageFactory implements Comparable<OrderBookMessage> {

    String tradeId;
    String side;

    BigDecimal price;

    String lastTradeId;

    String open24H;
    String volume24H;
    String low24H;
    String high24H;
    String volume30D;
    String bestBid;
    String bestAsk;
    String lastSize;
    Channel[] channels;

    @Override
    public int compareTo(OrderBookMessage other) {
        return this.getSequence().compareTo(other.getSequence());
    }

    public static class OrderBookMessageBuilder {
        private final OrderBookMessage message = new OrderBookMessage();

        public OrderBookMessageBuilder setType(String type) {
            message.setType(type);
            return this;
        }

        public OrderBookMessageBuilder setSide(String side) {
            message.setSide(side);
            return this;
        }

        public OrderBookMessageBuilder setPrice(BigDecimal price) {
            message.setPrice(price);
            return this;
        }

        public OrderBookMessageBuilder setSequence(Long id) {
            message.setSequence(id);
            return this;
        }

        public OrderBookMessage build() {
            return message;
        }
    }
}
