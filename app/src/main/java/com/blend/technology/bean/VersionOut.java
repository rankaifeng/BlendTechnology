package com.blend.technology.bean;

/**
 * Created by rankaifeng on 2017/10/23.
 */

public class VersionOut {
    private int flag;
    private String msg;

    private Data data;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        private String current_version;
        private String upgrade_detail;//更新内容
        private boolean is_force;//是否强制更新 10强制   00非强制
        private String url;//更新地址

        public String getCurrent_version() {
            return current_version;
        }

        public void setCurrent_version(String current_version) {
            this.current_version = current_version;
        }

        public String getUpgrade_detail() {
            return upgrade_detail;
        }

        public void setUpgrade_detail(String upgrade_detail) {
            this.upgrade_detail = upgrade_detail;
        }

        public boolean isIs_force() {
            return is_force;
        }

        public void setIs_force(boolean is_force) {
            this.is_force = is_force;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
