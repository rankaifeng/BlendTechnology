package com.blend.technology.contract;

import android.app.Activity;

import com.blend.technology.base.BasePresenter;
import com.blend.technology.base.IBaseActivity;
import com.blend.technology.bean.FoodOut;
import com.blend.technology.model.IBaseModel;

import io.reactivex.Observable;


public interface FoodContract {
    abstract class FoodPresenter extends BasePresenter<FoodModel, FoodView> {
        public abstract void getFoods(Activity activity);
    }

    interface FoodModel extends IBaseModel {
        Observable<FoodOut> getFoods();
    }

    interface FoodView extends IBaseActivity {
        void requestSuesses(FoodOut foodOut);
    }
}
