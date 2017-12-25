package com.blend.technology.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.blend.technology.base.BaseCompatActivity;
import com.blend.technology.base.BaseDisposable;
import com.blend.technology.bean.FoodOut;
import com.blend.technology.contract.FoodContract;
import com.blend.technology.model.imp.FoodImp;

import io.reactivex.Observable;

/**
 * Created by rankaifeng on 2017/12/16.
 */

public class FoodPresenter extends FoodContract.FoodPresenter {

    @NonNull
    public static FoodPresenter newInstance() {
        return new FoodPresenter();
    }


    @Override
    public FoodContract.FoodModel getModel() {
        return FoodImp.newInstance();
    }

    @Override
    public void getFoods(Activity activity) {
        Observable<FoodOut> foods = mModel.getFoods();
        mRxManager.register(new BaseDisposable<FoodOut>(activity) {
            @Override
            protected void requestSuccess(FoodOut foodOut) {
                if (foodOut.getMsg().equals("OK")) {
                    mView.requestSuesses(foodOut);
                } else {
                    ((BaseCompatActivity) activity).showToast(foodOut.getMsg());
                    ((BaseCompatActivity) activity).hideProgress();
                }
            }
        }.requestDisposable(foods));
    }
}
