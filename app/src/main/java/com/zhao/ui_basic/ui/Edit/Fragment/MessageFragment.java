package com.zhao.ui_basic.ui.Edit.Fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhao.ui_basic.R;
import com.zhao.ui_basic.Utils.Utils;
import com.zhao.ui_basic.ui.Edit.adapter.MessageAdapter;
import com.zhao.ui_basic.ui.Edit.model.WorkModel;
import com.zhao.ui_basic.ui.Edit.view.MessageFgView;
import com.zhao.ui_basic.ui.Edit.presenter.MessageFgPresenter;
import com.zhao.ui_basic.ui.base.BaseFragment;
import com.zhao.ui_basic.ui.main.TravelDetails;

import java.util.ArrayList;
import java.util.List;

import static com.zhao.ui_basic.http.ActionString.GET_SELF_TRAVEL_LIST;

public class MessageFragment extends BaseFragment<MessageFgView, MessageFgPresenter> implements MessageFgView  {
    private RecyclerView recyclerView;
    private MessageAdapter adapter;
    private List<WorkModel> input;
    public MessageFragment(String title) {
        super(title);//父类构造器？
    }

    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.rv_message);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {
        getmPresenter().getSelfTravelPageReleased();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected MessageFgPresenter createPresernter() {
        return new MessageFgPresenter();
    }

    @Override
    public void setData(Object o, String action) {
        if(!Utils.isEmpty(0)){
            if(action == GET_SELF_TRAVEL_LIST){
                input = new ArrayList<>();
                input = (List<WorkModel>)o;
            }
            if(adapter == null){
                adapter = new MessageAdapter(input);
                recyclerView.setAdapter(adapter);
            }else{
                adapter.notifyDataSetChanged();
            }
            adapter.setOnItemClickListener(new MessageAdapter.ItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    // 这里本来是跳转页面 ，我们就在这里直接让其弹toast来演示
                    Intent intent = new Intent();
                    intent.putExtra("TravelId",String.valueOf(input.get(position).getId()));
                    intent.setClass(getActivity(), TravelDetails.class);
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
        adapter.notifyDataSetChanged();
    }
}
