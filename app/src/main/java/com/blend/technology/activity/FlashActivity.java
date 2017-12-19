package com.blend.technology.activity;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.blend.technology.R;
import com.blend.technology.base.BaseCompatActivity;
import com.blend.technology.helper.RxHelper;
import com.blend.technology.utils.AppUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * Created by rankaifeng on 2017/12/15.
 */

public class FlashActivity extends BaseCompatActivity {
    @BindView(R.id.tv_version_name)
    TextView tvVersionName;
    @BindView(R.id.tv_count_down)
    TextView tvCountDown;
    @BindView(R.id.lin_change)
    LinearLayout linChange;
    private static final int TIME = 4;
    private boolean mIsShow;

    @Override
    protected int getLayout() {
        return R.layout.activity_flash;
    }

    @Override
    protected void initView() {
        tvVersionName.setText(AppUtils.getAppversionName(this));

        linChange.setOnClickListener(v -> {
            mIsShow = true;
            startActivity(LoginActivity.class);
        });

        /**
         * 实现倒计时
         */
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
    }

}
