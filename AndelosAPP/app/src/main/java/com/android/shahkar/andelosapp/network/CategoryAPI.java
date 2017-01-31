package com.android.shahkar.andelosapp.network;

import com.android.shahkar.andelosapp.models.RestaurantCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

/**
 * Created by User on 1/29/2017.
 */
public interface CategoryAPI {

    @GET("MealCategory")
    public Call<List<RestaurantCategory>> getCategoryList();
}
