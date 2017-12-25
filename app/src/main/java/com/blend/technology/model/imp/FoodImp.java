package com.blend.technology.model.imp;

import android.support.annotation.NonNull;

import com.blend.technology.api.FoodApi;
import com.blend.technology.base.BaseModel;
import com.blend.technology.bean.FoodOut;
import com.blend.technology.helper.RetrofitCreateHelper;
import com.blend.technology.helper.RxHelper;
import com.blend.technology.contract.FoodContract;

import io.reactivex.Observable;

/**
 * Created by rankaifeng on 2017/12/24.
 */

public class FoodImp extends BaseModel implements FoodContract.FoodModel {

    @NonNull
    public static FoodImp newInstance() {
        return new FoodImp();
    }

    @Override
    public Observable<FoodOut> getFoods() {
        return RetrofitCreateHelper.createApi(FoodApi.class).getFood()
                .compose(RxHelper.rxSchedulerHelper());
    }
}
