package org.okxborkerdemo.server;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.okxborkerdemo.WebSocketClient;

import java.io.Closeable;
import java.io.IOException;
import java.util.TreeMap;
import java.util.jar.JarEntry;

import static org.junit.jupiter.api.Assertions.*;

class PublicChannelServiceTest {
    WebSocketClient client = new WebSocketClient(true);

    @Test
    void subscribeTickers() throws InterruptedException, IOException {
        Closeable closeable = client.getPublicChannelService().subscribeTickers("BTC-USDT", JsonObject.class,
                response -> {
                    System.out.println("response class:" + response.getClass());
                    System.out.println(response);
                });
        Thread.sleep(10000);
        closeable.close();
    }
}