package ws;

import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import org.junit.jupiter.api.Test;
import ws.entry.WebSocketMessage;

class OkxApiWebSocketListenerTest {
    /**
     * 同一个频道是可以重复订阅的
     * */
    @Test
    void test(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("wss://wspap.okx.com:8443/ws/v5/public?brokerId=9999").build();
        WebSocket ws = client.newWebSocket(request,new OkxApiWebSocketListener<>(JsonObject.class,System.out::println));
        WebSocketMessage message = new WebSocketMessage();
        message.setOp("subscribe");
        message.add("instId","BTC-USDT");
        message.add("channel","trades");
        ws.send(message.getMessage().toString());

        message.setOp("subscribe");
        message.add("instId","ETH-USDT");
        message.add("channel","trades");
        ws.send(message.getMessage().toString());
//        ws.close(1001,"");
        try {
            Thread.sleep(100000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void t(){
        OkHttpClient client =new  OkHttpClient();
        Request request = new Request.Builder().url("wss://wspap.okx.com:8443/ws/v5/public?brokerId=9999").build();
        WebSocket ws = client.newWebSocket(request, new OkxApiWebSocketListener<>(JsonObject.class,System.out::println));
        WebSocketMessage message = new WebSocketMessage();
        message.setOp("subscribe");
        message.add("channel","candle1m");
        message.add("instId","BTC-USDT");
        ws.send(message.getMessage().toString());
        try {
            Thread.sleep(1000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}