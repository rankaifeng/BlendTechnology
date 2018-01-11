package com.blend.technology.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blend.technology.R;
import com.blend.technology.base.BaseCompatFragment;


public class Fragment2 extends BaseCompatFragment {

    public static Fragment2 newInstance() {
        Bundle args = new Bundle();
        Fragment2 fragment = new Fragment2();
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
