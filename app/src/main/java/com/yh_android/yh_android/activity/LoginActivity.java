package com.yh_android.yh_android.activity;

import android.arch.lifecycle.*;
import android.content.Context;
import android.os.Bundle;

import com.yh_android.yh_android.BR;
import com.yh_android.yh_android.R;
import com.yh_android.yh_android.databinding.ActivityLoginBinding;
import com.yh_android.yh_android.view_model.LoginViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * 一个MVVM模式的登陆界面
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    protected static Context context;
    //ActivityLoginBinding类是databinding框架自定生成的,对应activity_login.xml
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        /*binding.tvUsername.setTypeface(ResourcesCompat.getFont(this, R.font.yhfont));
        binding.tvPassword.setTypeface(ResourcesCompat.getFont(this, R.font.yhfont));
        binding.etUsername.setTypeface(ResourcesCompat.getFont(this, R.font.yhfont));
        binding.etPassword.setTypeface(ResourcesCompat.getFont(this, R.font.yhfont));
        binding.btnLogin.setTypeface(ResourcesCompat.getFont(this, R.font.yhfont));*/
        context = getApplicationContext();
        return BR.viewModel;
    }

    @Override
    public LoginViewModel initViewModel() {
        //View持有ViewModel的引用，如果没有特殊业务处理，这个方法可以不重写
        return ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    public static Context getContext(){
        return context;
    }
}
