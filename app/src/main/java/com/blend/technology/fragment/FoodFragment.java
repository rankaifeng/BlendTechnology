package com.blend.technology.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class FoodFragment extends
        BaseMVPCompatFragment<FoodContract.FoodPresenter, FoodContract.FoodModel>
        implements FoodContract.FoodView {
    @BindView(R.id.fra_recycler)
    XRecyclerView mRecyclerView;
    private FoodAdapter foodAdapter;
    private List<FoodOut.Data> dataList = new ArrayList<>();
    private List<FoodOut.Data> headDataList = new ArrayList<>();
    private List<String> imageViews = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

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
        SpacesItemDecoration decoration = new SpacesItemDecoration(5);
        mRecyclerView.addItemDecoration(decoration);
        mIPresenter.getFoods(getActivity());
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                getActivity().getWindow().getDecorView().postDelayed(() ->
                        mRecyclerView.refreshComplete(), 2000);
            }

            @Override
            public void onLoadMore() {
                getActivity().getWindow().getDecorView().postDelayed(() ->
                        mIPresenter.getFoods(getActivity()), 3000);
            }
        });
    }


    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return FoodPresenter.newInstance();
    }

    @Override
    public void requestsuesses(FoodOut foodOut) {
        mRecyclerView.loadMoreComplete();
        if (imageViews.size() == 0 && titleList.size() == 0 && headDataList.size() == 0) {
            for (int i = 0; i < 3; i++) {
                imageViews.add(foodOut.getArrayList().get(i).getImgUrl());
                titleList.add(foodOut.getArrayList().get(i).getTitle());
                headDataList.add(foodOut.getArrayList().get(i));
            }
            showHeadView();
        }
        dataList.addAll(foodOut.getArrayList());
        mRecyclerView.notifyItemInserted(dataList.size());
    }

    private void showHeadView() {
        // TODO: 2017/12/26 添加头部布局
        View headView = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_banner, null);
        Banner mBanner = (Banner) headView.findViewById(R.id.banner);
        RecyclerView fraHeadRecy = (RecyclerView) headView.findViewById(R.id.fra_head_recy);
        fraHeadRecy.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        foodAdapter = new FoodAdapter(getActivity(), R.layout.fragment_food_item, headDataList);
        fraHeadRecy.setAdapter(foodAdapter);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mBanner.setBannerTitles(titleList);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setImages(imageViews);
        mBanner.start();
        mRecyclerView.addHeaderView(headView);
    }
}
