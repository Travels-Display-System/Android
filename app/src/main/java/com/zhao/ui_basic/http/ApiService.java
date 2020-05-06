package com.zhao.ui_basic.http;

import com.zhao.ui_basic.mvp.BaseModel;
import com.zhao.ui_basic.ui.main.Model.MainModel;
import com.zhao.ui_basic.ui.user.model.RegisterModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    //Form* 表单提交
    //GET ——Query   POST ——Field
    @GET("/travel/query")
    Observable<List<MainModel>> getTravelPage(@Query("page") int page);

    @FormUrlEncoded
    @POST("/user")
    Observable<BaseModel<RegisterModel>> sendRegister(@Field("username") String username,
                                                      @Field("password") String password);
}
