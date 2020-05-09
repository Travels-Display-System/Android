package com.zhao.ui_basic.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.zhao.ui_basic.R;
import com.zhao.ui_basic.Utils.Utils;
import com.zhao.ui_basic.Utils.event.EventMessage;
import com.zhao.ui_basic.ui.Edit.model.WorkModel;
import com.zhao.ui_basic.ui.main.Model.MainModel;
import com.zhao.ui_basic.ui.main.ReportActivity;
import com.zhao.ui_basic.ui.main.TravelDetails;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ListItem extends FrameLayout {
    private RelativeLayout relativeLayout;
    private ImageView ivPhoto;
    private TextView tv_content;
    private TextView tv_name;
    private WorkModel workModel;
    public ListItem(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public ListItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }



    public ListItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        inflate(getContext(), R.layout.list_item,this);
        ivPhoto = findViewById(R.id.iv_photo);
        tv_content = findViewById(R.id.tv_content);
        tv_name = findViewById(R.id.tv_name);
        ivPhoto.setOnClickListener(view -> {
            if (!Utils.isEmpty(workModel)) {
                EventBus.getDefault().post(new EventMessage(1, workModel.getId()));
            }
        });
    }
    /**
     * 设置卡片数据
     *
     * @param model
     */
    public void setModel(WorkModel model) {
        if (!Utils.isEmpty(model)) {
            this.workModel = model;
            Glide.with(getContext()).load(R.mipmap.s).into(ivPhoto);
            if (!Utils.isEmpty(model.getTitle())) {
                tv_name.setText(model.getTitle());
            }
            if (!Utils.isEmpty(model.getState())) {
                tv_content.setText(model.getAdvice());
            }
        }
    }

}
