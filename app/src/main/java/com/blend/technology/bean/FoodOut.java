package com.blend.technology.bean;

import java.util.List;


public class FoodOut {
    private String msg;
    List<Data> arrayList;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Data> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<Data> arrayList) {
        this.arrayList = arrayList;
    }

    public class Data{
        private String id;
        private String title;
        private String imgUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
