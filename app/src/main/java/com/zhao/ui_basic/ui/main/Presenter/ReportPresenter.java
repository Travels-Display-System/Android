package com.zhao.ui_basic.ui.main.Presenter;

import com.zhao.ui_basic.Utils.Utils;
import com.zhao.ui_basic.http.ApiService;
import com.zhao.ui_basic.http.HttpUtils;
import com.zhao.ui_basic.http.ResponseListener;
import com.zhao.ui_basic.mvp.BasePresenter;
import com.zhao.ui_basic.ui.main.Model.Like;
import com.zhao.ui_basic.ui.main.Model.Report;
import com.zhao.ui_basic.ui.main.View.ReportView;

public class ReportPresenter extends BasePresenter<ReportView> {
    public String SEND_REPORT = "send_report";
    public void sendReport(
            String content,String userid,String travelid
    ){

        Report report = new Report(content,userid,travelid);
        HttpUtils.sendHttp(HttpUtils.createApi(ApiService.class).reportTravel(report),
                new ResponseListener<Report>() {

                    @Override
                    public void onSuccess(Report data) {
                        if (!Utils.isEmpty(data)) {
                            if (getmBaseView() != null) {
                                getmBaseView().setData(data,SEND_REPORT);
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
