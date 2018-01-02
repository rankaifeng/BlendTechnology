package com.blend.technology.food.contract;

import com.blend.technology.base.BasePresenter;
import com.blend.technology.base.IBaseActivity;
import com.blend.technology.bean.FoodOut;
import com.blend.technology.food.ui.FoodFragment;
import com.blend.technology.model.IBaseModel;

import io.reactivex.Observable;


public interface FoodContract {
    abstract class FoodPresenter extends BasePresenter<FoodModel, FoodView> {
        public abstract void getFoods(FoodFragment fragment, int page, int record);
    }

    interface FoodModel extends IBaseModel {
        Observable<FoodOut> getFoods(int page, int record);
    }

    interface FoodView extends IBaseActivity {
        /**
         * 请求成功
         *
         * @param foodOut
         */
        void requestSuccess(FoodOut foodOut);

    }
}
