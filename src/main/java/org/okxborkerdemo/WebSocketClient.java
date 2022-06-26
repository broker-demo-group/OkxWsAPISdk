package org.okxborkerdemo;

import com.google.gson.JsonObject;
import okhttp3.Request;
import okhttp3.WebSocket;
import org.okxborkerdemo.ws.OkxApiWebSocketListener;

/**
 * @author: bowen
 * @description:
 * @date: 2022/6/24  8:48 AM
 **/
public class WebSocketClient {

    WebSocket.Factory client;
    boolean isSimulate;
    String baseUrl = "wss://wspap.okx.com:8443/ws/v5/public?brokerId=9999";
    WebSocket ws;

    public WebSocketClient(WebSocket.Factory client, boolean isSimulate) {
        this.client = client;
        this.isSimulate = isSimulate;
        ws = newWebSocket();
    }


    public WebSocket newWebSocket() {
        Request request = new Request.Builder().url(baseUrl).build();
        return ws = client.newWebSocket(request,new OkxApiWebSocketListener<>(JsonObject.class,System.out::println));

    }
}
