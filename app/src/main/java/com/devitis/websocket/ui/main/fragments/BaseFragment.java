package com.devitis.websocket.ui.main.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.devitis.websocket.ui.main.BaseActivity;
import com.devitis.websocket.ui.main.IBaseView;
import com.devitis.websocket.ui.presenter.IBasePresenter;
import com.github.nkzawa.socketio.client.Manager;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URI;
import java.net.URISyntaxException;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Diana on 11.04.2019.
 */

public abstract class BaseFragment<P extends IBasePresenter> extends Fragment
        implements IBaseView {

    private BaseActivity baseActivity;
    private Unbinder unbinder;
    private ProgressDialog progressDialog;
    private Manager manager;
    protected Socket socket;

    protected @NonNull
    abstract P getPresenterInstance();

    protected P presenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);
        presenter = getPresenterInstance();
        presenter.attachView(this);

        try {

            manager = new Manager(new URI("http://kaboom.rksv.net"));
        } catch (URISyntaxException e) {

            e.printStackTrace();

        }

        socket = manager.socket("/watch");
        socket.connect();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof BaseActivity) {

            BaseActivity baseActivity = (BaseActivity) context;
            this.baseActivity = baseActivity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        baseActivity = null;
    }

    public BaseActivity getBaseActivity() {

        return baseActivity;
    }

    public void showShortToast(String message) {

        Toast.makeText(getBaseActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {

        if (unbinder != null) {

            unbinder.unbind();
        }
        socket.disconnect();
        super.onDestroyView();
    }

    @Override
    public void showProgress(final String message) {

        getBaseActivity().showProgress(message);

    }

    @Override
    public void hideProgress() {
        getBaseActivity().hideProgress();
    }
}
