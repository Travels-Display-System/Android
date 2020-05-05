package com.zhao.ui_basic.ui.user;

import androidx.annotation.Nullable;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhao.ui_basic.ui.main.MainActivity;
import com.zhao.ui_basic.R;
import com.zhao.ui_basic.Utils.SharedPreferencesUtils;
import com.zhao.ui_basic.ui.base.BaseActivity;
import com.zhao.ui_basic.ui.user.presenter.RegisterPresenter;
import com.zhao.ui_basic.ui.user.view.RegisterView;

public class RegisterActivity extends BaseActivity<RegisterView, RegisterPresenter>
        implements TextWatcher,View.OnClickListener,RegisterView{
    private ImageView imageView;
    private EditText etName;
    private EditText etEmail;
    private EditText etPass;
    private Button btRegister;
    private TextView tvLogin;

    private String name;
    private String password;
    private String Email;
    private String photo;
    @Override
    public boolean isRegister() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView(){
        imageView = findViewById(R.id.reg_iv_photo);
        etName = findViewById(R.id.et_username);
        etPass = findViewById(R.id.et_password);
        etEmail = findViewById(R.id.et_email);
        btRegister = findViewById(R.id.et_submit);
        tvLogin = findViewById(R.id.go_to_login);
        etName.addTextChangedListener(this);
        etEmail.addTextChangedListener(this);
        etPass.addTextChangedListener(this);
        imageView.setOnClickListener(this);
        btRegister.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        initData();
    }
    @Override
    public void initData(){

    }

    @Override
    public RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    private void selectPhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI,"image/*");
        //确保所有格式都可使用
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if(requestCode == 100){
            if(resultCode == RESULT_OK){
                photo  = data.getDataString();
                imageView.setImageURI(Uri.parse(photo));
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int a,int b,int c){

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int a,int b,int c){

    }

    @Override
    public void afterTextChanged(Editable s) {
        name = getEditText(etName);
        password = getEditText(etPass);
        Email = getEditText(etEmail);
        if(name.length() > 0 && Email.length() > 0 && password.length() >= 6 ){
            btRegister.setBackgroundResource( R.drawable.background_button);
        }else{
            btRegister.setBackgroundResource( R.drawable.background_button_not);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.go_to_login:
                showToast("go to Login");
                startActivity(new Intent(this, LogActivity.class));
                break;
            case R.id.et_submit:
                if (TextUtils.isEmpty(photo)) {
                    showToast("Please Select Photo");
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    showToast("Please Enter UserName");
                    return;
                }
                if (TextUtils.isEmpty(Email)) {
                    showToast("Please Enter Email");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    showToast("Please Enter PassWord");
                    return;
                }
                showToast("register");
                break;
            case R.id.reg_iv_photo:
                selectPhoto();
                break;
            default:
                break;
        }
    }

    @Override
    public void setData(Object data) {
        if (data != null) {
            String token = (String) data;
            SharedPreferencesUtils.saveToken(this, "token", token);//保存token
            startIntent(MainActivity.class, true);
        }
    }

    @Override
    public void error(String msg) {
        Log.e("error: =======>",msg);
    }
}
