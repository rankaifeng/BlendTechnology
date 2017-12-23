package com.blend.technology.model.imp;

import android.support.annotation.NonNull;

import com.blend.technology.api.UserInfoApi;
import com.blend.technology.base.BaseModel;
import com.blend.technology.bean.LoginIn;
import com.blend.technology.bean.LoginOut;
import com.blend.technology.helper.RetrofitCreateHelper;
import com.blend.technology.helper.RxHelper;
import com.blend.technology.utils.LoginContract;

import io.reactivex.Observable;

/**
 * Created by rankaifeng on 2017/12/18.
 */

public class LoginModelImp extends BaseModel implements LoginContract.LoginModel {

    @NonNull
    public static LoginModelImp newInstance() {
        return new LoginModelImp();
    }

    @Override
    public Observable<LoginOut> login(LoginIn loginIn) {
        return RetrofitCreateHelper.createApi(UserInfoApi.class).login(loginIn)
                .compose(RxHelper.<LoginOut>rxSchedulerHelper());
    }
}
