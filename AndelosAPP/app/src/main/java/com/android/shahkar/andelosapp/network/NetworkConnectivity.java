package com.android.shahkar.andelosapp.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.utils.ApplicationConstant;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

    public Boolean isLoggedIn(){

        Boolean loggedIn=true;

        SharedPreferences prefs = activityContext.getSharedPreferences(
                activityContext.getResources().getString(R.string.app_name), activityContext.MODE_PRIVATE);
        String token = prefs.getString(ApplicationConstant.TOKEN_PREF_KEY, "");
        long expire_in_millis = prefs.getLong(ApplicationConstant.EXPIRE_PREF_KEY, 0);

        //calculate expiration time for auth token
        Calendar dt_expire = new GregorianCalendar();
        dt_expire.setTimeInMillis(expire_in_millis);
        Long dt_now = System.currentTimeMillis();

        SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        String expire_time=formatter.format(dt_expire.getTime());
        if(token=="" || expire_in_millis==0 || expire_in_millis <= dt_now)
            loggedIn=false;


        return loggedIn;
    }
}
