package com.android.shahkar.andelosapp.network;

import android.text.TextUtils;
import android.util.Log;

import com.android.shahkar.andelosapp.utils.ApplicationConstant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS);

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(ApplicationConstant.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit;

    public static APIService createService() {
        return createService(null);
    }

    public static APIService createService(String authToken) {
        try {
            //Add Authorization Header
            if (!TextUtils.isEmpty(authToken)) {
                AuthenticationInterceptor interceptor =
                        new AuthenticationInterceptor(authToken);

                if (!httpClient.interceptors().contains(interceptor)) {
                    httpClient.addInterceptor(interceptor);
                }
            }

            //Build Retrofit
            builder.client(httpClient.build());
            retrofit = builder.build();
            return retrofit.create(APIService.class);

        } catch (Exception e) {
            Log.d("OnBuildRetrofit", "fail to create API: " + e.toString());
            return null;
        }
    }


}

class AuthenticationInterceptor implements Interceptor {

    private String authToken;

    public AuthenticationInterceptor(String token) {
        this.authToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("Authorization","Bearer " +authToken);

        Request request = builder.build();
        return chain.proceed(request);
    }
}
