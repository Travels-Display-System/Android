package com.zhao.ui_basic.http;

import com.zhao.ui_basic.mvp.BaseModel;
import com.zhao.ui_basic.ui.main.Model.Like;
import com.zhao.ui_basic.ui.main.Model.MainModel;
import com.zhao.ui_basic.ui.main.Model.Report;
import com.zhao.ui_basic.ui.user.model.RegisterModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    //Form* 表单提交
    //GET ——Query   POST ——Field
    @GET("/travel/query")
    Observable<List<MainModel>> getTravelPage(@Query("page") int page);


    @HTTP(method = "GET", path = "travel/{id}", hasBody = false)
    Observable<MainModel> getTravelById(@Path("id") String id);


    @FormUrlEncoded
    @POST("/user")
    Observable<RegisterModel> sendRegister(@Field("username") String username,
                                                      @Field("password") String password);

    @POST("/like")
    Observable<Like> likeTravel(@Body Like like);

    @POST("/unlike")
    Observable<Like> unlikeTravel(@Body Like like);

    @POST("report")
    Observable<Report> reportTravel(@Body Report report);

    @GET("travel/query/self")
    Observable<List<MainModel>> getSelfTravel(@Query("username") String username, @Query("page") int page);
}
