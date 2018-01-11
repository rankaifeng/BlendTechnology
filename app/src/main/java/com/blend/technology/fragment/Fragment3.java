package com.blend.technology.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blend.technology.R;
import com.blend.technology.base.BaseCompatFragment;


public class Fragment3 extends BaseCompatFragment {

    public static Fragment3 newInstance() {
        Bundle args = new Bundle();
        Fragment3 fragment = new Fragment3();
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
