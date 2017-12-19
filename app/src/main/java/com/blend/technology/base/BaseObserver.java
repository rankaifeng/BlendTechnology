package com.blend.technology.base;


import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.net.ParseException;
import android.support.annotation.NonNull;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by rankaifeng on 2017/9/25.
 */

public abstract class BaseObserver<T> implements Observer<T> {


    private Activity activity;

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
        if (null != t) {
            success(t);
        }
    }


    @Override
    public void onError(@NonNull Throwable t) {
        StringBuffer sb = new StringBuffer();
        if (t instanceof NetworkErrorException ||
                t instanceof UnknownHostException
                || t instanceof ConnectException) {
            sb.append("网络异常");
        } else if (t instanceof SocketTimeoutException
                || t instanceof InterruptedIOException
                || t instanceof TimeoutException) {
            sb.append("请求超时");
        } else if (t instanceof JsonSyntaxException) {
            sb.append("请求不合法");
        } else if (t instanceof JsonParseException
                || t instanceof JSONException
                || t instanceof ParseException) {   //  解析错误
            sb.append("解析错误");
        } else {
            return;
        }
    }

    @Override
    public void onComplete() {

    }

    public abstract void success(T obj);

}
