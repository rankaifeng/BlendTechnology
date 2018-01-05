package com.blend.technology.base;

import com.blend.technology.model.IBaseModel;


public abstract class BaseMVPCompatActivity<P extends BasePresenter, M extends IBaseModel>
        extends BaseCompatActivity implements IBaseActivity {

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
    protected void onDestroy() {
        super.onDestroy();
        if (mIPresenter != null) {
            mIPresenter.detchView();
        }
    }
}
