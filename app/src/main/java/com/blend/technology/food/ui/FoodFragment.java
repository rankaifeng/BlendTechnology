package com.blend.technology.food.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.blend.technology.R;
import com.blend.technology.adapter.FoodAdapter;
import com.blend.technology.adapter.PublicAdapter;
import com.blend.technology.base.BasePresenter;
import com.blend.technology.base.BaseRefreshFragment;
import com.blend.technology.bean.FoodOut;
import com.blend.technology.food.contract.FoodContract;
import com.blend.technology.food.presenter.FoodPresenter;
import com.blend.technology.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;


public class FoodFragment extends
        BaseRefreshFragment<FoodOut.Data, FoodContract.FoodPresenter, FoodContract.FoodModel>
        implements FoodContract.FoodView {
    private List<FoodOut.Data> dataList = new ArrayList<>();
    private List<String> imageViews = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    View headView;
    Banner mBanner;

    public static FoodFragment newInstance() {
        Bundle args = new Bundle();
        FoodFragment fragment = new FoodFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("InflateParams")
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        mXRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        headView = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_banner, null);
        mBanner = headView.findViewById(R.id.banner);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mXRecyclerView.addHeaderView(headView);
    }


    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return FoodPresenter.newInstance();
    }


    @Override
    public void requestSuccess(FoodOut foodOut) {
        dataList = foodOut.getArrayList();
        if (titleList.size() == 0 && imageViews.size() == 0) {
            for (int i = 0; i < 4; i++) {
                titleList.add(dataList.get(i).getTitle());
                imageViews.add(dataList.get(i).getImgUrl());
            }
        }
        mBanner.setBannerTitles(titleList);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setImages(imageViews);
        mBanner.start();
        requestSuccess(dataList);
    }


    @Override
    protected PublicAdapter<FoodOut.Data> getAdapter() {
        return new FoodAdapter(getActivity(), R.layout.fragment_food_item, dataList);
    }

    @Override
    protected void requestServiceData(int page, int record) {
        mIPresenter.getFoods(this, page, record);
    }

    @Override
    protected void showLoading() {
        showProgress("请求数据中......");
    }
}
