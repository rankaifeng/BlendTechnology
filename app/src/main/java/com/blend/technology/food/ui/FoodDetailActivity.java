package com.blend.technology.food.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blend.technology.R;
import com.blend.technology.adapter.FoodDetailAdapter;
import com.blend.technology.base.BaseMVPCompatActivity;
import com.blend.technology.base.BasePresenter;
import com.blend.technology.bean.FoodOut;
import com.blend.technology.food.contract.FoodContract;
import com.blend.technology.food.presenter.FoodPresenter;
import com.blend.technology.utils.SpacesItemDecoration;
import com.blend.technology.widgets.CompatNestedScrollView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.blend.technology.utils.StatusBarUtils.getStatusBarHeight;

/**
 * unknown on 2018/1/3.
 */
@SuppressLint("Registered")
public class FoodDetailActivity extends
        BaseMVPCompatActivity<FoodContract.FoodPresenter, FoodContract.FoodModel>
        implements FoodContract.FoodView {
    @BindView(R.id.img_food_detail)
    ImageView              imgFoodDetail;
    @BindView(R.id.toolbar)
    Toolbar                toolbar;
    @BindView(R.id.nsv_scrollview)
    CompatNestedScrollView nsvScrollview;
    @BindView(R.id.iv_toolbar_bg)
    ImageView              ivToolbarBg;
    @BindView(R.id.tv_food_detail_tag)
    TextView               tvFoodDetailTag;
    @BindView(R.id.tv_food_detail_imtro)
    TextView               tvFoodDetailImtro;
    @BindView(R.id.tv_food_detail_burden)
    TextView               tvFoodDetailBurden;
    @BindView(R.id.frg_food_detail_recycler_steps)
    RecyclerView           mRecyclerView;
    @BindView(R.id.tv_food_detail_ingredients)
    TextView               tvFoodDetailIngredients;
    private String id, imgUrl, title;
    private FoodDetailAdapter adapter;
    private List<FoodOut.Steps> stepsList = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_food_detil;
    }

    @Override
    protected void initView() {
        super.initView();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            imgUrl = bundle.getString("imgUrl");
            title = bundle.getString("title");
            id = bundle.getString("id");
        }
        initTitleBar(toolbar, title);
        nsvScrollview.bindAlphaView(ivToolbarBg);
        Glide.with(this).load(imgUrl).into(imgFoodDetail);
        ivToolbarBg.setBackgroundResource(R.color.colorPrimary);
        int headerBgHeight = toolbar.getLayoutParams().height + getStatusBarHeight(this);
        // 使背景图向上移动到图端，保留（toolbar+状态栏）的高度
        // 实际上此时ivToolbarBg高度还是330dp，只是除了toolbar外，剩下部分是透明状态
        ViewGroup.MarginLayoutParams ivTitleHeadBgParams = (ViewGroup.MarginLayoutParams)
                ivToolbarBg.getLayoutParams();
        int marginTop = ivToolbarBg.getLayoutParams().height - headerBgHeight;
        ivTitleHeadBgParams.setMargins(0, -marginTop, 0, 0);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodDetailAdapter(this, R.layout.activity_food_detil_item, stepsList);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(this, LinearLayoutManager.VERTICAL,
                R.drawable.recy_item, 0));
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mIPresenter.getFoodsDetail(this, id);
    }

    @Override
    public BasePresenter initPresenter() {
        return FoodPresenter.newInstance();
    }

    @Override
    public void requestSuccess(FoodOut foodOut) {
        if (foodOut.getData().size() >= 1) {
            FoodOut.Data data = foodOut.getData().get(0);
            tvFoodDetailTag.setText(data.getTags());
            tvFoodDetailImtro.setText(data.getImtro());
            tvFoodDetailIngredients.setText(data.getIngredients());
            tvFoodDetailBurden.setText(data.getBurden());

            stepsList.addAll(data.getSteps());
            adapter.notifyDataSetChanged();
        }
    }
}