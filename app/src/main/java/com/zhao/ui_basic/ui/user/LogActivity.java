package com.zhao.ui_basic.ui.user;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhao.ui_basic.R;
import com.zhao.ui_basic.mvp.BasePresenter;
import com.zhao.ui_basic.ui.base.BaseActivity;
import com.zhao.ui_basic.ui.main.MainActivity;
import com.zhao.ui_basic.ui.user.presenter.LoginPresenter;
import com.zhao.ui_basic.ui.user.view.LoginView;

public class LogActivity extends BaseActivity<LoginView, LoginPresenter>
        implements TextWatcher, View.OnClickListener,LoginView{

    private EditText etName;
    private EditText etPass;
    private Button btRegister;
    private TextView tvLogin;

    private String name;
    private String password;
    @Override
    public boolean isRegister() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_log;
    }

    @Override
    public void initView(){
        etName = findViewById(R.id.lg_username);
        etPass = findViewById(R.id.lg_password);
        btRegister = findViewById(R.id.lg_submit);
        tvLogin = findViewById(R.id.go_to_register);
        etName.addTextChangedListener(this);
        etPass.addTextChangedListener(this);
        btRegister.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        initData();
    }
    @Override
    public void initData(){

    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void setData(Object data, String action) {
        showToast((String) data);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        name = getEditText(etName);
        password = getEditText(etPass);
        btRegister.setEnabled(true);
        btRegister.setBackgroundResource( R.drawable.background_button);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.go_to_register:
                showToast("go to Register");
                startIntent(RegisterActivity.class);
                break;
            case R.id.lg_submit:
                startIntent(MainActivity.class);
                break;
            default:
                break;
        }
    }


    @Override
    public void error(String msg) {

    }
}
