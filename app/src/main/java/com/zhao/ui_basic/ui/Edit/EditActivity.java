package com.zhao.ui_basic.ui.Edit;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhao.ui_basic.R;
import com.zhao.ui_basic.ui.Edit.model.WorkModel;
import com.zhao.ui_basic.ui.Edit.presenter.EditPresernter;
import com.zhao.ui_basic.ui.Edit.view.EditView;
import com.zhao.ui_basic.ui.base.BaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity  extends BaseActivity<EditView, EditPresernter> implements EditView, View.OnClickListener, TextWatcher {
    private TextView tv_cancel;
    private TextView tv_send;
    private EditText et_profession;
    private EditText et_title;
    private String description;
    private String title;
    private String timestamp;

    @Override
    public boolean isRegister() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit;
    }

    @Override
    public void initView() {
        tv_cancel = findViewById(R.id.tv_cancel);
        tv_send = findViewById(R.id.tv_send);
        et_profession = findViewById(R.id.et_profession);
        et_title = findViewById(R.id.et_title);
        tv_send.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        if(getIntent()!=null){
            int type = getIntent().getIntExtra("type",0);
            if(type != 0){
                title = getIntent().getStringExtra("title");
                description = getIntent().getStringExtra("content");
                et_title.setText(title);
                et_profession.setText(description);
            }
        }

    }

    @Override
    public void initData() {

    }

    @Override
    public EditPresernter createPresenter() {
        return new EditPresernter();
    }

    @Override
    public void setData(Object data, String action) {

    }

    @Override
    public void error(String msg) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_send:
                getmPersenter().sendTravel(description, "dyj", timestamp,title);
                finish();
                break;
            case R.id.tv_cancel:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        title = getEditText(et_title);
        description = getEditText(et_profession);
        SimpleDateFormat   formatter   =   new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
        Date curDate =  new Date(System.currentTimeMillis());
        timestamp  =   formatter.format(curDate);
    }
}
