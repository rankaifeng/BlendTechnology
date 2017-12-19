package com.blend.technology.base;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by rankaifeng on 2017/12/19.
 */

public abstract class BaseDisposable<T> {
    BaseCompatActivity activity;


    public BaseDisposable(Activity activity) {
        this.activity = (BaseCompatActivity) activity;
    }

    public Disposable requestDisposable(Observable<T> observable) {
        activity.showProgress("数据请求中......");
        return observable.subscribe(t -> {
            if (activity != null) {
                activity.hideProgress();
            }
            requestSuccess(t);
        }, throwable -> {
            activity.hideProgress();
            activity.showToast(errorCode(throwable));
        });
    }

    protected abstract void requestSuccess(T t);


    private String errorCode(Throwable t) {
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
            return "";
        }
        return sb.toString();
    }
}
