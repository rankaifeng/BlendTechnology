package com.blend.technology.global;

import android.app.Application;
import android.content.Context;

/**
 * Created by rankaifeng on 2017/12/15.
 */

public class MyApplication extends Application {
    protected static Context mContext;
    private static MyApplication myApplication;

    public static synchronized MyApplication getInstanse() {
        if (myApplication == null) {
            myApplication = new MyApplication();
        }
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    /**
     * 获取上下文对象
     *
     * @return
     */
    public static Context getContext() {
        return mContext;
    }
}
