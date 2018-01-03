package com.blend.technology.base;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blend.technology.R;
import com.blend.technology.utils.StringUtils;
import com.blend.technology.utils.ViewUtil;
import com.blend.technology.widgets.MyDiglog;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;


public abstract class BaseCompatActivity extends SupportActivity {
    private static final String CLASS_NAME = "FlashActivity";
    private static final String CLASS_NAME_LOGIN = "LoginActivity";
    private static final String CLASS_NAME_FOOD = "FoodDetailActivity";
    private TextView mActionTitle;
    private ImageView mActionLeftImg;
    private TextView mActionLeftTxt;
    private ImageView mActionRightImg;
    private TextView mActionRightTxt;
    private TextView mnumCount;
    //左侧是否可以返回
    protected boolean mCanGoBack = true;
    protected boolean isShow = false;
    Dialog progressDlg;
    Toast toast = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changTitleBar();
        setContentView(getLayout());
        ButterKnife.bind(this);
        if (isShow) {
            defaultActionBar();
        }
        initView();
    }

    /**
     * 显示loading
     *
     * @param msg 自定义消息
     */
    public void showProgress(String msg) {
        if (!BaseCompatActivity.this.isFinishing()) {
            progressDlg = MyDiglog.createLoadingDialog(BaseCompatActivity.this, msg, false);
        }
    }

    protected void initTitleBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }

    public void showToast(String msg) {
        showToast(msg, true);
    }

    public void showToast(String msg, boolean status) {
        if (status) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        if (!status) {
            toast.cancel();
        }
    }

    /**
     * 隐藏loading
     */
    public void hideProgress() {
        if (progressDlg != null && progressDlg.isShowing()) {
            progressDlg.dismiss();
        }
    }

    protected void changTitleBar() {
        String childClassName = getChildClassName();
        switch (childClassName) {
            case CLASS_NAME:
            case CLASS_NAME_LOGIN:
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
                ViewUtil.initSystemBar(this, Color.TRANSPARENT);
                break;
            case CLASS_NAME_FOOD:
                ViewUtil.setBarColor(this, Color.TRANSPARENT);
                break;
            default:
                isShow = true;
                ViewUtil.initSystemBar(this, R.color.colorPrimary);
                break;
        }
    }


    public void defaultActionBar() {
        View customerActionBar = getLayoutInflater().inflate(R.layout.baseactivity_bar_layout, null);
        mActionTitle = customerActionBar.findViewById(R.id.txt_title);
        RelativeLayout mActionLeft = customerActionBar.findViewById(R.id.relative_left);
        mActionLeftTxt = customerActionBar.findViewById(R.id.txt_left);
        mActionLeftImg = customerActionBar.findViewById(R.id.img_back);
        RelativeLayout mActionRight = customerActionBar.findViewById(R.id.relative_right);
        mActionRightImg = customerActionBar.findViewById(R.id.img_right);
        mActionRightTxt = customerActionBar.findViewById(R.id.txt_right);
        mnumCount = customerActionBar.findViewById(R.id.tv_numcount);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.LEFT);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(customerActionBar, params);
        mActionLeft.setOnClickListener(v -> {
            if (mActionLeftImg.getVisibility() == View.GONE
                    && mActionLeftTxt.getVisibility() == View.GONE) {
                return;
            }
            if (mCanGoBack) {
                onBackPressed();
            } else {
                if (mActionLeftImg.getVisibility() == View.GONE) {
                    onActionLeftTextClick();
                } else if (mActionLeftTxt.getVisibility() == View.GONE) {
                    onActionLeftImageClick();
                } else {
                    onActionLeftImageClick();
                }
            }
        });

        /**
         * 右侧事件响应
         */
        mActionRight.setOnClickListener(v -> {
            if (mActionRightImg.getVisibility() == View.VISIBLE) {
                onActionRightImageClick();
            } else if (mActionRightTxt.getVisibility() == View.VISIBLE) {
                onActionRightTextClick();
            }
        });
    }

    private void toggleActionLeftImg(boolean toggle) {
        if (mActionLeftImg != null) {
            if (toggle) {
                mActionLeftImg.setVisibility(View.VISIBLE);
            } else {
                mActionLeftImg.setVisibility(View.GONE);
            }
        }
    }

    private void toggleActionLeftText(boolean toggle) {
        if (mActionLeftTxt != null) {
            if (toggle) {
                mActionLeftTxt.setVisibility(View.VISIBLE);
            } else {
                mActionLeftTxt.setVisibility(View.GONE);
            }
        }
    }


    private void toggleActionRightImg(boolean toggle) {
        if (mActionRightImg != null) {
            if (toggle) {
                mActionRightImg.setVisibility(View.VISIBLE);
            } else {
                mActionRightImg.setVisibility(View.GONE);
            }
        }
    }


    private void toggleActionRightImg2(boolean toggle) {
        if (mActionRightImg != null) {
            if (toggle) {
                mActionRightImg.setVisibility(View.VISIBLE);
                mnumCount.setVisibility(View.VISIBLE);
            } else {
                mActionRightImg.setVisibility(View.GONE);
                mnumCount.setVisibility(View.GONE);
            }
        }
    }

    //右侧count
    private void toggleActionRightImgCount(boolean toggle) {
        String c = mnumCount.getText().toString();
        if (!"0".equals(c)) {
            if (toggle) {
                mActionRightImg.setVisibility(View.VISIBLE);
                mnumCount.setVisibility(View.VISIBLE);
            }
        } else if ("0".equals(c)) {
            mActionRightImg.setVisibility(View.VISIBLE);
            mnumCount.setVisibility(View.GONE);
        }
    }


    private void toggleActionRightText(boolean toggle) {
        if (mActionRightTxt != null) {
            if (toggle) {
                mActionRightTxt.setVisibility(View.VISIBLE);
            } else {
                mActionRightTxt.setVisibility(View.GONE);
            }
        }
    }

    private void toggleActionRightTextDrawableLeft(int id) {
        if (mActionRightTxt != null) {
            mActionRightTxt.setCompoundDrawablesWithIntrinsicBounds(id, // left
                    0, // top
                    0, // right
                    0);// bo
            mActionRightTxt.setCompoundDrawablePadding(3);
        }
    }

    /**
     * 设置ActionBar 居中title
     *
     * @param title
     */
    public void setActionTitle(String title) {
        if (StringUtils.isNotEmpty(title) && mActionTitle != null) {
            mActionTitle.setText(title);
        }
    }

    /**
     * 设置ActionBar 右侧count
     *
     * @param numcount
     */
    public void setActionRightCount(String numcount) {
        if (StringUtils.isNotEmpty(numcount) && mActionTitle != null) {
            mnumCount.setText(numcount);
        }
    }

    /**
     * 设置ActionBar 左侧图片资源
     *
     * @param id
     */
    public void setActionLeftImg(int id) {
        if (mActionLeftImg != null) {
            mActionLeftImg.setImageResource(id);
        }
    }


    /**
     * 设置ActionBar 图片右侧资源
     *
     * @param id
     */
    public void setActionRightImg(int id) {
        if (mActionRightImg != null) {
            mActionRightImg.setImageResource(id);
        }
    }

    /**
     * 设置ActionBar 右侧文字资源
     *
     * @param id
     */
    public void setActionRightTxt(int id) {
        if (mActionRightTxt != null) {
            mActionRightTxt.setText(getString(id));
        }
    }

    /**
     * 设置ActionBar 左侧文字资源
     *
     * @param id
     */
    public void setActionLeftTxt(int id) {
        if (mActionLeftTxt != null) {
            mActionLeftTxt.setText(getString(id));
        }
    }


    /**
     * 只显示标题
     * 不显示左右两侧图标
     *
     * @param title
     */
    public void showActionTitleOnly(String title) {
        setActionTitle(title);
        toggleActionLeftImg(false);
        toggleActionRightImg(false);
        toggleActionRightText(false);
        toggleActionRightImgCount(false);
    }

    /**
     * 显示标题和左侧返回按钮
     *
     * @param title
     */
    public void showActionTitleAndBack(String title) {
        setActionTitle(title);
        toggleActionLeftImg(true);
        toggleActionRightImg(false);
        toggleActionRightText(false);
    }

    /**
     * 显示左边的图标+文字、中间标题、右边的文字
     *
     * @param title
     * @param rightTxtRes
     */
    public void showActionBacksTitleAndRightText(String title, int rightTxtRes) {
        setActionTitle(title);
        setActionRightTxt(rightTxtRes);
        toggleActionLeftImg(true);
        toggleActionLeftText(true);
        toggleActionRightImg(false);
        toggleActionRightText(true);
    }

    /**
     * 显示左边的图标+文字、中间标题、右边的图片
     *
     * @param title
     * @param rightImgRes
     */
    public void showActionBacksTitleAndRightImage(String title, int rightImgRes) {
        setActionTitle(title);
        toggleActionLeftImg(true);
        toggleActionLeftText(true);
        toggleActionRightImg(true);
        toggleActionRightText(false);
        setActionRightImg(rightImgRes);
    }

    /**
     * 显示左边的图片、中间标题、右边的文字
     *
     * @param title
     * @param rightTxtRes
     */
    public void showActionBacksTitleLeftImgAndRightText(String title, int leftImgRes, int rightTxtRes) {
        setActionTitle(title);
        setActionRightTxt(rightTxtRes);
        setActionLeftImg(leftImgRes);
        toggleActionLeftImg(true);
        toggleActionLeftText(false);
        toggleActionRightImg(false);
        toggleActionRightText(true);
    }

    /**
     * 显示左边图标+文字、中间标题
     *
     * @param title
     */
    public void showActionBacksTitle(String title) {
        setActionTitle(title);
        toggleActionLeftImg(true);
        toggleActionLeftText(true);
        toggleActionRightImg(false);
        toggleActionRightText(false);
    }

    /**
     * 显示标题和左侧返回按钮
     * 设置右侧自定义图标
     *
     * @param title
     * @param imgRightId
     */
    public void showActionTitleBackAndRightImage(String title, int imgRightId) {
        setActionTitle(title);
        toggleActionLeftImg(true);
        toggleActionRightImg(true);
        toggleActionRightText(false);
        setActionRightImg(imgRightId);
    }

    public void showActionTitleAndRightText(String title, int txtRightId) {
        setActionTitle(title);
        toggleActionLeftImg(false);
        toggleActionRightImg(false);
        toggleActionRightText(true);
        setActionRightTxt(txtRightId);
    }

    public void showActionTitleAndRightImage(String title, int imgRightId) {
        setActionTitle(title);
        toggleActionLeftImg(false);
        toggleActionRightImg(true);
        toggleActionRightText(false);
        setActionRightImg(imgRightId);
    }

    /**
     * 显示左侧图标不带返回,标题
     *
     * @param title
     * @param imgLeftId
     */
    public void showActionTitleAndLeftImage(String title, int imgLeftId) {
        setActionTitle(title);
        toggleActionLeftImg(true);
        toggleActionRightImg(false);
        toggleActionRightText(false);
        setActionLeftImg(imgLeftId);
    }

    //显示右侧带count的图片
    public void showActionTitleAndRightImage2(String title, int imgRightId, String numcount) {
        setActionTitle(title);
        setActionRightCount(numcount);
        toggleActionLeftImg(false);
        toggleActionRightImg(true);
        toggleActionRightText(false);
        setActionRightImg(imgRightId);
        toggleActionRightImgCount(true);
    }

    public void showActionTitleAndRightImage3(String title) {
        setActionTitle(title);
        toggleActionLeftImg(false);
        toggleActionRightImg2(false);
        toggleActionRightText(false);
    }

    public void showActionTitleAndRightLeftImage(String title, int imgRightId, int imgLeftId) {
        setActionTitle(title);
        toggleActionLeftImg(true);
        toggleActionRightImg(true);
        toggleActionRightText(false);
        setActionRightImg(imgRightId);
        setActionLeftImg(imgLeftId);
    }

    public void showActionBacksTitleAndRightImageText(String title, int imgRightId, int rightText) {
        setActionTitle(title);
        setActionRightTxt(rightText);
        toggleActionLeftImg(true);
        toggleActionLeftText(true);
        toggleActionRightText(true);
        toggleActionRightTextDrawableLeft(imgRightId);
        toggleActionRightImg(false);
    }


    /**
     * Action Bar 右侧图片事件响应
     * 需要在子类中重写
     */
    public void onActionRightImageClick() {

    }

    /**
     * Action Bar 左侧图片事件响应
     * 需要在子类中重写
     */
    public void onActionLeftImageClick() {

    }


    /**
     * Action Bar 右侧文字事件响应
     */
    public void onActionRightTextClick() {

    }

    /**
     * Action Bar 左侧文字事件响应
     */
    public void onActionLeftTextClick() {
    }

    /**
     * 子类重写此方法获取布局文件
     *
     * @return
     */
    protected abstract int getLayout();

    /**
     * 子类重写此方法初始化数据
     */
    protected abstract void initView();

    protected String getChildClassName() {
        return getClass().getSimpleName();
    }

    /**
     * 跳转界面无参数传递
     *
     * @param mClass
     */
    protected void startActivity(Class<?> mClass) {
        Intent mIntent = new Intent(this, mClass);
        startActivity(mIntent);
        finish();
    }
}


