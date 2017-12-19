package com.blend.technology.base;

import android.support.annotation.NonNull;

import com.blend.technology.widgets.RxManager;

/**
 * Created by rankaifeng on 2017/12/16.
 */

public abstract class BasePresenter<M, V> {
    public V mView;
    public M mModel;
    protected RxManager mRxManager = new RxManager();

    /**
     * 返回presenter持有的model引用
     *
     * @return
     */
    public abstract M getModel();

    /**
     * 绑定model跟view
     *
     * @param v
     * @param m
     */
    public void attchView(@NonNull M m, @NonNull V v) {
        this.mView = v;
        this.mModel = m;
    }

    /**
     * 解除view跟model的绑定关系
     */
    public void detchView() {
        mRxManager.unSubscribe();
        this.mModel = null;
        this.mView = null;
    }
}
