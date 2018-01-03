package com.blend.technology.food.ui;

import android.annotation.SuppressLint;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blend.technology.R;
import com.blend.technology.base.BaseCompatActivity;
import com.blend.technology.widgets.CompatNestedScrollView;
import com.bumptech.glide.Glide;

import butterknife.BindView;

import static com.blend.technology.utils.StatusBarUtils.getStatusBarHeight;

/**
 * unknown on 2018/1/3.
 */
@SuppressLint("Registered")
public class FoodDetailActivity extends BaseCompatActivity {
    @BindView(R.id.img_food_detail)
    ImageView imgFoodDetail;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.nsv_scrollview)
    CompatNestedScrollView nsvScrollview;
    @BindView(R.id.iv_toolbar_bg)
    ImageView ivToolbarBg;

    @Override
    protected int getLayout() {
        return R.layout.activity_food_detil;
    }

    @Override
    protected void initView() {
        String imgUrl = getIntent().getStringExtra("imgUrl");
        String title = getIntent().getStringExtra("title");
        String id = getIntent().getStringExtra("id");

        initTitleBar(toolbar, title);
        nsvScrollview.bindAlphaView(ivToolbarBg);
        Glide.with(this).load(imgUrl).into(imgFoodDetail);
        ivToolbarBg.setBackgroundResource(R.color.colorPrimary);
        int headerBgHeight = toolbar.getLayoutParams().height + getStatusBarHeight(this);
        // 使背景图向上移动到图片的最低端，保留（toolbar+状态栏）的高度
        // 实际上此时ivToolbarBg高度还是330dp，只是除了toolbar外，剩下部分是透明状态
        ViewGroup.MarginLayoutParams ivTitleHeadBgParams = (ViewGroup.MarginLayoutParams)
                ivToolbarBg.getLayoutParams();
        int marginTop = ivToolbarBg.getLayoutParams().height - headerBgHeight;
        ivTitleHeadBgParams.setMargins(0, -marginTop, 0, 0);
    }
}
