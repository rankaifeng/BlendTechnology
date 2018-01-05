package com.blend.technology.login.ui;

import android.widget.Button;
import android.widget.EditText;

import com.blend.technology.R;
import com.blend.technology.activity.MainActivity;
import com.blend.technology.base.BaseMVPCompatActivity;
import com.blend.technology.base.BasePresenter;
import com.blend.technology.bean.LoginIn;
import com.blend.technology.bean.LoginOut;
import com.blend.technology.login.contract.LoginContract;
import com.blend.technology.login.presenter.LoginPresenter;
import com.blend.technology.utils.Config;
import com.blend.technology.utils.StringUtils;
import com.blend.technology.widgets.BlurredView;

import butterknife.BindView;


public class LoginActivity extends BaseMVPCompatActivity<LoginContract.LoginPresenter,
        LoginContract.LoginModel>
        implements LoginContract.LoginView {
    @BindView(R.id.blurr_img)
    BlurredView blurrImg;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.edit_login_name)
    EditText editLoginName;
    @BindView(R.id.edit_login_pwd)
    EditText editLoginPwd;
    private String editStrName;
    private String editStrPwd;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        super.initView();
        blurrImg.setBlurredLevel(Config.TAG_NUMBER);
        btnLogin.setOnClickListener(v -> {
            editStrName = editLoginName.getText().toString();
            editStrPwd = editLoginPwd.getText().toString();
            if (StringUtils.isEmpty(editStrName)) {
                showToast("请输入用户名");
                return;
            }
            if (StringUtils.isEmpty(editStrPwd)) {
                showToast("请输入密码");
                return;
            }
            LoginIn loginIn = new LoginIn();
            loginIn.setUserCode(editStrName);
            loginIn.setPassword(editStrPwd);
            mIPresenter.login(LoginActivity.this, loginIn);
        });
    }


    @Override
    public void loginSuess(LoginOut userInfoOut) {
        startActivity(MainActivity.class);
    }

    @Override
    public BasePresenter initPresenter() {
        return LoginPresenter.newInstance();
    }

}
