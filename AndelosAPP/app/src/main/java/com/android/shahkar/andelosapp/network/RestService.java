package com.android.shahkar.andelosapp.network;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 1/30/2017.
 */
public class RestService {

    private static final String BASE_URL="http://10.0.2.2:8001/api/";
    private static Retrofit retrofit=null;

    public static Retrofit getRestService() {

        try {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();

            return retrofit;
        }
        catch (Exception e)
        {
            Log.d("OnBuildRetrofit","failt to build retrofit: "+e.toString());
            return  null;
        }
    }



}
