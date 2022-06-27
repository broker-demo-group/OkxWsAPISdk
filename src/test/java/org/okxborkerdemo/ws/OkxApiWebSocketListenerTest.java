package org.okxborkerdemo.ws;

import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import org.junit.jupiter.api.Test;
import org.okxborkerdemo.WebSocketClient;
import org.okxborkerdemo.ws.OkxApiWebSocketListener;
import org.okxborkerdemo.server.entry.WebSocketMessage;

import java.io.Closeable;
import java.io.IOException;

class OkxApiWebSocketListenerTest {

    WebSocketClient webSocketClient = new WebSocketClient(true);

    /**
     * 同一个频道是可以重复订阅的
     */
    @Test
    void test1_1() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("wss://wspap.okx.com:8443/ws/v5/public?brokerId=9999").build();
        WebSocket ws = client.newWebSocket(request, new OkxApiWebSocketListener<>(JsonObject.class, System.out::println));
        WebSocketMessage message = new WebSocketMessage();
        message.setOp("subscribe");
        message.add("instId", "BTC-USDT");
        message.add("channel", "trades");
        ws.send(message.getMessage().toString());

        message.setOp("subscribe");
        message.add("instId", "ETH-USDT");
        message.add("channel", "trades");
        ws.send(message.getMessage().toString());
//        ws.close(1001,"");
        try {
            Thread.sleep(100000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1_2() throws IOException {
        WebSocketMessage message = new WebSocketMessage();
        message.setOp("subscribe");
        message.add("instId", "BTC-USDT");
        message.add("channel", "trades");
        Closeable subscribe = webSocketClient.subscribe(message, new OkxApiWebSocketListener<>(JsonObject.class, System.out::println));
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        subscribe.close();
    }
}