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
    private ViewPager vpPhoto;
    private LinearLayout llLine;
    private PhotoAdaptor photoAdaptor;
    private View[] vLines;
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
        vpPhoto = findViewById(R.id.vp_photo);
        llLine = findViewById(R.id.ll_line);
    }

    @Override
    public void initData() {
        List<String> uri = new ArrayList<>();
        uri.add("");uri.add("");uri.add("");
        photoAdaptor = new PhotoAdaptor(uri);
        vpPhoto.setAdapter(photoAdaptor);
        vpPhoto.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectLine(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initLine();
    }
    private void initLine() {
        vLines = new View[3];
        for (int i = 0; i < 3; i++) {
            vLines[i] = new View(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    0, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.height = 10;
            layoutParams.weight = 1;
            layoutParams.rightMargin = 15;
            layoutParams.leftMargin = 15;
            vLines[i].setLayoutParams(layoutParams);
            if (i == 0) {
                vLines[i].setBackgroundResource(R.drawable.bg_line);
            } else {
                vLines[i].setBackgroundResource(R.drawable.bg_line_not);
            }
            llLine.addView(vLines[i]);
        }
    }


    private void selectLine(int index) {
        for (int i = 0; i < vLines.length; i++) {
            if (index == i) {
                vLines[i].setBackgroundResource(R.drawable.bg_line);
            } else {
                vLines[i].setBackgroundResource(R.drawable.bg_line_not);
            }
        }
    }

    @Override
    public TravelPresenter createPresenter() {
        return new TravelPresenter();
    }

    @Override
    public void setData(Object data, String action) {

    }

    @Override
    public void error(String msg) {

    }
}
