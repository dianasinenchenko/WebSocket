package com.devitis.websocket.data.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Diana on 11.04.2019.
 */

public class WebSocketClient {

    private static Retrofit retrofit;

    public WebSocketClient() {
    }

    public static final String BASE_WEB_SOCKET_URL = "  ";

    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_WEB_SOCKET_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static IWebSocket getWebSocket() {

        return getClient()
                .create(IWebSocket.class);
    }
}
