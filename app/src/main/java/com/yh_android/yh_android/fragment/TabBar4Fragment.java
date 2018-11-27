package com.yh_android.yh_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.yh_android.yh_android.BR;
import com.yh_android.yh_android.R;

import me.goldze.mvvmhabit.base.BaseFragment;

/**
 * Created by siang on 2018/11/26.
 */

public class TabBar4Fragment extends BaseFragment {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_tab_bar_4;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
