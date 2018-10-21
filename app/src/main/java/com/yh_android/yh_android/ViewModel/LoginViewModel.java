package com.yh_android.yh_android.ViewModel;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
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
    public ObservableInt clearBtnVisibility = new ObservableInt();
    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //密码开关观察者
        public ObservableBoolean pSwitchObservable = new ObservableBoolean(false);
    }

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
    //密码显示开关  (你可以尝试着狂按这个按钮,会发现它有防多次点击的功能)
    public BindingCommand passwordShowSwitchOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //让观察者的数据改变,逻辑从ViewModel层转到View层，在View层的监听则会被调用
            uc.pSwitchObservable.set(!uc.pSwitchObservable.get());
        }
    });
    //用户名输入框焦点改变的回调事件
    public BindingCommand<Boolean> onFocusChangeCommand = new BindingCommand<>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean hasFocus) {
            if (hasFocus) {
                clearBtnVisibility.set(View.VISIBLE);
            } else {
                clearBtnVisibility.set(View.INVISIBLE);
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

