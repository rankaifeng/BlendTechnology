package com.blend.technology.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiConfiguration.KeyMgmt;
import android.net.wifi.WifiManager;


/**
 * Created by Horrarndoo on 2017/8/31.
 * <p>
 * Wifi连接工具类
 */
public class NetworkConnectionUtils {
    private final static String TAG = "NetworkConnectionUtils";

    public NetworkConnectionUtils() {
    }

    /**
     * 连接指定
     *
     * @param manager
     * @param wifiSSID
     * @return
     */
    public static boolean connectToSocketWifi(WifiManager manager, String wifiSSID) {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = "\"" + wifiSSID + "\"";
        wifiConfiguration.allowedKeyManagement.set(KeyMgmt.NONE);
        wifiConfiguration.wepKeys[0] = "\"" + "\""; //小米手机MIUI7/华为EMUI4.1 需要webKey

        int networkId = manager.addNetwork(wifiConfiguration);

        if (networkId != -1) {
            manager.enableNetwork(networkId, true);
            return true;
        } else {
            WifiConfiguration wifiConfiguration2 = new WifiConfiguration();
            wifiConfiguration2.SSID = "\"" + wifiSSID + "\"";
            //wifiConfiguration.wepKeys[0] = "\"" + "\"";//去掉webKey  //小米手机MIUI8不能有webKey
            wifiConfiguration2.allowedKeyManagement.set(KeyMgmt.NONE);
            networkId = manager.addNetwork(wifiConfiguration2);
            if (networkId != -1) {
                manager.enableNetwork(networkId, true);
                return true;
            }
        }
        return false;
    }


    /**
     * 格式化RouterSSID
     *
     * @param strRouterSSID 要格式化的当前连接的路由ssid
     * @return 去除"\"后的RouterSSID字符串
     */
    public static String formatRouterSSID(String strRouterSSID) {
        //e("formate routerSSID before---" + strRouterSSID);
        if (strRouterSSID.contains("\"")) {
            strRouterSSID = strRouterSSID.replaceAll("\"", "");
            //e("formate routerSSID after---" + strRouterSSID);
        }
        return strRouterSSID;
    }


    /**
     * 判断网络是否连接
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null == cm) {
            return false;
        }

        NetworkInfo info = cm.getActiveNetworkInfo();
        if (null != info && info.isConnected()) {
            if (info.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否有网络
     *
     * @return 返回值
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();

            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断是否是wifi连接
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null == cm) {
            return false;
        }

        NetworkInfo info = cm.getActiveNetworkInfo();
        if (null != info) {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;

    }


    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity, int requestCode) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction(Intent.ACTION_VIEW);
        activity.startActivityForResult(intent, requestCode);
    }
}
