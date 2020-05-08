package com.zhao.ui_basic.ui.main.Presenter;

import com.zhao.ui_basic.Utils.Utils;
import com.zhao.ui_basic.http.ApiService;
import com.zhao.ui_basic.http.HttpUtils;
import com.zhao.ui_basic.http.ResponseListener;
import com.zhao.ui_basic.mvp.BasePresenter;
import com.zhao.ui_basic.ui.main.Model.Like;
import com.zhao.ui_basic.ui.main.Model.MainModel;
import com.zhao.ui_basic.ui.main.View.TravelView;

import java.util.List;

import static com.zhao.ui_basic.http.ActionString.GET_TRAVEL_DETAIL;

public class TravelPresenter extends BasePresenter<TravelView> {
    public void getTravelDetail(String id){
        HttpUtils.sendHttp(HttpUtils.createApi(ApiService.class).getTravelById(id),
                new ResponseListener<MainModel>() {

                    @Override
                    public void onSuccess(MainModel data) {
                        if (!Utils.isEmpty(data)) {
                            if (getmBaseView() != null) {
                                getmBaseView().setData(data,GET_TRAVEL_DETAIL);
                            }

                        }
                    }

                    @Override
                    public void onFail(String data) {
                        if (getmBaseView() != null) {
                            getmBaseView().error(data);
                        }
                    }
                });

    }

    public void getAction(String userId, String travelId, int type) {
        Like like = new Like(userId,travelId);
        if (type == 0)//喜欢他妈的是0
        {
            HttpUtils.sendHttp(HttpUtils.createApi(ApiService.class).likeTravel(like),
                    new ResponseListener<Like>() {

                        @Override
                        public void onSuccess(Like data) {
                            if (!Utils.isEmpty(data)) {
                                if (getmBaseView() != null) {
                                    getmBaseView().acType(type);
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
        }else{//不喜欢
            HttpUtils.sendHttp(HttpUtils.createApi(ApiService.class).unlikeTravel(like),
                    new ResponseListener<Like>() {

                        @Override
                        public void onSuccess(Like data) {
                            if (!Utils.isEmpty(data)) {
                                if (getmBaseView() != null) {
                                    getmBaseView().acType(type);
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
}
