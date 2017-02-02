package com.android.shahkar.andelosapp.network;

import android.util.Log;

import com.android.shahkar.andelosapp.models.RestaurantCategory;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchListService<T>  {

     private Call<List<T>> call;
    private List<T> fetchList;

    public FetchListService(Call<List<T>> callAPI) {
        call=callAPI;
    }

    public List<T> FetchList(final ResultCallBack<T> resultCallback){

        if (call != null) {

            call.enqueue(new Callback<List<T>>() {
                @Override
                public void onResponse(Call<List<T>> call, Response<List<T>> response) {
                    try {
                        if (response.isSuccessful()) {
                            fetchList = response.body();
                            resultCallback.OnResultReady(fetchList);
                        }
                        else
                            Log.d("OnResponse", "Fail to  get List with error: " + response.message());

                    } catch (Exception ex) {
                        Log.d("OnResponse", "Err in Response: " + ex.toString());
                    }
                }

                @Override
                public void onFailure(Call<List<T>> call, Throwable t) {
                    Log.d("OnResponse", "Fail to Response: " + t.toString());
                }
            });

        }
        return fetchList;
    }
}
