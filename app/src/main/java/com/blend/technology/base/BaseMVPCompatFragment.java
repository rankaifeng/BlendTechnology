package com.blend.technology.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blend.technology.model.IBaseModel;


public abstract class BaseMVPCompatFragment<P extends BasePresenter, M extends IBaseModel>
        extends BaseCompatFragment implements IBaseFragment {

    public P mIPresenter;
    public M mIModel;


    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        mIPresenter = (P) initPresenter();
        if (mIPresenter != null) {
            mIModel = (M) mIPresenter.getModel();
            if (mIModel != null) mIPresenter.attchView(mIModel, this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mIPresenter != null) {
            mIPresenter.detchView();
        }
    }
}
