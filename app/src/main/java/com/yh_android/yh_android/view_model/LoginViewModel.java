package com.yh_android.yh_android.view_model;

import android.app.Application;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.PopupWindow;

import com.yh_android.yh_android.activity.TabBarActivity;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;

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

    private PopupWindow mPopWindow;
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
            //showPopupWindow();
            login();
        }
    });

    /*private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(LoginActivity.getContext()).inflate(R.layout.pop_login_alert, null);
        mPopWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv1 = (TextView)contentView.findViewById(R.id.pop_computer);
        TextView tv2 = (TextView)contentView.findViewById(R.id.pop_financial);
        TextView tv3 = (TextView)contentView.findViewById(R.id.pop_manage);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(LoginActivity.getContext()).inflate(R.layout.activity_login, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }*/

    /**
     * 网络模拟一个登陆操作
     **/
    private void login() {
        /*if (TextUtils.isEmpty(userName.get())) {
            //ToastUtils.showShort("请输入账号！");
            return;
        }
        if (TextUtils.isEmpty(password.get())) {
            //ToastUtils.showShort("请输入密码！");
            return;
        }*/
        startActivity(TabBarActivity.class);
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

