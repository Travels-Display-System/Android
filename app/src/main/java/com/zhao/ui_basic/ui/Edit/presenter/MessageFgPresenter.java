package com.zhao.ui_basic.ui.Edit.presenter;

import com.zhao.ui_basic.Utils.Utils;
import com.zhao.ui_basic.http.ApiService;
import com.zhao.ui_basic.http.HttpUtils;
import com.zhao.ui_basic.http.ResponseListener;
import com.zhao.ui_basic.mvp.BaseModel;
import com.zhao.ui_basic.mvp.BasePresenter;
import com.zhao.ui_basic.ui.Edit.model.WorkModel;
import com.zhao.ui_basic.ui.Edit.view.MessageFgView;
import com.zhao.ui_basic.ui.Edit.view.WorkView;

import java.util.List;

import okhttp3.ResponseBody;

import static com.zhao.ui_basic.http.ActionString.GET_SELF_TRAVEL_LIST;

public class MessageFgPresenter extends BasePresenter<MessageFgView> {

    public void getSelfTravelPageReleased() {
        HttpUtils.sendHttp(HttpUtils.createApi(ApiService.class).getSelfTravelR("dyj"),
                new ResponseListener<List<WorkModel>>() {
                    @Override
                    public void onSuccess(List<WorkModel> data) {
                        if (!Utils.isListEmpty(data)) {
                            if (getmBaseView() != null) {
                                getmBaseView().setData(data, GET_SELF_TRAVEL_LIST);
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
    public void delete(WorkModel workModel) {
        HttpUtils.sendHttp(HttpUtils.createApi(ApiService.class).deleteTravel(workModel),
                new ResponseListener<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody data) {
                        if (!Utils.isEmpty(data)) {
                            if (getmBaseView() != null) {
                                getmBaseView().update(workModel);
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