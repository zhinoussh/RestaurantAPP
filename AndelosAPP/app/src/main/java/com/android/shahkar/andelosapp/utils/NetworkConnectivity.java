package com.android.shahkar.andelosapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by User on 2/4/2017.
 */
public class NetworkConnectivity {

    private Context activityContext;

    public NetworkConnectivity(Context c) {
        activityContext = c;
    }

    public Boolean checkNetworkStatus() {

        ConnectivityManager cm = (ConnectivityManager)
                activityContext.getSystemService(activityContext.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
            return true;
        else
            return false;
    }
}
