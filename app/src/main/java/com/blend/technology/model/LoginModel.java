package com.blend.technology.model;

import com.blend.technology.base.BaseModel;
import com.blend.technology.bean.LoginIn;
import com.blend.technology.utils.CallBackRequest;


public abstract class LoginModel extends BaseModel {
    public abstract void login(LoginIn loginIn, CallBackRequest callBackRequest);
}
