package com.blend.technology.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blend.technology.R;
import com.blend.technology.adapter.WelcomePagerAdapter;
import com.blend.technology.base.BaseCompatActivity;
import com.blend.technology.helper.RxHelper;
import com.blend.technology.login.ui.LoginActivity;
import com.blend.technology.utils.AppUtils;
import com.blend.technology.utils.ViewPagerListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;


public class FlashActivity extends BaseCompatActivity {
    @BindView(R.id.tv_version_name)
    TextView tvVersionName;
    @BindView(R.id.tv_count_down)
    TextView tvCountDown;
    @BindView(R.id.lin_change)
    LinearLayout linChange;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.lin_point)
    LinearLayout linPoint;
    @BindView(R.id.btn_go)
    Button btnGo;
    @BindView(R.id.img_flash)
    ImageView imgFlash;

    private List<View> viewList = new ArrayList<>();//存放图片资源的集合
    private int imgArray[];//存放图片资源的数组

    //实例化原点View
    private ImageView ivPoint;
    private ImageView ivPointArray[];//存放小圆点的数组

    private static final int TIME = 4;
    private boolean mIsShow;
    private int IMG[] = new int[]{R.mipmap.flash_one, R.mipmap.flash_two, R.mipmap.flash};

    @Override
    protected int getLayout() {
        return R.layout.activity_flash;
    }

    @Override
    protected void initView() {
        tvVersionName.setText(AppUtils.getAppversionName(this));
        btnGo.setOnClickListener(v -> startActivity(LoginActivity.class));
        linChange.setOnClickListener(v -> {
            mIsShow = true;
            startActivity(LoginActivity.class);
        });
        Random random = new Random();
        int i = random.nextInt(IMG.length);
        imgFlash.setBackgroundResource(IMG[i]);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);//初始化操作，参数传入0和1，即由透明度0变化到透明度为1
        imgFlash.startAnimation(alphaAnimation);//开始动画
        alphaAnimation.setFillAfter(true);//动画结束后保持状态
        alphaAnimation.setDuration(4000);//动画持续时间，单位为毫秒
        Observable.interval(1, TimeUnit.SECONDS)
                .take(5)
                .map(aLong -> TIME - aLong)
                .compose(RxHelper.rxSchedulerHelper())
                .subscribe(aLong -> {
                    tvCountDown.setText(String.valueOf(aLong));
                    if (aLong == 0) {
                        if (!mIsShow)
                            startActivity(LoginActivity.class);
                        finish();
                    }
                });
//        initViewPager();
//        initPoint();
        /**
         * 监听小圆点
         */
        mViewPager.setOnPageChangeListener(new ViewPagerListener() {
            @Override
            public void pageSelected(int position) {
                int length = imgArray.length;
                for (int i = 0; i < length; i++) {
                    ivPointArray[position].setBackgroundResource(R.drawable.point_s);
                    if (position != i) {
                        ivPointArray[i].setBackgroundResource(R.drawable.empty_point);
                    }
                    if (position == imgArray.length - 1) {
                        btnGo.setVisibility(View.VISIBLE);
                    } else {
                        btnGo.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    /**
     * 初始化viewpager
     */
    private void initViewPager() {
        imgArray = new int[]{R.mipmap.flash, R.mipmap.flash_one, R.mipmap.flash_two};
        LinearLayout.LayoutParams params = new LinearLayout
                .LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        for (int anImgArray : imgArray) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(anImgArray);
            viewList.add(imageView);
        }
        mViewPager.setAdapter(new WelcomePagerAdapter(viewList));
    }

    /**
     * 初始化小圆点
     */
    private void initPoint() {
        ivPointArray = new ImageView[viewList.size()];
        for (int i = 0; i < viewList.size(); i++) {
            ivPoint = new ImageView(this);
            ivPoint.setLayoutParams(new ViewGroup.LayoutParams(35, 35));
            ivPoint.setPadding(35, 0, 35, 0);
            ivPointArray[i] = ivPoint;
            if (i == 0) {
                ivPoint.setBackgroundResource(R.drawable.point_s);
            } else {
                ivPoint.setBackgroundResource(R.drawable.empty_point);
            }
            linPoint.addView(ivPointArray[i]);
        }
    }
}
