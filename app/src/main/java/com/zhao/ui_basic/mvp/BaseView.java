package com.zhao.ui_basic.mvp;

public interface BaseView<T> {
     void setData(T o);
     void error(String msg);
}
