package ru.medyannikov.homebank.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Vladimir on 16.03.2016.
 */
public class NetworkStatusChecker {

    public NetworkStatusChecker(){

    }

    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }
}
