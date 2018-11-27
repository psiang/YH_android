package com.yh_android.yh_android.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;

import com.yh_android.yh_android.BR;
import com.yh_android.yh_android.R;
import com.yh_android.yh_android.databinding.ActivityTabBarBinding;
import com.yh_android.yh_android.fragment.TabBar1Fragment;
import com.yh_android.yh_android.fragment.TabBar2Fragment;
import com.yh_android.yh_android.fragment.TabBar3Fragment;
import com.yh_android.yh_android.fragment.TabBar4Fragment;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.item.NormalItemView;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

/**
 * 底部tab按钮
 * Created by siang on 2018/11/26.
 */

public class TabBarActivity extends BaseActivity<ActivityTabBarBinding, BaseViewModel> {
    private List<Fragment> mFragments;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_tab_bar;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        //初始化Fragment
        initFragment();
        //初始化底部Button
        initBottomTab();
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new TabBar1Fragment());
        mFragments.add(new TabBar2Fragment());
        mFragments.add(new TabBar3Fragment());
        mFragments.add(new TabBar4Fragment());
        //默认选中第一个
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        binding.textviewName.setText(getResources().getString(R.string.home));
        transaction.add(R.id.frameLayout, mFragments.get(0));
        transaction.commitAllowingStateLoss();
    }

    private void initBottomTab() {
        NavigationController navigationController = binding.pagerBottomTab.custom()
                .addItem(newItem(R.mipmap.tabbar_home, R.mipmap.tabbar_home_selected, this.getBaseContext().getString(R.string.home)))
                .addItem(newItem(R.mipmap.tabbar_passagecenter, R.mipmap.tabbar_passagecenter_selected, this.getBaseContext().getString(R.string.article)))
                .addItem(newItem(R.mipmap.tabbar_discover, R.mipmap.tabbar_discover_selected, this.getBaseContext().getString(R.string.community)))
                .addItem(newItem(R.mipmap.tabbar_profile, R.mipmap.tabbar_profile_selected, this.getBaseContext().getString(R.string.chat)))
                .build();
        //底部按钮的点击事件监听
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (index == 0)
                    binding.textviewName.setText(getResources().getString(R.string.home));
                else
                if (index == 1)
                    binding.textviewName.setText(getResources().getString(R.string.article));
                else
                if (index == 2)
                    binding.textviewName.setText(getResources().getString(R.string.community));
                else
                if (index == 3)
                    binding.textviewName.setText(getResources().getString(R.string.chat));
                transaction.replace(R.id.frameLayout, mFragments.get(index));
                transaction.commitAllowingStateLoss();
            }

            @Override
            public void onRepeat(int index) {
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        NormalItemView normalItemView = new NormalItemView(this);
        normalItemView.initialize(drawable, checkedDrawable, text);
        normalItemView.setTextDefaultColor(fromIntToARGB(ContextCompat.getColor(this, R.color.YHColor_Gray)));
        normalItemView.setTextCheckedColor(fromIntToARGB(ContextCompat.getColor(this, R.color.YHColor_tabBar_DarkRed)));
        return normalItemView;
    }

    private static int fromIntToARGB(int colorInt) {
        int red = (colorInt & 0xff0000) >> 16;
        int green = (colorInt & 0x00ff00) >> 8;
        int blue = (colorInt & 0x0000ff);
        int color = Color.rgb(red, green, blue);
        return color;
    }
}
