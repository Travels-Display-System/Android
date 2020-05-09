package com.zhao.ui_basic.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zhao.ui_basic.R;
import com.zhao.ui_basic.Utils.SharedPreferencesUtils;
import com.zhao.ui_basic.mvp.BasePresenter;
import com.zhao.ui_basic.mvp.BaseView;


public abstract class BaseFragment<V extends BaseView, P extends BasePresenter>  extends Fragment implements BaseView {
    private String title;
    private View view;
    private P mPresenter;

    public BaseFragment(){

    }
    public BaseFragment(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);//加载器
        if(mPresenter == null){
            mPresenter = createPresernter();
        }
        mPresenter.bindView(this);
        initView(view);
        initData();
        return view;
    }

    protected abstract void initView(View view);
    protected abstract void initData();
    protected abstract int getLayoutId();
    protected abstract P createPresernter();

    public P getmPresenter() {
        return mPresenter;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unBindView();
    }
    public void startIntent(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        startActivity(intent);
    }
    public String getToken() {
        return SharedPreferencesUtils.getSaveToken(getActivity(), "token");
    }
}
