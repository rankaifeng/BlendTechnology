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
import com.blend.technology.base.MyBanner;
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
    private List<FoodOut.Data> dataList   = new ArrayList<>();
    private List<String>       imageViews = new ArrayList<>();
    private List<String>       titleList  = new ArrayList<>();
    Banner mBanner;
    private FoodAdapter foodAdapter;

    public static FoodFragment newInstance() {
        Bundle args = new Bundle();
        FoodFragment fragment = new FoodFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
        mXRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        foodAdapter = new FoodAdapter(getActivity(), R.layout.fragment_food_item, dataList);
        View headView = getHeadView();
        mBanner = headView.findViewById(R.id.banner);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mXRecyclerView.addHeaderView(headView);
        foodAdapter.setOnItemClickListener((view1, holder, position) -> {
            intentActivity(position);
        });
        mBanner.setOnBannerListener(this::intentActivity);
    }

    @SuppressLint("InflateParams")
    private View getHeadView() {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_banner, null);
    }

    private void intentActivity(int position) {
        FoodOut.Data data = foodAdapter.mDatas.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("imgUrl", data.getImgUrl());
        bundle.putString("title", data.getTitle());
        bundle.putString("id", data.getId());
        startActivity(bundle, FoodDetailActivity.class);
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return FoodPresenter.newInstance();
    }


    @Override
    public void requestSuccess(FoodOut foodOut) {
        dataList = foodOut.getData();
        if (titleList.size() == 0 && imageViews.size() == 0) {
            for (int i = 0; i < 4; i++) {
                titleList.add(dataList.get(i).getTitle());
                imageViews.add(dataList.get(i).getImgUrl());
            }
        }
        MyBanner.Builder builder = new MyBanner.Builder();
        builder.mBanner(mBanner)
                .bannerTitls(titleList)
                .images(imageViews)
                .imageLoader(new GlideImageLoader())
                .build().start();
        requestSuccess(dataList);
    }


    @Override
    protected PublicAdapter<FoodOut.Data> getAdapter() {
        return foodAdapter;
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
