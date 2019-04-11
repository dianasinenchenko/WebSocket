package com.devitis.websocket.ui.main.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devitis.websocket.R;
import com.devitis.websocket.ui.contracts.MainActivityContract;
import com.devitis.websocket.ui.presenter.MainActivityPresenter;

/**
 * Created by Diana on 11.04.2019.
 */

public class MainFragment extends BaseFragment<MainActivityContract.IPresenter>
        implements MainActivityContract.IView, View.OnClickListener {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.check();
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void showErrorMessage(String message) {

        showShortToast(message);

    }

    @Override
    public void connected() {



    }

    @NonNull
    @Override
    protected MainActivityContract.IPresenter getPresenterInstance() {
        return new MainActivityPresenter(this);

    }
}
