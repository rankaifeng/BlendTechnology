package com.blend.technology.bean;

import java.util.List;

/**
 * Created by rankaifeng on 2017/12/24.
 */

public class FoodOut {
    List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    class Data{
        private String id;
        private String title;
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

        public String getAlbums() {
            return albums;
        }

        public void setAlbums(String albums) {
            this.albums = albums;
        }
    }
}
