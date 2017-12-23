package com.blend.technology.utils;

import android.app.Activity;

import com.blend.technology.base.BasePresenter;
import com.blend.technology.base.IBaseActivity;
import com.blend.technology.bean.LoginIn;
import com.blend.technology.bean.LoginOut;
import com.blend.technology.model.IBaseModel;

import io.reactivex.Observable;


public interface LoginContract {
    abstract class LoginPresenter extends BasePresenter<LoginModel, LoginView> {
        public abstract void login(Activity activity, LoginIn loginIn);
    }

    interface LoginModel extends IBaseModel {
        Observable<LoginOut> login(LoginIn loginIn);
    }

    interface LoginView extends IBaseActivity {
        void loginSuess(LoginOut userInfoOut);
    }
}
