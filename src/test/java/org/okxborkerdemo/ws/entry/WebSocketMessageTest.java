package org.okxborkerdemo.ws.entry;

import org.junit.jupiter.api.Test;
import org.okxborkerdemo.server.entry.WebSocketMessage;

class WebSocketMessageTest {

    @Test
    void getMessage() {

        WebSocketMessage message = new WebSocketMessage();
        message.setOp("subscribe");
        message.add("channel", "tickers");
        message.add("instId", "BTC-USDT");
        message.add("param", "");
        System.out.println(message.getMessage());

    }
}