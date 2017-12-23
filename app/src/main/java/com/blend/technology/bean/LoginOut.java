package com.blend.technology.bean;

/**
 * Created by rankaifeng on 2017/10/19.
 */

public class LoginOut {
    private String msg;
    private LoginDetail loginOut;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LoginDetail getLoginOut() {
        return loginOut;
    }

    public void setLoginOut(LoginDetail loginOut) {
        this.loginOut = loginOut;
    }

    public class LoginDetail{
        private String token;
        private String company;
        private String name;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
