package com.blend.technology.api;

import com.blend.technology.bean.FoodOut;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface FoodApi {
    /**
     * 获取美食列表
     *
     * @param page
     * @param record
     * @return
     */
    @GET("getFoods")
    Observable<FoodOut> getFood(@Query("page") int page, @Query("record") int record);

    /**
     * 获取美食详情
     *
     * @param id
     * @return
     */
    @GET("getFoodsDetail")
    Observable<FoodOut> getFoodDetail(@Query("id") String id);
}
