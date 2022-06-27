package org.okxborkerdemo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import org.okxborkerdemo.server.PublicChannelService;
import org.okxborkerdemo.ws.OkxApiWebSocketListener;
import org.okxborkerdemo.server.entry.WebSocketMessage;

import java.io.Closeable;

/**
 * @author: bowen
 * @description:
 * @date: 2022/6/24  8:48 AM
 **/
public class WebSocketClient {

    WebSocket.Factory client;
    boolean isSimulate;
    String baseUrl = "wss://wspap.okx.com:8443/ws/v5/public?brokerId=9999";
    PublicChannelService publicChannelService;

    public WebSocketClient(boolean isSimulate) {
        this.client = new OkHttpClient.Builder().build();
        this.isSimulate = isSimulate;

        publicChannelService = new PublicChannelService(this);

    }

    public WebSocket createWebSocket(OkxApiWebSocketListener<?> listener) {
        Request request = new Request.Builder().url(baseUrl).build();
        return client.newWebSocket(request, listener);
    }

    public Closeable subscribe(WebSocketMessage message, OkxApiWebSocketListener<?> listener) {
        WebSocket ws = createWebSocket(listener);
        ws.send(message.getMessage().toString());
        return () -> {
            int code = 1000;
            listener.onClosing(ws, code, null);
            ws.close(code, null);
            listener.onClosed(ws, code, null);
        };
    }

    public PublicChannelService getPublicChannelService() {
        return publicChannelService;
    }
}
