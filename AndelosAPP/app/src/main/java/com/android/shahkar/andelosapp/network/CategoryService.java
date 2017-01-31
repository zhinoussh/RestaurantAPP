package com.android.shahkar.andelosapp.network;

import android.util.Log;

import com.android.shahkar.andelosapp.models.RestaurantCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by User on 1/30/2017.
 */
public class CategoryService {

    private CategoryAPI api = null;
    private List<RestaurantCategory> categoryList = null;

    public CategoryService() {
        Retrofit restService = RestService.getRestService();
        if (restService != null)
            api = restService.create(CategoryAPI.class);

    }

    public List<RestaurantCategory> CategoryList(final ResultCallback resultCallback) {
        if (api != null) {
            Call<List<RestaurantCategory>> call = api.getCategoryList();

            call.enqueue(new Callback<List<RestaurantCategory>>() {
                @Override
                public void onResponse(Call<List<RestaurantCategory>> call, Response<List<RestaurantCategory>> response) {
                    try {
                        if (response.isSuccessful()) {
                            categoryList = response.body();
                            resultCallback.OnResultReady(categoryList);
                        } else
                            Log.d("OnResponse", "Fail to  catch Category List with error code: " + response.code());

                    } catch (Exception ex) {
                        Log.d("OnResponse", "Err in Response: " + ex.toString());
                    }
                }

                @Override
                public void onFailure(Call<List<RestaurantCategory>> call, Throwable t) {
                    Log.d("OnResponse", "Fail to Response: " + t.toString());
                }
            });

        }
        return categoryList;
    }

    public interface ResultCallback {
        public void OnResultReady(List<RestaurantCategory> categoryList);
    }
}
