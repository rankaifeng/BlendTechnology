package com.blend.technology.api;

import com.blend.technology.bean.LoginIn;
import com.blend.technology.bean.LoginOut;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface UserInfoApi {
    @POST("login")
    Observable<LoginOut> login(@Body LoginIn loginIn);
}
