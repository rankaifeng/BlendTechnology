package com.blend.technology.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.blend.technology.R;
import com.blend.technology.adapter.FoodAdapter;
import com.blend.technology.base.BaseMVPCompatFragment;
import com.blend.technology.base.BasePresenter;
import com.blend.technology.bean.FoodOut;
import com.blend.technology.contract.FoodContract;
import com.blend.technology.presenter.FoodPresenter;
import com.blend.technology.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by rankaifeng on 2017/12/23.
 */

public class FoodFragment extends
        BaseMVPCompatFragment<FoodContract.FoodPresenter, FoodContract.FoodModel>
        implements FoodContract.FoodView {
    @BindView(R.id.fra_recycler)
    RecyclerView mRecyclerView;
    private FoodAdapter foodAdapter;
    private List<FoodOut.Data> dataList = new ArrayList<>();

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
        mIPresenter.getFoods(getActivity());
        foodAdapter = new FoodAdapter(getActivity(), R.layout.fragment_food_item, dataList);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(foodAdapter);

        //设置item之间的间隔
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        mRecyclerView.addItemDecoration(decoration);
    }


    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return FoodPresenter.newInstance();
    }

    @Override
    public void requestSuesses(FoodOut foodOut) {
        dataList.addAll(foodOut.getArrayList());
        foodAdapter.notifyDataSetChanged();
    }
}
