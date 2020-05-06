package com.zhao.ui_basic.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhao.ui_basic.R;
import com.zhao.ui_basic.ui.base.BaseActivity;
import com.zhao.ui_basic.ui.main.Model.MainModel;
import com.zhao.ui_basic.ui.main.Presenter.TravelPresenter;
import com.zhao.ui_basic.ui.main.View.TravelView;
import com.zhao.ui_basic.ui.main.adaptor.PhotoAdaptor;
import java.util.ArrayList;
import java.util.List;

public class TravelDetails extends  BaseActivity<TravelView, TravelPresenter> implements TravelView, View.OnClickListener {

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean isRegister() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_travel;
    }

    @Override
    public void initView() {
        
    }

    @Override
    public void initData() {

    }

    @Override
    public TravelPresenter createPresenter() {
        return null;
    }

    @Override
    public void setData(Object data, String action) {

    }

    @Override
    public void error(String msg) {

    }
}
