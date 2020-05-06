package com.zhao.ui_basic.ui.main.adaptor;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.zhao.ui_basic.R;

import java.util.List;

public class PhotoAdaptor extends PagerAdapter {
    private List<String> photoUrl;

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView ivPhoto = new ImageView(container.getContext());
        ivPhoto.setScaleType(ImageView.ScaleType.FIT_XY);//图片平铺
        ivPhoto.setImageResource(R.mipmap.s);
        /**if (!Utils.isListEmpty(photoUrl)) {
            Glide.with(container.getContext()).
                    load(photoUrl.get(position)).
                    into(ivPhoto);
        }*/
        container.addView(ivPhoto);
        return ivPhoto;
    }
    public PhotoAdaptor(List<String> in){
        this.photoUrl = in;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    //滑动时移除指向
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
