package org.okxborkerdemo.ws;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.okxborkerdemo.exception.OkxApiException;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * @author: bowen
 * @description:
 * @date: 2022/6/23  2:31 PM
 **/
public class OkxApiWebSocketListener<T> extends WebSocketListener {

    private final OkxWebSocketCallBack<T> callback;
    private final Class<T> clazz;
    private boolean closing;

    public OkxApiWebSocketListener(Class<T> clazz, OkxWebSocketCallBack<T> callBack) {
        this.callback = callBack;
        this.clazz = clazz;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        System.out.println(webSocket.request().url() + " is Opening");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {

        JsonObject jsonObject = new Gson().fromJson(text, JsonObject.class);
        if (jsonObject.has("event") && "error".equals(jsonObject.get("event").getAsString())) {
            throw new OkxApiException(jsonObject.get("msg").getAsString(), jsonObject.get("code").getAsInt());
        }
        T t = new Gson().fromJson(text, clazz);
        callback.onResponse(t);
    }


    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        closing = true;
        System.out.println(webSocket.request().url() + " is Closing");
    }


    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        if (!closing) {
            callback.onFailure(t);
        }
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        System.out.println(webSocket.request().url() + " is Closed");
    }
}
