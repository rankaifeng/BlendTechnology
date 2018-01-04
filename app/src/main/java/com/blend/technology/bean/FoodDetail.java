package com.blend.technology.bean;

import java.util.List;

/**
 * unknown on 2018/1/4.
 */
public class FoodDetail {
    private String msg;
    private List<Data> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data {
        private String id;
        private String tags;
        private String imtro;
        private String ingredients;
        private String burden;
        private List<Steps> steps;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getImtro() {
            return imtro;
        }

        public void setImtro(String imtro) {
            this.imtro = imtro;
        }

        public String getIngredients() {
            return ingredients;
        }

        public void setIngredients(String ingredients) {
            this.ingredients = ingredients;
        }

        public String getBurden() {
            return burden;
        }

        public void setBurden(String burden) {
            this.burden = burden;
        }

        public List<Steps> getSteps() {
            return steps;
        }

        public void setSteps(List<Steps> steps) {
            this.steps = steps;
        }
    }

    public class Steps {
        private String img;
        private String step;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getStep() {
            return step;
        }

        public void setStep(String step) {
            this.step = step;
        }
    }
}
