package com.blend.technology.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.blend.technology.base.BaseDisposable;
import com.blend.technology.bean.UserInfoOut;
import com.blend.technology.model.imp.LoginModelImp;
import com.blend.technology.utils.LoginContract;

import io.reactivex.Observable;

/**
 * Created by rankaifeng on 2017/12/16.
 */

public class LoginPresenter extends LoginContract.LoginPresenter {

    @NonNull
    public static LoginPresenter newInstance() {
        return new LoginPresenter();
    }


    @Override
    public LoginContract.LoginModel getModel() {
        return LoginModelImp.newInstance();
    }

    @Override
    public void login(Activity activity) {
        Observable<UserInfoOut> login = mModel.login();
        mRxManager.register(new BaseDisposable<UserInfoOut>(activity) {
            @Override
            protected void requestSuccess(UserInfoOut out) {
                mView.loginSuess(out);
            }
        }.requestDisposable(login));
    }
}
