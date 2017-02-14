package com.android.shahkar.andelosapp.network;

import com.android.shahkar.andelosapp.models.AccessToken;
import com.android.shahkar.andelosapp.models.RestaurantCategory;
import com.android.shahkar.andelosapp.models.RestaurantMenuItem;
import com.android.shahkar.andelosapp.models.User;

import java.util.List;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

    @GET("Category")
    public Call<List<RestaurantCategory>> getCategoryList();

    @GET("Menu/GetMenuInCategory/{id}")
    public Call<List<RestaurantMenuItem>> getMenuList(@Path("id") int CategoryID);

    @FormUrlEncoded
    @POST("token")
    public Call<AccessToken> getAccessToken(
            @Field("username") String userName
            , @Field("password") String pssword
            , @Field("grant_type") String grant_type);

    @POST("Account/Register")
    public Call<AccessToken> RegisterUser(@Body User user);

    @POST("Account/Logout")
    public Call<ResponseBody> LogoutUser();
}
