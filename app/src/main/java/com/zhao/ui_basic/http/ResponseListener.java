package com.zhao.ui_basic.http;

public interface ResponseListener<T> {
    void onSuccess(T data);
    void onFail(String data);
}
