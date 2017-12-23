package com.blend.technology.api;

import com.blend.technology.bean.LoginIn;
import com.blend.technology.bean.LoginOut;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by rankaifeng on 2017/12/16.
 */

public interface UserInfoApi {
    @POST("login")
    Observable<LoginOut> login(@Body LoginIn loginIn);
}
