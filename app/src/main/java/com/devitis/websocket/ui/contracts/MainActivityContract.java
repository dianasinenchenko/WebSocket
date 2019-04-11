package com.devitis.websocket.ui.contracts;

import android.view.View;

import com.devitis.websocket.ui.main.IBaseView;
import com.devitis.websocket.ui.presenter.IBasePresenter;

/**
 * Created by Diana on 11.04.2019.
 */

public class MainActivityContract {

    public interface IView extends IBaseView {

        void showErrorMessage(String message);
        void connected();

    }

    public interface IPresenter extends IBasePresenter<IView> {

        void sendErrorMessage(String message);
        void check();
    }
}

