package com.blend.technology.food.presenter;

import android.support.annotation.NonNull;

import com.blend.technology.base.BaseCompatActivity;
import com.blend.technology.base.BaseDisposable;
import com.blend.technology.bean.FoodOut;
import com.blend.technology.food.contract.FoodContract;
import com.blend.technology.food.model.FoodImp;
import com.blend.technology.food.ui.FoodDetailActivity;
import com.blend.technology.food.ui.FoodFragment;

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
    public void getFoods(FoodFragment fragment, int page, int record) {
        Observable<FoodOut> foods = mModel.getFoods(page, record);
        mRxManager.register(foods.subscribe(foodOut -> mView.requestSuccess(foodOut),
                fragment::requestFail));
    }

    @Override
    public void getFoodsDetail(FoodDetailActivity activity, String id) {
        Observable<FoodOut> foodsDetail = mModel.getFoodsDetail(id);
        mRxManager.register(new BaseDisposable<FoodOut>(activity) {
            @Override
            protected void requestSuccess(FoodOut out) {
                if (out.getMsg().equals("OK")) {
                    mView.requestSuccess(out);
                } else {
                    ((BaseCompatActivity) activity).showToast(out.getMsg());
                    ((BaseCompatActivity) activity).hideProgress();
                }
            }
        }.requestDisposable(foodsDetail));
    }
}
