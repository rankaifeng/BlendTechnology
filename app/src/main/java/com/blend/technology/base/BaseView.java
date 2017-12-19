package com.blend.technology.base;

import android.support.annotation.NonNull;

/**
 * Created by rankaifeng on 2017/12/16.
 */

public interface BaseView {
    /**
     * 返回一个不为空的senter对象
     *
     * @return
     */
    @NonNull
    BasePresenter initPresenter();

}
