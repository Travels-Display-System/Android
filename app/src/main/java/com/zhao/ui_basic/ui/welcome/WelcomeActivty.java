package com.zhao.ui_basic.ui.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.zhao.ui_basic.R;
import com.zhao.ui_basic.ui.base.BaseActivity;
import com.zhao.ui_basic.ui.main.MainActivity;
import com.zhao.ui_basic.ui.user.LogActivity;
import com.zhao.ui_basic.ui.welcome.presenter.WelcomePersenter;
import com.zhao.ui_basic.ui.welcome.view.WelcomeView;

public class WelcomeActivty extends BaseActivity<WelcomeView, WelcomePersenter> {


    @Override
    public boolean isRegister() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {
        new Handler().postDelayed(() -> {
            if (isToken()) {
                startIntent(MainActivity.class, true);
            } else {
                startIntent(LogActivity.class, true);
            }
        }, 1000);
    }

    @Override
    public void initData() {

    }

    @Override
    public WelcomePersenter createPresenter() {
        return new WelcomePersenter();
    }


    @Override
    public void setData(Object data,String action) {

    }


    @Override
    public void error(String e) {

    }
}
