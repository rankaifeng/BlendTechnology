package com.blend.technology.food.model;

import android.support.annotation.NonNull;

import com.blend.technology.api.FoodApi;
import com.blend.technology.base.BaseModel;
import com.blend.technology.bean.FoodOut;
import com.blend.technology.helper.RetrofitCreateHelper;
import com.blend.technology.helper.RxHelper;
import com.blend.technology.food.contract.FoodContract;

import io.reactivex.Observable;


public class FoodImp extends BaseModel implements FoodContract.FoodModel {

    @NonNull
    public static FoodImp newInstance() {
        return new FoodImp();
    }

    @Override
    public Observable<FoodOut> getFoods(int page, int record) {
        return RetrofitCreateHelper.createApi(FoodApi.class).getFood(page, record)
                .compose(RxHelper.rxSchedulerHelper());
    }

    @Override
    public Observable<FoodOut> getFoodsDetail(String id) {
        return RetrofitCreateHelper.createApi(FoodApi.class).getFoodDetail(id)
                .compose(RxHelper.rxSchedulerHelper());
    }

}
