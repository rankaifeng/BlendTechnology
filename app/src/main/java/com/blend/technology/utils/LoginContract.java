package com.blend.technology.utils;

import android.app.Activity;

import com.blend.technology.base.BasePresenter;
import com.blend.technology.base.IBaseActivity;
import com.blend.technology.bean.UserInfoOut;
import com.blend.technology.model.IBaseModel;

import io.reactivex.Observable;


public interface LoginContract {
    abstract class LoginPresenter extends BasePresenter<LoginModel, LoginView> {
        public abstract void login(Activity activity);
    }

    interface LoginModel extends IBaseModel {
        Observable<UserInfoOut> login();
    }

    interface LoginView extends IBaseActivity {
        void loginSuess(UserInfoOut userInfoOut);
    }
}
