package com.zhao.ui_basic.http;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求工具类
 */
public class HttpUtils {

    private static OkHttpClient okHttpClient;
    /**
     * 生产环境
     */
    private static final String BASE_URL = "http://101.132.114.122:8099/";
    /**
     * 测试环境
     */
    private static final String BASE_URL_TEST = "http://101.132.114.122:8099/";

    public static String isTest(boolean isTest) {
        if (isTest) {
            return BASE_URL_TEST;
        } else {
            return BASE_URL;
        }
    }

    //Builder模式，build()提交
    public static <T> T createApi(Class<T> tClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(isTest(true))//地址处理
                .client(getClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//java适配器
                .addConverterFactory(GsonConverterFactory.create())//gson工厂
                .build();
        return retrofit.create(tClass);
    }

    private static synchronized OkHttpClient getClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .readTimeout(20, TimeUnit.SECONDS)//超时设置
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(getInterceptor())
                    .build();
        }
        return okHttpClient;
    }

    /**
     * 拦截器
     *
     * @return
     */
    private static Interceptor getInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印日志
                Log.e("http:====>",message);
            }
        });
        return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }


    public static <T> void sendHttp(Observable<T> observable, final ResponseListener<T> listenter) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//主线程修改ui
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(T t) {
                        if (listenter != null) {
                            listenter.onSuccess(t);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("tag", "=========>" + e);
                        if (listenter != null) {
                            listenter.onFail(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}