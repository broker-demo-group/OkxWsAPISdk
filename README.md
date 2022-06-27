# OkxWsAPISdk
### 例如订阅 **BTC-USDT** 的行情频道：
> 获取 **BTC-USDT** 的最新成交价、买一价、卖一价和24小时交易量等信息，每100ms有成交就收到一次推送数据。

```
  Closeable closeable = client.getPublicChannelService().subscribeTickers("BTC-USDT", JsonObject.class,
                  response -> {
                      System.out.println(response);
                  });
```

### output
```
{
    "arg": {
        "channel": "tickers",
        "instId": "BTC-USDT"
    },
    "data": [
        {
            "instType": "SPOT",
            "instId": "BTC-USDT",
            "last": "21454",
            "lastSz": "0.6860674",
            "askPx": "21455.4",
            "askSz": "34.69203406",
            "bidPx": "21455.3",
            "bidSz": "35.32497742",
            "open24h": "21418.3",
            "high24h": "21873.6",
            "low24h": "20927.1",
            "sodUtc0": "21039.7",
            "sodUtc8": "21327.5",
            "volCcy24h": "21708329174.25886254",
            "vol24h": "1019936.72059745",
            "ts": "1656324000453"
        }
    ]
}
```
