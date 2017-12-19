package com.blend.technology.api;

import com.blend.technology.bean.UserInfoOut;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by rankaifeng on 2017/12/16.
 */

public interface UserInfoApi {
    @GET("MyWeb/selectAll")
    Observable<UserInfoOut> login();
}
