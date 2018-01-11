package com.blend.technology.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blend.technology.widgets.MyDiglog;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;


public abstract class BaseCompatFragment extends SupportFragment {

    protected String   TAG;
    protected Context  mContext;
    protected Activity mActivity;
    private   Unbinder binder;
    private Toast toast = null;
    Dialog progressDlg;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        if (getLayoutView() != null) {
            return getLayoutView();
        } else {
            //            return inflater.inflate(getLayoutId(), null);
            return inflater.inflate(getLayoutId(), container, false);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TAG = getClass().getSimpleName();
        binder = ButterKnife.bind(this, view);
        getBundle(getArguments());
        initView(view, savedInstanceState);
        //        initUI(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (binder != null)
            binder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @LayoutRes
    public abstract int getLayoutId();

    public View getLayoutView() {
        return null;
    }

    /**
     * 得到Activity传进来的值
     */
    public void getBundle(Bundle bundle) {
    }

    /**
     * 初始化UI
     */
    //    public abstract void initUI(View view, @Nullable Bundle savedInstanceState);

    /**
     * 处理回退事件
     *
     * @return true 事件已消费
     * <p>
     * false 事件向上传递
     */
    @Override
    public boolean onBackPressedSupport() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            //如果当前存在fragment>1，当前fragment出栈
            pop();
        } else {
            //已经退栈到root fragment，交由Activity处理回退事件
            return false;
        }
        return true;
    }

    protected abstract void initView(View view, @Nullable Bundle savedInstanceState);

    public void showToast(String msg) {
        showToast(msg, true);
    }

    public void showToast(String msg, boolean status) {
        if (status) {
            toast = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        if (!status) {
            toast.cancel();
        }
    }

    /**
     * 显示loading
     *
     * @param msg 自定义消息
     */
    public void showProgress(String msg) {
        progressDlg = MyDiglog.createLoadingDialog(getActivity(), msg, false);
    }

    /**
     * 隐藏loading
     */
    public void hideProgress() {
        if (progressDlg != null && progressDlg.isShowing()) {
            progressDlg.dismiss();
        }
    }

    public void startActivity(Bundle mBundle, Class<?> mClass) {
        Intent intent = new Intent(getActivity(), mClass);
        intent.putExtras(mBundle);
        startActivity(intent);
    }
}
