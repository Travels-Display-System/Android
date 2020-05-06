package com.zhao.ui_basic.ui.main.Presenter;


import android.util.Log;

import com.zhao.ui_basic.Utils.Utils;
import com.zhao.ui_basic.http.ApiService;
import com.zhao.ui_basic.http.HttpUtils;
import com.zhao.ui_basic.http.ResponseListener;
import com.zhao.ui_basic.mvp.BaseModel;
import com.zhao.ui_basic.mvp.BasePresenter;
import com.zhao.ui_basic.ui.main.Model.MainModel;
import com.zhao.ui_basic.ui.main.View.MainView;
import com.zhao.ui_basic.ui.user.model.RegisterModel;

import java.util.List;

import static com.zhao.ui_basic.http.ActionString.GET_TRAVEL_LIST;

public class MainPresenter extends BasePresenter<MainView> {
//处理事务
    /**
     * 发起注册
     */
    public void getTravelPage(int page) {
        Log.e("++++++++++mpther fuker","+++++++++++++++++++++++++++++++++++++++++");
        HttpUtils.sendHttp(HttpUtils.createApi(ApiService.class).getTravelPage(page),
                new ResponseListener<List<MainModel>>() {
                    @Override
                    public void onSuccess(List<MainModel> data) {
                        System.out.print("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        if (!Utils.isEmpty(data)) {
                                if (getmBaseView() != null) {
                                    getmBaseView().setData(data, GET_TRAVEL_LIST);
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
