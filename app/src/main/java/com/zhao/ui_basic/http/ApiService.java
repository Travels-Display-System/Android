package com.zhao.ui_basic.http;

import com.zhao.ui_basic.mvp.BaseModel;
import com.zhao.ui_basic.ui.user.model.RegisterModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    //Form* 表单提交
    //GET ——Query   POST ——Field
    @FormUrlEncoded
    @GET("/user/register")
    Observable<BaseModel<RegisterModel>> getRegister(@Query("user_type") int userType,
                                                     @Query("photo") String photo,
                                                     @Query("name") String name,
                                                     @Query("email") String email,
                                                     @Query("password") String pass);

}
