package com.blend.technology.login.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.blend.technology.base.BaseCompatActivity;
import com.blend.technology.base.BaseDisposable;
import com.blend.technology.bean.LoginIn;
import com.blend.technology.bean.LoginOut;
import com.blend.technology.login.model.LoginModelImp;
import com.blend.technology.login.contract.LoginContract;

import io.reactivex.Observable;


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
    public void login(Activity activity, LoginIn loginIn) {
        Observable<LoginOut> login = mModel.login(loginIn);
        mRxManager.register(new BaseDisposable<LoginOut>(activity) {
            @Override
            protected void requestSuccess(LoginOut out) {
                if (out.getMsg().equals("OK")) {
                    mView.loginSuess(out);
                } else {
                    ((BaseCompatActivity) activity).showToast(out.getMsg());
                    ((BaseCompatActivity) activity).hideProgress();
                }
            }
        }.requestDisposable(login));
    }
}
