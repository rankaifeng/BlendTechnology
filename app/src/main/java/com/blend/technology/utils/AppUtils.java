package com.blend.technology.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


public class AppUtils {

    public static String getAppversionName(Context context) {
        String appVersion = "";
        PackageManager mPackManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = mPackManager.getPackageInfo(context.getPackageName(), 0);
            appVersion = packageInfo.versionName;
            if (appVersion == null || appVersion.length() <= 0) {
                return "";
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appVersion;
    }
}
