package com.blend.technology.bean;

import java.io.Serializable;

/**
 * Created by rankaifeng on 2017/12/23.
 */

public class Foods implements Serializable {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    class Data {
        private String id;
        private String title;
        private String tags;
        private String imtro;
        private String ingredients;
        private String burden;
        private String albums;

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

        public String getAlbums() {
            return albums;
        }

        public void setAlbums(String albums) {
            this.albums = albums;
        }
    }
}
