package com.blend.technology.activity;

import android.support.design.widget.BottomNavigationView;
import android.view.KeyEvent;

import com.blend.technology.R;
import com.blend.technology.base.BaseCompatActivity;
import com.blend.technology.helper.BottomNavigationViewHelper;

import butterknife.BindView;

public class MainActivity extends BaseCompatActivity {
    @BindView(R.id.bottom_naviga)
    BottomNavigationView bottomNavigate;
    private long exitTime;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        showActionTitleOnly("首页");
        BottomNavigationViewHelper.disableShiftMode(bottomNavigate);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                showToast("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
