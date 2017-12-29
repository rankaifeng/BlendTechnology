package com.blend.technology.utils;

import android.view.View;

import com.jcodecraeer.xrecyclerview.CustomFooterViewCallBack;

/**
 * unknown on 2017/12/29.
 */
public abstract class FooterViewError implements CustomFooterViewCallBack {


    @Override
    public void onLoadingMore(View yourFooterView) {
        loadingMore(yourFooterView);
    }

    @Override
    public void onLoadMoreComplete(View yourFooterView) {
        loadMoreComplete(yourFooterView);
    }

    @Override
    public void onSetNoMore(View yourFooterView, boolean noMore) {

    }

    protected abstract void loadingMore(View footerView);

    protected abstract void loadMoreComplete(View footerView);
}
