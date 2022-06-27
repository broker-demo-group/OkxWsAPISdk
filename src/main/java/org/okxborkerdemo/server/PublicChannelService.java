package org.okxborkerdemo.server;

import com.google.gson.JsonObject;
import org.okxborkerdemo.WebSocketClient;
import org.okxborkerdemo.server.entry.WebSocketMessage;
import org.okxborkerdemo.ws.OkxApiWebSocketListener;
import org.okxborkerdemo.ws.OkxWebSocketCallBack;

import java.io.Closeable;

/**
 * @author: bowen
 * @description:
 * @date: 2022/6/27  4:41 PM
 **/
public class PublicChannelService {
    WebSocketClient client;

    public PublicChannelService(WebSocketClient client) {
        this.client = client;
    }


    public <T> Closeable subscribeTickers(String instId, Class<T> clazz, OkxWebSocketCallBack<T> callBack) {
        WebSocketMessage message = new WebSocketMessage();
        message.setOp("subscribe");
        message.add("instId", instId);
        message.add("channel", "tickers");
        return client.subscribe(message, new OkxApiWebSocketListener<>(clazz, callBack));
    }
}
