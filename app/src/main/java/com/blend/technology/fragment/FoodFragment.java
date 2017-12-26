package com.blend.technology.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.blend.technology.R;
import com.blend.technology.adapter.FoodAdapter;
import com.blend.technology.base.BaseMVPCompatFragment;
import com.blend.technology.base.BasePresenter;
import com.blend.technology.bean.FoodOut;
import com.blend.technology.contract.FoodContract;
import com.blend.technology.presenter.FoodPresenter;
import com.blend.technology.utils.GlideImageLoader;
import com.blend.technology.utils.SpacesItemDecoration;
import com.blend.technology.widgets.WrapRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

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
    WrapRecyclerView mRecyclerView;
    private FoodAdapter foodAdapter;
    private List<FoodOut.Data> dataList = new ArrayList<>();
    private Banner mBanner;
    private List<String> imageViews = new ArrayList<>();
    private List<String> titileList = new ArrayList<>();

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
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        foodAdapter = new FoodAdapter(getActivity(), R.layout.fragment_food_item, dataList);
        mRecyclerView.setAdapter(foodAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        mRecyclerView.addItemDecoration(decoration);
        mIPresenter.getFoods(getActivity());
    }


    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return FoodPresenter.newInstance();
    }

    @Override
    public void requestSuesses(FoodOut foodOut) {
        dataList.addAll(foodOut.getArrayList());
        for (int i = 0; i < 4; i++) {
            imageViews.add(foodOut.getArrayList().get(i).getImgUrl());
            titileList.add(foodOut.getArrayList().get(i).getTitle());
        }
        // TODO: 2017/12/26 添加头部布局
        View headView = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_banner, null);
        mBanner = (Banner) headView.findViewById(R.id.banner);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mBanner.setBannerTitles(titileList);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setImages(imageViews);
        mBanner.start();
        mRecyclerView.addHeaderView(headView);
    }
}
