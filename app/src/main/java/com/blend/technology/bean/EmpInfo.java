package com.blend.technology.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by wangrun on 2016/8/24
 */
public class EmpInfo implements Serializable {
    private String comp_name;//企业名称
    private String emp_code;//用户账号
    private String emp_mobile_phone;//用户手机号
    private ArrayList<String> platList;//站点信息
    private String status;//用户状态  状态00删除10新增15审批不通过20审批通过

    public String getComp_name() {
        return comp_name;
    }

    public void setComp_name(String comp_name) {
        this.comp_name = comp_name;
    }

    public String getEmp_code() {
        return emp_code;
    }

    public void setEmp_code(String emp_code) {
        this.emp_code = emp_code;
    }

    public String getEmp_mobile_phone() {
        return emp_mobile_phone;
    }

    public void setEmp_mobile_phone(String emp_mobile_phone) {
        this.emp_mobile_phone = emp_mobile_phone;
    }

    public ArrayList<String> getPlatList() {
        return platList;
    }

    public void setPlatList(ArrayList<String> platList) {
        this.platList = platList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
