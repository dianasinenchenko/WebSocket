package com.devitis.websocket.ui.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.devitis.websocket.ui.presenter.IBasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Diana on 11.04.2019.
 */

public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity
        implements IBaseView {

    private Unbinder unbinder;

    private ProgressDialog progressDialog = null;

    protected @NonNull
    abstract P getPresenterInstance();

    protected P presenter;

    public void showShortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = getPresenterInstance();
        presenter.attachView(this);
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        unbinder.unbind();
    }

    @Override
    public void showProgress(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (progressDialog == null || !progressDialog.isShowing()) {

                    progressDialog = new ProgressDialog(BaseActivity.this);
                    progressDialog.setMessage(message);
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            }
        });
    }

    @Override
    public void hideProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (progressDialog != null && progressDialog.isShowing()) {

                    progressDialog.dismiss();
                }
            }
        });
    }
}
