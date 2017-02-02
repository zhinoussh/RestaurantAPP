package com.android.shahkar.andelosapp.network;

import com.android.shahkar.andelosapp.models.RestaurantMenuItem;

import java.util.List;

import retrofit2.Call;

/**
 * Created by User on 2/2/2017.
 */
public class MenuService extends FetchListService<RestaurantMenuItem> {

    public MenuService(Call<List<RestaurantMenuItem>> callAPI) {
        super(callAPI);
    }
}
