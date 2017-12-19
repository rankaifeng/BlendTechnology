package com.blend.technology.base;

import com.blend.technology.model.IBaseModel;

/**
 * Created by rankaifeng on 2017/12/16.
 */

public abstract class BaseMVPCompatActivity<P extends BasePresenter, M extends IBaseModel>
        extends BaseCompatActivity implements IBaseActivity {

    public P mIPresenter;
    public M mIModel;


    @Override
    protected void initView() {
        initV();
        mIPresenter = (P) initPresenter();
        if (mIPresenter != null) {
            mIModel = (M) mIPresenter.getModel();
            if (mIModel != null) {
                mIPresenter.attchView(mIModel, this);
            }
        }
    }

    public abstract void initV();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mIPresenter != null) {
            mIPresenter.detchView();
        }
    }
}
