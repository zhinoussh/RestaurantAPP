package com.android.shahkar.andelosapp.network;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {

    private static final String BASE_URL = "http://10.0.2.2:8001/api/";
    private static Retrofit retrofit = null;

    public static APIService getAPIService() {
        try {
            if (retrofit == null) {
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(60,TimeUnit.SECONDS).build();

                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();
            }
            return retrofit.create(APIService.class);
        } catch (Exception e) {
            Log.d("OnBuildRetrofit", "fail to create API: " + e.toString());
            return null;
        }
    }




}
