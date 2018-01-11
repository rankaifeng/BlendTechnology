package com.blend.technology.base;

import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.List;

/**
 * unknown on 2018/1/9.
 */
public class MyBanner {
    private Banner mBanner;

    MyBanner(Builder builder) {
        this.mBanner = builder.mBanner;
        mBanner.setImages(builder.images);
        mBanner.setBannerTitles(builder.bannerTitls);
        mBanner.setImageLoader(builder.imageLoader);
    }

    public static class Builder {
        private List<String>         bannerTitls;
        private ImageLoaderInterface imageLoader;
        private List<String>         images;
        private Banner               mBanner;

        public Builder bannerTitls(List<String> bannerTitls) {
            this.bannerTitls = bannerTitls;
            return this;
        }

        public Builder imageLoader(ImageLoaderInterface imageLoader) {
            this.imageLoader = imageLoader;
            return this;
        }

        public Builder mBanner(Banner mBanner) {
            this.mBanner = mBanner;
            return this;
        }

        public Builder images(List<String> images) {
            this.images = images;
            return this;
        }

        public MyBanner build() {
            return new MyBanner(this);
        }
    }

    public void start() {
        if (mBanner != null) {
            mBanner.start();
        }
    }
}
