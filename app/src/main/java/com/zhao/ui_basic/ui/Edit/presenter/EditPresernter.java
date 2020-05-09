package com.zhao.ui_basic.ui.Edit.presenter;

import com.zhao.ui_basic.Utils.Utils;
import com.zhao.ui_basic.http.ApiService;
import com.zhao.ui_basic.http.HttpUtils;
import com.zhao.ui_basic.http.ResponseListener;
import com.zhao.ui_basic.mvp.BasePresenter;
import com.zhao.ui_basic.ui.Edit.model.WorkModel;
import com.zhao.ui_basic.ui.main.Model.Report;

public class EditPresernter extends BasePresenter {
    public void sendTravel(
            String content,String userid,String timeStamp,String title
    ){

        WorkModel report = new WorkModel(title,content,userid,timeStamp);
        HttpUtils.sendHttp(HttpUtils.createApi(ApiService.class).sendTravel(report),
                new ResponseListener<WorkModel>() {
                    @Override
                    public void onSuccess(WorkModel data) {
                        if (!Utils.isEmpty(data)) {
                            if (getmBaseView() != null) {

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
