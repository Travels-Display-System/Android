package com.zhao.ui_basic.mvp;

public interface BaseView<T> {
     void setData(T o,String action);
     void error(String msg);
}
