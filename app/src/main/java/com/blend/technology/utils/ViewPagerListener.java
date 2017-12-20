package com.blend.technology.utils;

import android.support.v4.view.ViewPager;

/**
 * Created by rankaifeng on 2017/12/20.
 */

public abstract class ViewPagerListener implements ViewPager.OnPageChangeListener {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 滑动监听 目前只用这一个方法
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        pageSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public abstract void pageSelected(int position);


//        linChange.setOnClickListener(v -> {
//            mIsShow = true;
//            startActivity(LoginActivity.class);
//        });
    /**
     * 实现倒计时
     */
//        Observable.interval(1, TimeUnit.SECONDS)
//                .take(5)
//                .map(aLong -> TIME - aLong)
//                .compose(RxHelper.rxSchedulerHelper())
//                .subscribe(aLong -> {
//                    tvCountDown.setText(String.valueOf(aLong));
//                    if (aLong == 0) {
//                        if (!mIsShow)
//                            startActivity(LoginActivity.class);
//                        finish();
//                    }
//                });
}
