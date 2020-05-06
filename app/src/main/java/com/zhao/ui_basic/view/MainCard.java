package com.zhao.ui_basic.view;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.zhao.ui_basic.R;
import com.zhao.ui_basic.Utils.Utils;
import com.zhao.ui_basic.Utils.event.EventMessage;
import com.zhao.ui_basic.ui.main.Model.MainModel;

import org.greenrobot.eventbus.EventBus;

public class MainCard extends FrameLayout implements View.OnTouchListener {

    private static final int TIME = 300;
    private static final float ROTATION = 30.0f;
    private static final float LEFT_DEFAULTX = -300.0f;
    private static final float RIGHT_DEFAULTX = 300.0f;

    private CardView cdViewMain;

    private ImageView ivPhoto;
    private TextView tvName;
    private TextView tvAge;
    private TextView tvWork;
    private ImageView ivDefault;
    private TextView tvLike;
    private TextView tvNope;
    //坐标
    private float oldX;
    private float oldY;
    private float newX;
    private float newY;
    private int sceWh;//屏幕宽度
    private float leftBoy;//左右临界值
    private float rightBoy;
    private float dX;
    private MainModel mainModel;

    public MainCard(Context context) {
        super(context);
        initView(context);
    }

    public MainCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MainCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        inflate(getContext(),R.layout.card_view,this);
        cdViewMain = findViewById(R.id.cd_view_main);
        ivDefault = findViewById(R.id.iv_default);
        ivPhoto = findViewById(R.id.iv_photo);
        tvLike = findViewById(R.id.tv_like);
        tvNope = findViewById(R.id.tv_nope);
        tvName = findViewById(R.id.title);
        tvWork = findViewById(R.id.username);
        cdViewMain.setOnTouchListener(this);
        sceWh = getSceWh(getContext());
        leftBoy = sceWh * (1.0f / 6.0f);
        rightBoy = sceWh * (5.0f / 6.0f);
        setOnTouchListener(this);
        ivDefault.setOnClickListener(view -> {
            if (!Utils.isEmpty(mainModel)) {
                EventBus.getDefault().post(new EventMessage(2, mainModel.getId()));
            }
        });
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                oldX = event.getX();
                oldY = event.getY();
                view.clearAnimation();
                return true;
            case MotionEvent.ACTION_UP:
                if (isLeftBoy(view)) {
                    startAnimate(view, -(sceWh * 2));//向左滑出
                } else if (isRightBoy(view)) {
                    startAnimate(view, (sceWh * 2));//向右滑出
                } else {
                    updataView(view);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                newX = event.getX();
                newY = event.getY();
                dX = newX - oldX;
                //获取当前位置
                view.setX(view.getX() + dX);
                view.setY(view.getY() + (newY - oldY));
                cardRotation(view, view.getX());
                setTvAlpha(view.getX() + (newX - oldX));
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    //向原始位置回弹
    private void updataView(View view) {
        view.animate()
                .x(0)
                .y(0)
                .rotation(0)
                .setInterpolator(new OvershootInterpolator())//插值器
                .setDuration(TIME);//动画是静态时间
    }

    //获取屏幕宽度用于删除处理
    private int getSceWh(Context context) {
        Point point = new Point();
        ((Activity) context).getWindowManager().getDefaultDisplay().getSize(point);
        return point.x;
    }

    //判定左右是否越界，未越界则回弹
    private boolean isLeftBoy(View view) {
        return (view.getX() + (view.getWidth() / 2)) < leftBoy;
    }

    private boolean isRightBoy(View view) {
        return (view.getX() + (view.getWidth() / 2)) > rightBoy;
    }

    //划出操作
    private void startAnimate(View view, int whX) {
        view.animate()
                .x(whX)
                .y(0)
                .setInterpolator(new AccelerateInterpolator())
                .setDuration(500)//倾斜角度
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        ViewGroup viewGroup = (ViewGroup) view.getParent();
                        if (viewGroup != null) {
                            viewGroup.removeView(view);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
    }

    //执行倾斜角操作
    private void cardRotation(View view, float px) {
        float rotation = (ROTATION * px) / sceWh;
        int ht = view.getHeight() / 2;
        if (oldY < ht) {
            view.setRotation(rotation);
        } else {
            view.setRotation(-rotation);
        }
    }

    private void setTvAlpha(float dx) {
        float alpha = dx / (sceWh * 0.5f);
        tvLike.setAlpha(alpha);
        tvNope.setAlpha(-alpha);
    }
    //左右滑动效果
    public void leftSliding(int iamgeId) {
        startAnimate(this, -(sceWh * 2));
        cardRotation(this, LEFT_DEFAULTX);
        setTvAlpha(LEFT_DEFAULTX + (newX - oldX));
        tvNope.setBackgroundResource(iamgeId);
    }

    public void rightSliding(int iamgeId) {
        startAnimate(this, (sceWh * 2));
        cardRotation(this, RIGHT_DEFAULTX);
        setTvAlpha(RIGHT_DEFAULTX + (newX - oldX));
        tvLike.setBackgroundResource(iamgeId);
    }

    /**
     * 设置卡片数据
     *
     * @param model
     */
    public void setModel(MainModel model) {
        if (!Utils.isEmpty(model)) {
            this.mainModel = model;
            if (!Utils.isEmpty(model.getTitle())) {
                tvName.setText(model.getTitle());
            }
            if (!Utils.isEmpty(model.getUsername())) {
                tvWork.setText(model.getUsername());
            }
        }
    }
}
