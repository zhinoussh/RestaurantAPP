package com.android.shahkar.andelosapp.network;

import com.android.shahkar.andelosapp.models.RestaurantCategory;
import com.android.shahkar.andelosapp.models.RestaurantMenuItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by User on 1/29/2017.
 */
public interface APIService {

    @GET("Category")
    public Call<List<RestaurantCategory>> getCategoryList();

    @GET("Menu/GetMenuInCategory/{id}")
    public Call<List<RestaurantMenuItem>> getMenuList(@Path("id") int CategoryID);
}
