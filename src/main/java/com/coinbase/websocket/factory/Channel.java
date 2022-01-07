package com.coinbase.websocket.factory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import static com.coinbase.websocket.factory.ChannelName.*;

@Data
public class Channel {
    public static final Channel CHANNEL_HEARTBEAT = new Channel(HEARTBEAT, null);
    public static final Channel CHANNEL_TICKER = new Channel(TICKER, null);
    public static final Channel CHANNEL_LEVEL2 = new Channel(LEVEL_2, null);

    private ChannelName name;

    @JsonProperty("product_ids")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String[] productIds;

    public Channel() {
    }

    public Channel(ChannelName name, String[] productIds) {
        this.name = name;
        this.productIds = productIds;
    }
}
