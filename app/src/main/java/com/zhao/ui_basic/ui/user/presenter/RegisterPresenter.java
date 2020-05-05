package com.zhao.ui_basic.ui.user.presenter;

import com.zhao.ui_basic.Utils.Utils;
import com.zhao.ui_basic.http.ApiService;
import com.zhao.ui_basic.http.HttpUtils;
import com.zhao.ui_basic.http.ResponseListener;
import com.zhao.ui_basic.mvp.BaseModel;
import com.zhao.ui_basic.mvp.BasePresenter;
import com.zhao.ui_basic.ui.user.model.RegisterModel;
import com.zhao.ui_basic.ui.user.view.RegisterView;

public class RegisterPresenter extends BasePresenter<RegisterView> {
    /**
     * 发起注册
     */
    public void sendRegister(String photo, String name, String email, String pass) {
        HttpUtils.sendHttp(HttpUtils.createApi(ApiService.class)
                        .getRegister(1, photo, name, email, pass),
                new ResponseListener<BaseModel<RegisterModel>>() {
                    @Override
                    public void onSuccess(BaseModel<RegisterModel> data) {
                        if (!Utils.isEmpty(data)) {
                            if (data.getCode() == 200) {
                                RegisterModel model = data.getData();
                                if (getmBaseView() != null) {
                                    getmBaseView().setData(model.getToken());
                                }
                            }
                        }
                    }

                    @Override
                    public void onFail(String e) {
                        if (getmBaseView() != null) {
                            getmBaseView().error(e);
                        }
                    }
                });
    }
}
