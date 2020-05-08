package com.zhao.ui_basic.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhao.ui_basic.R;
import com.zhao.ui_basic.Utils.event.EventMessage;
import com.zhao.ui_basic.ui.author.WorkListActivity;
import com.zhao.ui_basic.ui.base.BaseActivity;
import com.zhao.ui_basic.ui.main.Presenter.ReportPresenter;
import com.zhao.ui_basic.ui.main.View.ReportView;
import com.zhao.ui_basic.ui.user.view.RegisterView;

public class ReportActivity extends BaseActivity<ReportView, ReportPresenter> implements RegisterView, View.OnClickListener {
    private EditText Abstract;
    private EditText description;
    private TextView cancel;
    private TextView send;
    private String strAbstract;
    private String strDes;
    private String Id;
    @Override
    public boolean isRegister() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_report;
    }

    @Override
    public void initView() {
        Abstract = findViewById(R.id.et_abstract);
        description = findViewById(R.id.et_description);
        send = findViewById(R.id.tv_send);
        cancel = findViewById(R.id.tv_cancel);
        send.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void initData() {
        if(getIntent()!=null){
            Id = getIntent().getStringExtra("TravelId");
        }
    }

    @Override
    public ReportPresenter createPresenter() {
        return new ReportPresenter();
    }

    @Override
    public void setData(Object data, String action) {

    }

    @Override
    public void error(String msg) {
        Log.e("report Error ===>",msg);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_send:
                showToast("举报已发送");
                strAbstract = getEditText(Abstract);
                strDes = getEditText(description);
                getmPersenter().sendReport("Abstract: " + strAbstract + "/n details: " + strDes, "1588402474590", Id);
                break;
            case R.id.tv_cancel:
                finish();
                break;
            default:
                break;
        }
    }
}
