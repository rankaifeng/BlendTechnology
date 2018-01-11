package com.blend.technology.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.blend.technology.R;
import com.blend.technology.adapter.FragmentAdapter;
import com.blend.technology.base.BaseCompatFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class OneFragment extends BaseCompatFragment {
    @BindView(R.id.toolbar)
    Toolbar   toolbar;
    @BindView(R.id.tl_tabs)
    TabLayout tlTabs;
    @BindView(R.id.vp_fragment)
    ViewPager vpFragment;
    String[] tabs = new String[]{"测试1", "测试2", "测试3"};
    private List<Fragment> fragments;

    public static OneFragment newInstance() {
        Bundle args = new Bundle();
        OneFragment fragment = new OneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragments = new ArrayList<>();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_one;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        toolbar.setTitle("任务");
        //实际上3个子布局是一样的，都只有一个recycleview，但是为了后续升级拓展，子fragment都是使用单独的布局文件
        for (int i = 0; i < tabs.length; i++) {
            tlTabs.addTab(tlTabs.newTab().setText(tabs[i]));
            switch (i) {
                case 0:
                    fragments.add(Fragment1.newInstance());
                    break;
                case 1:
                    fragments.add(Fragment2.newInstance());
                    break;
                case 2:
                    fragments.add(Fragment3.newInstance());
                    break;
            }
        }
        vpFragment.setAdapter(new FragmentAdapter(getChildFragmentManager(), fragments));
        //要设置到viewpager.setAdapter后才起作用
        vpFragment.setCurrentItem(0);
        tlTabs.setupWithViewPager(vpFragment);
        tlTabs.setVerticalScrollbarPosition(0);
        //tlTabs.setupWithViewPager方法内部会remove所有的tabs
        // ，这里重新设置一遍tabs的text，否则tabs的text不显示
        for (int i = 0; i < tabs.length; i++) {
            tlTabs.getTabAt(i).setText(tabs[i]);
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }
}
