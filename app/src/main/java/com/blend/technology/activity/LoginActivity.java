package com.blend.technology.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.blend.technology.R;
import com.blend.technology.base.BaseMVPCompatActivity;
import com.blend.technology.base.BasePresenter;
import com.blend.technology.bean.UserInfoOut;
import com.blend.technology.presenter.LoginPresenter;
import com.blend.technology.utils.LoginContract;

import butterknife.BindView;

/**
 * Created by rankaifeng on 2017/12/15.
 */

public class LoginActivity extends BaseMVPCompatActivity<LoginContract.LoginPresenter,
        LoginContract.LoginModel>
        implements LoginContract.LoginView {
    @BindView(R.id.btn_request)
    Button btnRequest;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_pwd)
    TextView tvPwd;
    boolean isRequest = false;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initV() {
        showActionTitleOnly("测试数据");
        btnRequest.setOnClickListener(v -> {
            if (!isRequest) {
                mIPresenter.login(this);
            } else {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void loginSuess(UserInfoOut userInfoOut) {
        tvName.setText(userInfoOut.getName());
        tvPwd.setText(userInfoOut.getPwd());
        btnRequest.setText("跳转到首页");
        isRequest = true;
    }

    @Override
    public BasePresenter initPresenter() {
        return LoginPresenter.newInstance();
    }

}
