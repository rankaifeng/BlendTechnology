package com.blend.technology.api;

import com.blend.technology.bean.FoodOut;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by rankaifeng on 2017/12/24.
 */

public interface FoodApi {

    @GET("getFoods")
    Observable<FoodOut> getFood();
}
