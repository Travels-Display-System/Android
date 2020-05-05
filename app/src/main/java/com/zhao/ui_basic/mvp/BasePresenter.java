package com.zhao.ui_basic.mvp;

public class BasePresenter<V extends BaseView> {
    private V mBaseView;

    public void bindView(V view) {
        if (view != null) {
            this.mBaseView = view;
        }
    }

    public void unBindView() {
        mBaseView = null;
    }

    public V getmBaseView() {
        return mBaseView;
    }
}
