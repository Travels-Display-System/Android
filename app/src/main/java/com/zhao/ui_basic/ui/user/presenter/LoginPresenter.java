package com.zhao.ui_basic.ui.user.presenter;

import com.zhao.ui_basic.mvp.BasePresenter;
import com.zhao.ui_basic.ui.user.view.LoginView;

public class LoginPresenter extends BasePresenter<LoginView> {
    public void sendLogin(String name,String password){
        getmBaseView().setData(name+"  "+password,"send_Login");
    }
}
