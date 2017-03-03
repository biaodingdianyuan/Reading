package com.example.yangliu.reading;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.R.attr.tag;

/**
 * Created by liuhaifeng on 2017/2/24.
 */

public class MyApplication extends Application {
    public static boolean isnet=false;
    @Override
    public void onCreate() {
        super.onCreate();
        isnet=isNetworkAvailable(getApplicationContext());

    }
    public static boolean isNetworkAvailable(final Context context) {
        boolean hasWifoCon = false;
        boolean hasMobileCon = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfos = cm.getAllNetworkInfo();
        for (NetworkInfo net : netInfos) {

            String type = net.getTypeName();
            if (type.equalsIgnoreCase("WIFI")) {
                if (net.isConnected()) {
                    hasWifoCon = true;
                }
            }

            if (type.equalsIgnoreCase("MOBILE")) {
                if (net.isConnected()) {
                    hasMobileCon = true;
                }
            }
        }
        return hasWifoCon || hasMobileCon;

    }
}
