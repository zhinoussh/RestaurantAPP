package com.android.shahkar.andelosapp.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {

    private static final String BASE_URL = "http://10.0.2.2:8001/api/";
    private static Retrofit retrofit = null;

    public static APIService getAPIService() {
        try {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
            }
            return retrofit.create(APIService.class);
        } catch (Exception e) {
            Log.d("OnBuildRetrofit", "fail to create API: " + e.toString());
            return null;
        }
    }




}
