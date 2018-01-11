package com.blend.technology.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blend.technology.R;
import com.blend.technology.adapter.PublicAdapter;
import com.blend.technology.base.BaseCompatFragment;
import com.blend.technology.base.BaseViewHolder;
import com.blend.technology.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class Fragment1 extends BaseCompatFragment {
    @BindView(R.id.frg_two_recy)
    RecyclerView mRecyclerView;
    List<String> stringList = new ArrayList<>();

    public static Fragment1 newInstance() {
        Bundle args = new Bundle();
        Fragment1 fragment = new Fragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_two;
    }


    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        for (int i = 0; i < 40; i++) {
            stringList.add("测试数据" + i);
        }
        Myadapter myadapter = new Myadapter(getActivity(), R.layout.fragment_toobar, stringList);
        mRecyclerView.setAdapter(myadapter);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(10));
    }

    class Myadapter extends PublicAdapter {


        protected Myadapter(Context context, int layoutId, List datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(BaseViewHolder holder, int position) {
            holder.setText(R.id.tv_toobar, stringList.get(position));
        }
    }
}
