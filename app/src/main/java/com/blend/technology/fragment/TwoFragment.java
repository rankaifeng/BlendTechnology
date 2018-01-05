package com.blend.technology.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blend.technology.R;
import com.blend.technology.base.BaseCompatFragment;


public class TwoFragment extends BaseCompatFragment {

    public static TwoFragment newInstance() {
        Bundle args = new Bundle();
        TwoFragment fragment = new TwoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_two;
    }


    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
    }
}
