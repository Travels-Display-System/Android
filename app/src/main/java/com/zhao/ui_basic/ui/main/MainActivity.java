package com.zhao.ui_basic.ui.main;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.zhao.ui_basic.R;
import com.zhao.ui_basic.Utils.Utils;
import com.zhao.ui_basic.Utils.event.EventMessage;
import com.zhao.ui_basic.ui.main.Model.MainModel;
import com.zhao.ui_basic.ui.main.Presenter.MainPresenter;
import com.zhao.ui_basic.ui.main.View.MainView;
import com.zhao.ui_basic.ui.base.BaseActivity;
import com.zhao.ui_basic.view.CardLayout;
import com.zhao.ui_basic.view.MainCard;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static com.zhao.ui_basic.http.ActionString.GET_TRAVEL_LIST;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView, View.OnClickListener{

    private ImageView ivProfile;
    private ImageView ivBoost;
    private ImageView ivMessage;
    private ImageView ivRewind;
    private ImageView ivWhoSees;
    private ImageView ivSuperlike;
    private ImageView ivUnlimitedLikes;
    private CardLayout mCardLayout;
    private List<MainCard> mainCards = new ArrayList<>();
    private int page = 1;
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
        ivProfile = findViewById(R.id.iv_profile);
        ivBoost = findViewById(R.id.iv_boost);
        ivMessage = findViewById(R.id.iv_message);
        ivRewind = findViewById(R.id.iv_rewind);
        ivSuperlike = findViewById(R.id.iv_superlike);
        ivUnlimitedLikes = findViewById(R.id.iv_unlimited_likes);
        ivWhoSees = findViewById(R.id.iv_who_sees);
        mCardLayout = findViewById(R.id.cd_layout);
        ivUnlimitedLikes.setOnClickListener(this);
        ivRewind.setOnClickListener(this);
        ivBoost.setOnClickListener(this);
        ivWhoSees.setOnClickListener(this);
        ivSuperlike.setOnClickListener(this);
        ivProfile.setOnClickListener(this);
        ivMessage.setOnClickListener(this);
    }

    @Override
    public void initData() {
        getmPersenter().getTravelPage(page);
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }



    @Override
    public void setData(Object data,String action) {
        if (action.equals(GET_TRAVEL_LIST)) {
            if (!Utils.isEmpty(data)) {
                modelList = (List<MainModel>) data;
                if (!Utils.isListEmpty(modelList)) {
                    for (MainModel model : modelList) {
                        MainCard mainCard = new MainCard(this);
                        mainCard.setModel(model);
                        mCardLayout.addCardView(mainCard);
                        mainCards.add(mainCard);
                    }
                }
            }
        }
    }

    @Override
    public void error(String e) {
        Log.e("error:", e);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_rewind:
                upData();
                break;
            case R.id.iv_who_sees:
                onClickLeft(R.mipmap.recs_nope_stamp);
                break;
            case R.id.iv_superlike:
                onClickLeft(R.mipmap.recs_superlike_stamp);
                break;
            case R.id.iv_unlimited_likes:
                onClickRight(R.mipmap.recs_like_stamp);
                break;
            case R.id.iv_boost:
                onClickRight(R.mipmap.recs_rewind_stamp);
                break;
            case R.id.iv_profile:
                break;
            case R.id.iv_message:
                break;
            default:
                break;
        }
    }



    @Subscribe(threadMode = ThreadMode.MAIN)//与。。。通信
    public void onEventMessage(EventMessage message){
        if (!Utils.isEmpty(message)) {
            if (message.getCode() == 1) {
                mainCards.remove(0);
                if ((int) message.getData() == 1) {
                    initData();
                }
            }else if(message.getCode() == 2) {
               startIntent(TravelDetails.class);
            }
        }
    }

    private void onClickLeft(int iamgeId) {
        if (!Utils.isListEmpty(mainCards)) {
            mainCards.get(0).leftSliding(iamgeId);
        }
    }

    private void onClickRight(int iamgeId) {
        if (!Utils.isListEmpty(mainCards)) {
            mainCards.get(0).rightSliding(iamgeId);
        }
    }

    private void upData() {
        if (mCardLayout.getChildCount() != 0) {
            mCardLayout.removeAllViews();
        }
        if (!Utils.isListEmpty(mainCards)) {
            mainCards.clear();
        }
        if (!Utils.isListEmpty(modelList)) {
            modelList.clear();
        }
        page++;
        getmPersenter().getTravelPage(page);
    }

}
