package com.devitis.websocket.ui.presenter;

import com.devitis.websocket.ui.main.IBaseView;

/**
 * Created by Diana on 11.04.2019.
 */

public class BasePresenterImpl<V extends IBaseView> implements IBasePresenter<V> {

    protected V iView;

    @Override
    public void attachView(V view) {

        iView = view;

    }

    @Override
    public void detachView() {

        iView = null;

    }

    public V getView() {
        return iView;
    }
}
