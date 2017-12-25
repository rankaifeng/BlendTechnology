package com.blend.technology.base;

import com.blend.technology.model.IBaseModel;

/**
 * Created by rankaifeng on 2017/12/16.
 */

public abstract class BaseMVPCompatFragment<P extends BasePresenter, M extends IBaseModel>
        extends BaseCompatFragment implements IBaseFragment {

    public P mIPresenter;
    public M mIModel;

    @Override
    protected void initView() {
        mIPresenter = (P) initPresenter();
        if (mIPresenter != null) {
            mIModel = (M) mIPresenter.getModel();
            if (mIModel != null) {
                mIPresenter.attchView(mIModel, this);
            }
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
