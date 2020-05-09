package com.zhao.ui_basic.ui.Edit.listener;

import com.zhao.ui_basic.ui.Edit.model.WorkModel;

import java.util.List;

public interface clickListener {
    //RecyclerView的点击事件，将信息回调给view
    void onItemClick(int Position, List<WorkModel> dataBeanList);
}