package com.zhao.ui_basic.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhao.ui_basic.R;
import com.zhao.ui_basic.Utils.Utils;
import com.zhao.ui_basic.Utils.event.EventMessage;
import com.zhao.ui_basic.ui.base.BaseActivity;
import com.zhao.ui_basic.ui.main.Model.MainModel;
import com.zhao.ui_basic.ui.main.Presenter.TravelPresenter;
import com.zhao.ui_basic.ui.main.View.TravelView;
import com.zhao.ui_basic.ui.main.adaptor.PhotoAdaptor;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class TravelDetails extends  BaseActivity<TravelView, TravelPresenter> implements TravelView, View.OnClickListener {
    private ViewPager vpPhoto;
    private LinearLayout llLine;
    private PhotoAdaptor photoAdaptor;
    private View[] vLines;
    private String Id;
    private MainModel model;

    private TextView tvName;
    private TextView tvAge;
    private TextView tvWork;
    private TextView tvBio;
    private ImageView ivFinish;
    private ImageView icPass;
    private ImageView icBule;
    private ImageView icLike;

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
        tvName = findViewById(R.id.tv_name);
        tvAge = findViewById(R.id.tv_age);
        tvWork = findViewById(R.id.tv_work);
        tvBio = findViewById(R.id.tv_bio);
        ivFinish = findViewById(R.id.iv_finish);
        icBule = findViewById(R.id.ic_bule);
        icLike = findViewById(R.id.ic_like);
        icPass = findViewById(R.id.ic_pass);
        ivFinish.setOnClickListener(this);
        icPass.setOnClickListener(this);
        icLike.setOnClickListener(this);
        icBule.setOnClickListener(this);
        if(getIntent()!=null){
            Id = getIntent().getStringExtra("TravelId");
        }
    }

    @Override
    public void initData() {
        getmPersenter().getTravelDetail(Id);
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
        if (!Utils.isEmpty(data)) {
            model = (MainModel) data;
            /**if (!Utils.isListEmpty(model.getPhoto())) {
                initLine(model.getPhoto().size());
                if (photoAdaptor == null) {
                    photoAdaptor = new PhotoAdaptor(model.getPhoto());
                    vpPhoto.setAdapter(photoAdaptor);
                } else {
                    photoAdaptor.notifyDataSetChanged();
                }
            }*/

            tvName.setText(model.getTitle());
            tvAge.setText(model.getId().toString());
            tvWork.setText(model.getUsername());
            tvBio.setText(model.getContent());

        }
    }

    @Override
    public void error(String msg) {
        Log.e("Travel Detail ===>",msg);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                finish();
                break;
            case R.id.ic_pass:
                onEventMessage(new EventMessage(1,Id));
                break;
            case R.id.ic_bule:
                    getmPersenter().getAction("1588402474590", model.getId(), 1);
               // setAction(1);
                break;
            case R.id.ic_like:
                getmPersenter().getAction("1588402474590", model.getId(), 0);
                //setAction(2);
                break;
            default:
                break;
        }
    }



    @Subscribe(threadMode = ThreadMode.MAIN)//与。。。通信
    public void onEventMessage(EventMessage message){
        if (!Utils.isEmpty(message)) {
            if (message.getCode() == 1) {
                Intent intent = new Intent();
                intent.putExtra("TravelId",String.valueOf(message.getData()));
                intent.setClass(this,ReportActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_bottom, R.anim.anim_bottom_not);
            }
        }
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.anim_bottom_out);
    }

    @Override
    public void acType(int type) {
        switch (type) {
            case 0:
                finish();
                break;
            case 1:
                finish();
                break;
            default:
                break;
        }
    }
}
