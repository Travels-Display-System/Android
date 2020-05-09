package com.zhao.ui_basic.ui.Edit.Fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.zhao.ui_basic.R;
import com.zhao.ui_basic.Utils.Utils;
import com.zhao.ui_basic.http.ApiService;
import com.zhao.ui_basic.ui.Edit.EditActivity;
import com.zhao.ui_basic.ui.Edit.adapter.FeedAdapter;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import com.zhao.ui_basic.ui.Edit.model.WorkModel;
import com.zhao.ui_basic.ui.Edit.presenter.FeedFgPresenter;
import com.zhao.ui_basic.ui.Edit.view.FeedFgView;
import com.zhao.ui_basic.ui.base.BaseFragment;
import com.zhao.ui_basic.ui.main.TravelDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FeedFragment extends BaseFragment<FeedFgView, FeedFgPresenter> implements FeedFgView {
    private RecyclerView reFeed;
    private FeedAdapter feedAdapter;
    private List<WorkModel> input;
    private int page;
    public FeedFragment(String title) {
        super(title);//父类构造器？
    }

    @Override
    protected void initView(View view) {
        reFeed = view.findViewById(R.id.rv_feed);
        reFeed.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {
        getmPresenter().getSelfTravelPageReview();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_feed;
    }

    @Override
    protected FeedFgPresenter createPresernter() {
        return new FeedFgPresenter();
    }

    @Override
    public void setData(Object o, String action) {
        if(!Utils.isEmpty(0)){
            input = new ArrayList<>();
            input = (List<WorkModel>)o;
            if(feedAdapter == null){
                feedAdapter = new FeedAdapter(input);
                reFeed.setAdapter(feedAdapter);
            }else{
                feedAdapter.notifyDataSetChanged();
            }
            Log.e("======================","============should get twice===============================");
            feedAdapter.setOnItemClickListener(new FeedAdapter.ItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    // 这里本来是跳转页面 ，我们就在这里直接让其弹toast来演示
                    Intent intent = new Intent();
                    intent.putExtra("type",1);
                    intent.putExtra("title", input.get(position).getTitle());
                    intent.putExtra("content", input.get(position).getContent());
                    intent.putExtra("Id", input.get(position).getId());
                    intent.setClass(getActivity(), EditActivity.class);
                    startActivity(intent);
                }

                @Override
                public void delete(int position) {
                    getmPresenter().delete(input.get(position));
                }
            });
        }
    }

    @Override
    public void error(String msg) {

    }

    @Override
    public void update(WorkModel w ) {
        if (!Utils.isListEmpty(input)) {
            input.remove(w);
        }
        feedAdapter.notifyDataSetChanged();
    }
}