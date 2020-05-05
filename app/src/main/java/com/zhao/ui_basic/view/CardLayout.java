package com.zhao.ui_basic.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zhao.ui_basic.Utils.event.EventMessage;

import org.greenrobot.eventbus.EventBus;
public class CardLayout extends FrameLayout {
    //框
    public CardLayout(@NonNull Context context) {
        super(context);
    }

    public CardLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CardLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void addCardView(MainCard mainCard) {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(mainCard, 0, layoutParams);//添加子view
    }

    @Override
    public void removeView(View view) {
        super.removeView(view);
        EventBus.getDefault().post(new EventMessage(1,getChildCount()));
    }
}
