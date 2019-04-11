package com.devitis.websocket.data.service;

import com.devitis.websocket.data.model.InitialResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Diana on 11.04.2019.
 */

public interface IWebSocket {

    @GET("   ")
    Call<InitialResponse> check();
}
