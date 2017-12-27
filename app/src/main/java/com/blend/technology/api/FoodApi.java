package com.blend.technology.api;

import com.blend.technology.bean.FoodOut;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface FoodApi {

    @GET("getFoods")
    Observable<FoodOut> getFood();
}
