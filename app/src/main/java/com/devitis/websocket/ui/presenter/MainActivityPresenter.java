package com.devitis.websocket.ui.presenter;

import com.devitis.websocket.data.model.InitialResponse;
import com.devitis.websocket.data.service.WebSocketClient;
import com.devitis.websocket.data.utils.ExecutorSupplier;
import com.devitis.websocket.ui.contracts.MainActivityContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by Diana on 11.04.2019.
 */

public class MainActivityPresenter extends BasePresenterImpl<MainActivityContract.IView>
        implements MainActivityContract.IPresenter {

    private MainActivityContract.IView iView;


    public MainActivityPresenter(MainActivityContract.IView iView) {
        this.iView = iView;
    }

    @Override
    public void sendErrorMessage(String message) {

        iView.showErrorMessage(message);

    }

    @Override
    public void check() {

        ExecutorSupplier.getInstance().getWorkerThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                Call<InitialResponse> c  = WebSocketClient.getWebSocket().check();
                c.enqueue(new Callback<InitialResponse>() {
                    @Override
                    public void onResponse(Call<InitialResponse> call, Response<InitialResponse> response) {
                        InitialResponse resp = response.body();
                        if (resp.getStatus().equals("OK"))
                            iView.connected();
                        else
                            iView.showErrorMessage("Couldn't Connect");
                    }

                    @Override
                    public void onFailure(Call<InitialResponse> call, Throwable t) {
                        iView.showErrorMessage("Couldn't Connect");
                    }
                });
            }
        });
    }

}
