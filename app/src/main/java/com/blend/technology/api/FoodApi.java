package com.blend.technology.api;

import com.blend.technology.bean.FoodOut;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface FoodApi {

    @GET("getFoods")
    Observable<FoodOut> getFood(@Query("page") int page, @Query("record") int record);
}
