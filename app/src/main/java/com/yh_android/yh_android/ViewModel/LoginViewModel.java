package com.yh_android.yh_android.ViewModel;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.adapters.TextViewBindingAdapter;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * Created by goldze on 2017/7/17.
 */

public class LoginViewModel extends BaseViewModel {
    //用户名的绑定
    public ObservableField<String> userName = new ObservableField<>("");
    //密码的绑定
    public ObservableField<String> password = new ObservableField<>("");
    //用户名清除按钮的显示隐藏绑定
    public ObservableInt clearUsernameBtnVisibility = new ObservableInt(View.INVISIBLE);
    //密码清除按钮的显示隐藏绑定
    public ObservableInt clearPasswordBtnVisibility = new ObservableInt(View.INVISIBLE);

    /*private Handler loginHandler;
    private Runnable loginRunnable;*/

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    //清除用户名的点击事件, 逻辑从View层转换到ViewModel层
    public BindingCommand clearUserNameOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            userName.set("");
        }
    });

    //清除密码的点击事件, 逻辑从View层转换到ViewModel层
    public BindingCommand clearPasswordOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            password.set("");
        }
    });

    //用户名输入框是否有文字
    public BindingCommand onUsernameEmptyCommand = new BindingCommand(new BindingConsumer<String>() {
        @Override
        public void call(String input) {
            if (input.length() == 0) {
                clearUsernameBtnVisibility.set(View.INVISIBLE);
            } else {
                clearUsernameBtnVisibility.set(View.VISIBLE);
            }
        }
    });

    //密码输入框是否有文字
    public BindingCommand onPasswordEmptyCommand = new BindingCommand(new BindingConsumer<String>() {
        @Override
        public void call(String input) {
            if (input.length() == 0) {
                clearPasswordBtnVisibility.set(View.INVISIBLE);
            } else {
                clearPasswordBtnVisibility.set(View.VISIBLE);
            }
        }
    });

    //登录按钮的点击事件
    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            login();
        }
    });

    /**
     * 网络模拟一个登陆操作
     **/
    private void login() {
        if (TextUtils.isEmpty(userName.get())) {
            //ToastUtils.showShort("请输入账号！");
            return;
        }
        if (TextUtils.isEmpty(password.get())) {
            //ToastUtils.showShort("请输入密码！");
            return;
        }
        /*//showDialog();
        //进入DemoActivity页面
        loginRunnable = new Runnable() {
            @Override
            public void run() {
                //dismissDialog();
                //进入DemoActivity页面
                //startActivity(DemoActivity.class);
                //关闭页面
                finish();
            }
        };
        loginHandler = new Handler();
        loginHandler.postDelayed(loginRunnable, 3 * 1000);*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /*if (loginHandler != null) {
            //界面销毁时移除Runnable，实际网络请求时不需要手动取消请求，在请求时加入.compose(RxUtils.bindToLifecycle(context))绑定生命周期，在界面销毁时会自动取消网络访问，避免界面销毁时子线程还存在而引发的逻辑异常
            loginHandler.removeCallbacks(loginRunnable);
        }*/
    }
}

