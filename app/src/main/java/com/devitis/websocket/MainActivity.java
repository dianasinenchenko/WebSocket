package com.devitis.websocket;


import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import com.devitis.websocket.ui.contracts.MainActivityContract;
import com.devitis.websocket.ui.main.BaseActivity;
import com.devitis.websocket.ui.main.fragments.MainFragment;
import com.devitis.websocket.ui.presenter.MainActivityPresenter;

public class MainActivity extends BaseActivity<MainActivityContract.IPresenter>
        implements MainActivityContract.IView {

    @NonNull
    @Override
    protected MainActivityContract.IPresenter getPresenterInstance() {
        return new MainActivityPresenter(this);
    }

    void showMainFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction()
                .add(R.id.frame_container, new MainFragment(), MainFragment.class.getSimpleName());
        transaction.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showMainFragment();


    }

    @Override
    public void showErrorMessage(String message) {

        showShortToast(message);
    }

    @Override
    public void connected() {

    }
}
