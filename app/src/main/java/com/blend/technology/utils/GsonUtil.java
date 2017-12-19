package com.blend.technology.utils;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by rankaifeng on 2017/11/1.
 */

public class GsonUtil {
    private static GsonUtil instnse;
    private static Gson mGson;

    public static GsonUtil getInstnse() {
        if (instnse == null && mGson == null) {
            mGson = new Gson();
            instnse = new GsonUtil();
        }
        return instnse;
    }


    public RequestBody changeBody(Object object) {
        if (object != null) {
            String mJson = mGson.toJson(object);
            return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), mJson);
        } else {
            return null;
        }
    }
}
