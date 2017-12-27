package com.blend.technology.activity;

import android.support.design.widget.BottomNavigationView;
import android.view.KeyEvent;

import com.blend.technology.R;
import com.blend.technology.base.BaseCompatActivity;
import com.blend.technology.food.ui.FoodFragment;
import com.blend.technology.fragment.OneFragment;
import com.blend.technology.fragment.ThreeFragment;
import com.blend.technology.fragment.TwoFragment;
import com.blend.technology.helper.BottomNavigationViewHelper;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseCompatActivity {
    public static final int FOOD = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    @BindView(R.id.bottom_naviga)
    BottomNavigationView bottomNavigate;
    private long exitTime;
    private SupportFragment[] mFragments = new SupportFragment[4];

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        showActionTitleOnly("首页");
        BottomNavigationViewHelper.disableShiftMode(bottomNavigate);
        initFragment();
    }

    private void initFragment() {
        mFragments[FOOD] = FoodFragment.newInstance();
        mFragments[ONE] = OneFragment.newInstance();
        mFragments[TWO] = TwoFragment.newInstance();
        mFragments[THREE] = ThreeFragment.newInstance();
        loadMultipleRootFragment(R.id.fl_container, FOOD, mFragments[FOOD],
                mFragments[ONE],
                mFragments[TWO],
                mFragments[THREE]);
        bottomNavigate.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_item_food:
                    showHideFragment(mFragments[FOOD]);
                    break;
                case R.id.menu_item_gank_io:
                    showHideFragment(mFragments[ONE]);
                    break;
                case R.id.menu_item_movie:
                    showHideFragment(mFragments[TWO]);
                    break;
                case R.id.menu_item_book:
                    showHideFragment(mFragments[THREE]);
                    break;
            }
            return true;
        });
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
