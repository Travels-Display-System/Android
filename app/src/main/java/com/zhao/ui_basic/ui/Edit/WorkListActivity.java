package com.zhao.ui_basic.ui.Edit;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.zhao.ui_basic.R;
import com.zhao.ui_basic.Utils.Utils;
import com.zhao.ui_basic.Utils.event.EventMessage;
import com.zhao.ui_basic.ui.Edit.Fragment.FeedFragment;
import com.zhao.ui_basic.ui.Edit.Fragment.MessageFragment;
import com.zhao.ui_basic.ui.Edit.adapter.FragementAdapter;
import com.zhao.ui_basic.ui.Edit.model.WorkModel;
import com.zhao.ui_basic.ui.Edit.presenter.WorkPresenter;
import com.zhao.ui_basic.ui.Edit.view.WorkView;
import com.zhao.ui_basic.ui.base.BaseActivity;
import com.zhao.ui_basic.ui.base.BaseFragment;
import com.zhao.ui_basic.ui.main.ReportActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class WorkListActivity extends BaseActivity<WorkView, WorkPresenter> implements WorkView, View.OnClickListener {
    private TabLayout tabLayout;
    private TextView tv_back;
    private TextView addNew;
    private ListView rvUserMessage;
    private List<WorkModel> workModelList;
    private ViewPager viewPager;
    private int page;
    @Override
    public boolean isRegister() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_worklist;
    }

    @Override
    public void initView() {
        tabLayout = findViewById(R.id.tbl_page_card);
        tv_back = findViewById(R.id.tv_Back);
        addNew = findViewById(R.id.tv_new);
        rvUserMessage = findViewById(R.id.list_item);
        viewPager = findViewById(R.id.vp_fragment);
        tv_back.setOnClickListener(this);
        addNew.setOnClickListener(this);
    }

    @Override
    public void initData() {
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new MessageFragment("Released"));
        fragments.add(new FeedFragment("Under Review"));
        FragementAdapter fragementAdapter = new FragementAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(fragementAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public WorkPresenter createPresenter() {
        return new WorkPresenter();
    }
    @Override
    public void setData(Object data,String action) {
    }

    @Override
    public void error(String msg) {
    Log.e("WrokList error=====>",msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_new:
                Intent intent = new Intent();
                intent.putExtra("type",0);
                intent.setClass(this, EditActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_bottom, R.anim.anim_bottom_not);
                break;
            case R.id.tv_Back:
                finish();
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
                intent.setClass(this, EditActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_bottom, R.anim.anim_bottom_not);
            }
        }
    }
}
