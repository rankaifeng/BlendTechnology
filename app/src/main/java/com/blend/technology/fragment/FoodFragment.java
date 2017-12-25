package com.blend.technology.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.blend.technology.R;
import com.blend.technology.base.BaseMVPCompatFragment;
import com.blend.technology.base.BasePresenter;
import com.blend.technology.bean.FoodOut;
import com.blend.technology.presenter.FoodPresenter;
import com.blend.technology.contract.FoodContract;

/**
 * Created by rankaifeng on 2017/12/23.
 */

public class FoodFragment extends
        BaseMVPCompatFragment<FoodContract.FoodPresenter, FoodContract.FoodModel>
        implements FoodContract.FoodView {

    public static FoodFragment newInstance() {
        Bundle args = new Bundle();
        FoodFragment fragment = new FoodFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_food;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
//        mIPresenter.getFoods(getActivity());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return FoodPresenter.newInstance();
    }

    @Override
    public void requestSuesses(FoodOut foodOut) {
        Log.i("111", foodOut.toString());
    }
}
