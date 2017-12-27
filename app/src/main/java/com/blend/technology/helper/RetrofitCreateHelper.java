package com.blend.technology.helper;

import com.blend.technology.utils.Config;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitCreateHelper {
    public static <T> T createApi(Class<T> clazz) {
        return new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build().create(clazz);
    }

    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(chain -> {
            Request newRequest = chain.request().newBuilder()
                    .addHeader("OuYeellAuthorization",
                            "android<>D50A28DC9BD94E99A8A7278F02385184<>1.0.0.2<>121.488602<>31.291834")
                    .addHeader("DeviceInfo", "3736917327797053555")
                    .addHeader("Content-Type", "application/json;charset=UTF-8")
                    .build();
            return chain.proceed(newRequest);
        }).connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }
}
