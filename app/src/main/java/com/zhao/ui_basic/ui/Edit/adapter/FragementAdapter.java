package com.zhao.ui_basic.ui.Edit.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.zhao.ui_basic.ui.base.BaseFragment;

import java.util.List;

public class FragementAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;
    public FragementAdapter(@NonNull FragmentManager fm,List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public FragementAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
