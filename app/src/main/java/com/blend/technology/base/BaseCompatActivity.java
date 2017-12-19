package com.blend.technology.base;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by rankaifeng on 2017/12/15.
 */

public abstract class BaseCompatActivity extends AppCompatActivity {
    private static final String CLASS_NAME = "FlashActivity";
    private TextView m_ActionTitle;
    private RelativeLayout m_ActionLeft;
    private ImageView m_ActionLeftImg;
    private TextView m_ActionLeftTxt;
    private RelativeLayout m_ActionRight;
    private ImageView m_ActionRightImg;
    private TextView m_ActionRightTxt;
    private TextView m_numCount;
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
        if (BaseCompatActivity.this != null && !BaseCompatActivity.this.isFinishing()) {
            progressDlg = MyDiglog.createLoadingDialog(BaseCompatActivity.this, msg, false);
        }
    }

    public void showToast(String msg) {
        showToast(msg, true);
    }

    public void showToast(String msg, boolean status) {
        if (status == true) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        if (status == false) {
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
        if (childClassName.equals(CLASS_NAME)) {
            ViewUtil.initSystemBar(this, Color.parseColor("#00000000"));
        } else {
            isShow = true;
            ViewUtil.initSystemBar(this, R.color.colorPrimary);
        }
    }

    public void defaultActionBar() {
        View customerActionBar = getLayoutInflater().inflate(R.layout.baseactivity_bar_layout, null);
        m_ActionTitle = (TextView) customerActionBar.findViewById(R.id.txt_title);
        m_ActionLeft = (RelativeLayout) customerActionBar.findViewById(R.id.relative_left);
        m_ActionLeftTxt = (TextView) customerActionBar.findViewById(R.id.txt_left);
        m_ActionLeftImg = (ImageView) customerActionBar.findViewById(R.id.img_back);
        m_ActionRight = (RelativeLayout) customerActionBar.findViewById(R.id.relative_right);
        m_ActionRightImg = (ImageView) customerActionBar.findViewById(R.id.img_right);
        m_ActionRightTxt = (TextView) customerActionBar.findViewById(R.id.txt_right);
        m_numCount = (TextView) customerActionBar.findViewById(R.id.tv_numcount);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.LEFT);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(customerActionBar, params);
        m_ActionLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (m_ActionLeftImg.getVisibility() == View.GONE && m_ActionLeftTxt.getVisibility() == View.GONE) {
                    return;
                }
                if (mCanGoBack) {
                    onBackPressed();
                } else {
                    if (m_ActionLeftImg.getVisibility() == View.GONE) {
                        onActionLeftTextClick();
                    } else if (m_ActionLeftTxt.getVisibility() == View.GONE) {
                        onActionLeftImageClick();
                    } else {
                        onActionLeftImageClick();
                    }
                }
            }

        });

        /**
         * 右侧事件响应
         */
        m_ActionRight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (m_ActionRightImg.getVisibility() == View.VISIBLE) {
                    onActionRightImageClick();
                } else if (m_ActionRightTxt.getVisibility() == View.VISIBLE) {
                    onActionRightTextClick();
                }
            }
        });
    }

    private void toggleActionLeftImg(boolean toggle) {
        if (m_ActionLeftImg != null) {
            if (toggle) {
                m_ActionLeftImg.setVisibility(View.VISIBLE);
            } else {
                m_ActionLeftImg.setVisibility(View.GONE);
            }
        }
    }

    private void toggleActionLeftText(boolean toggle) {
        if (m_ActionLeftTxt != null) {
            if (toggle) {
                m_ActionLeftTxt.setVisibility(View.VISIBLE);
            } else {
                m_ActionLeftTxt.setVisibility(View.GONE);
            }
        }
    }


    private void toggleActionRightImg(boolean toggle) {
        if (m_ActionRightImg != null) {
            if (toggle) {
                m_ActionRightImg.setVisibility(View.VISIBLE);
            } else {
                m_ActionRightImg.setVisibility(View.GONE);
            }
        }
    }


    private void toggleActionRightImg2(boolean toggle) {
        if (m_ActionRightImg != null) {
            if (toggle) {
                m_ActionRightImg.setVisibility(View.VISIBLE);
                m_numCount.setVisibility(View.VISIBLE);
            } else {
                m_ActionRightImg.setVisibility(View.GONE);
                m_numCount.setVisibility(View.GONE);
            }
        }
    }

    //右侧count
    private void toggleActionRightImgCount(boolean toggle) {
        String c = m_numCount.getText().toString();
        if (!"0".equals(c)) {
            if (toggle) {
                m_ActionRightImg.setVisibility(View.VISIBLE);
                m_numCount.setVisibility(View.VISIBLE);
            }
        } else if ("0".equals(c)) {
            m_ActionRightImg.setVisibility(View.VISIBLE);
            m_numCount.setVisibility(View.GONE);
        }
    }


    private void toggleActionRightText(boolean toggle) {
        if (m_ActionRightTxt != null) {
            if (toggle) {
                m_ActionRightTxt.setVisibility(View.VISIBLE);
            } else {
                m_ActionRightTxt.setVisibility(View.GONE);
            }
        }
    }

    private void toggleActionRightTextDrawableLeft(int id) {
        if (m_ActionRightTxt != null) {
            m_ActionRightTxt.setCompoundDrawablesWithIntrinsicBounds(id, // left
                    0, // top
                    0, // right
                    0);// bo
            m_ActionRightTxt.setCompoundDrawablePadding(3);
        }
    }

    /**
     * 设置ActionBar 居中title
     *
     * @param title
     */
    public void setActionTitle(String title) {
        if (StringUtils.isNotEmpty(title) && m_ActionTitle != null) {
            m_ActionTitle.setText(title);
        }
    }

    /**
     * 设置ActionBar 右侧count
     *
     * @param numcount
     */
    public void setActionRightCount(String numcount) {
        if (StringUtils.isNotEmpty(numcount) && m_ActionTitle != null) {
            m_numCount.setText(numcount);
        }
    }

    /**
     * 设置ActionBar 左侧图片资源
     *
     * @param id
     */
    public void setActionLeftImg(int id) {
        if (m_ActionLeftImg != null) {
            m_ActionLeftImg.setImageResource(id);
        }
    }


    /**
     * 设置ActionBar 图片右侧资源
     *
     * @param id
     */
    public void setActionRightImg(int id) {
        if (m_ActionRightImg != null) {
            m_ActionRightImg.setImageResource(id);
        }
    }

    /**
     * 设置ActionBar 右侧文字资源
     *
     * @param id
     */
    public void setActionRightTxt(int id) {
        if (m_ActionRightTxt != null) {
            m_ActionRightTxt.setText(getString(id));
        }
    }

    /**
     * 设置ActionBar 左侧文字资源
     *
     * @param id
     */
    public void setActionLeftTxt(int id) {
        if (m_ActionLeftTxt != null) {
            m_ActionLeftTxt.setText(getString(id));
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
     * 显示左边文字、中间标题、右边文字
     *
     * @param title
     * @param rightTxtRes
     */
    public void showActionBackTitleAndRightTxt(String title, int rightTxtRes) {
        setActionTitle(title);
        setActionRightTxt(rightTxtRes);
        toggleActionLeftImg(false);
        toggleActionLeftText(true);
        toggleActionRightImg(false);
        toggleActionRightText(true);
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

    /**
     * 显示标题和左侧返回按钮
     * 设置右侧自定义文本
     *
     * @param title
     * @param txtRightId
     */
    public void showActionTitleBackAndRightText(String title, int txtRightId) {
        setActionTitle(title);
        toggleActionLeftImg(true);
        toggleActionRightImg(false);
        toggleActionRightText(true);
        setActionRightTxt(txtRightId);
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


