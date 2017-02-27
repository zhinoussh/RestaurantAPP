package com.android.shahkar.andelosapp.network;

import com.android.shahkar.andelosapp.models.RestaurantCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class CategoryService extends FetchListService<RestaurantCategory> {

    public CategoryService(Call<List<RestaurantCategory>> callAPI) {
        super(callAPI);
    }
}
