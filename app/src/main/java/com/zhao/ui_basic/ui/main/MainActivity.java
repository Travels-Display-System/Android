package com.zhao.ui_basic.ui.main;

import android.util.Log;
import android.widget.ImageView;

import com.zhao.ui_basic.R;
import com.zhao.ui_basic.ui.main.Model.MainModel;
import com.zhao.ui_basic.ui.main.Presenter.MainPresenter;
import com.zhao.ui_basic.ui.main.View.MainView;
import com.zhao.ui_basic.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView{

    private ImageView ivProfile;
    private ImageView ivBoost;
    private ImageView ivMessage;
    private ImageView ivRewind;
    private ImageView ivWhoSees;
    private ImageView ivSuperlike;
    private ImageView ivUnlimitedLikes;
    private CardLayout mCardLayout;
    private List<MainCard> mainCards = new ArrayList<>();
    private int page = 0;
    private int pageSize = 20;
    private List<MainModel> modelList = new ArrayList<>();
    @Override
    public boolean isRegister() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }


    @Override
    public void setData(Object o) {

    }

    @Override
    public void error(String e) {
        Log.e("error:", e);
    }
}
