package com.blend.technology.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blend.technology.R;
import com.blend.technology.base.BaseCompatFragment;


public class ThreeFragment extends BaseCompatFragment {

    public static ThreeFragment newInstance() {
        Bundle args = new Bundle();
        ThreeFragment fragment = new ThreeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_three;
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }
}
