package com.devitis.websocket.ui.presenter;

import com.devitis.websocket.ui.main.IBaseView;

/**
 * Created by Diana on 11.04.2019.
 */

public interface IBasePresenter<V extends IBaseView> {

    void attachView(V view);

    void detachView();
}
