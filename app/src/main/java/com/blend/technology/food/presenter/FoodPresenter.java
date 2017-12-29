package com.blend.technology.food.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.blend.technology.base.BaseCompatActivity;
import com.blend.technology.base.BaseDisposable;
import com.blend.technology.bean.FoodOut;
import com.blend.technology.food.contract.FoodContract;
import com.blend.technology.food.model.FoodImp;

import io.reactivex.Observable;


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
    public void getFoods(Activity activity,int page, int record) {
        Observable<FoodOut> foods = mModel.getFoods(page, record);
        mRxManager.register(new BaseDisposable<FoodOut>(activity) {
            @Override
            protected void requestSuccess(FoodOut foodOut) {
                if (foodOut.getMsg().equals("OK")) {
                    mView.requestSuccess(foodOut);
                } else {
                    ((BaseCompatActivity) activity).showToast(foodOut.getMsg());
                    ((BaseCompatActivity) activity).hideProgress();
                }
            }
        }.requestDisposable(foods));
    }
}
