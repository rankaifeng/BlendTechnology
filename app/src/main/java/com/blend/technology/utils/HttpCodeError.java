package com.blend.technology.utils;

import android.accounts.NetworkErrorException;
import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;


public class HttpCodeError {
    public static String returnErrorMsg(Throwable throwable) {
        StringBuffer sb = new StringBuffer();
        if (throwable instanceof NetworkErrorException ||
                throwable instanceof UnknownHostException
                || throwable instanceof ConnectException) {
            sb.append("网络异常");
        } else if (throwable instanceof SocketTimeoutException
                || throwable instanceof InterruptedIOException
                || throwable instanceof TimeoutException) {
            sb.append("请求超时");
        } else if (throwable instanceof JsonSyntaxException) {
            sb.append("请求不合法");
        } else if (throwable instanceof JsonParseException
                || throwable instanceof JSONException
                || throwable instanceof ParseException) {   //  解析错误
            sb.append("解析错误");
        } else {
            return "";
        }
        return sb.toString();
    }
}
