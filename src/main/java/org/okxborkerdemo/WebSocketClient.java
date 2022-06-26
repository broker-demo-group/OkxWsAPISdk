package org.okxborkerdemo;

import okhttp3.Request;
import okhttp3.WebSocket;

/**
 * @author: bowen
 * @description:
 * @date: 2022/6/24  8:48 AM
 *
 **/
public class WebSocketClient {

    WebSocket.Factory client;
    boolean isSimulate;
    static final String URL = "wss://wspap.okx.com:8443/ws/v5/public?brokerId=9999";
    WebSocket ws;
    public WebSocketClient(WebSocket.Factory client, boolean isSimulate) {
        this.client = client;
        this.isSimulate = isSimulate;
        ws =  newWebSocket();
    }


    public WebSocket newWebSocket(){
        Request request = new Request.Builder().url(URL).build();
//        WebSocket ws = client.newWebSocket(request,new OkxApiWebSocketListener<>(JsonObject.class,System.out::println));
        return ws;
    }
}
