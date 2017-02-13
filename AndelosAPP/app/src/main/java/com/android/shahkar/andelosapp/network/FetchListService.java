package com.android.shahkar.andelosapp.network;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.shahkar.andelosapp.utils.ResultCallBackList;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchListService<T>  {

     private Call<List<T>> call;

    public FetchListService(Call<List<T>> callAPI) {
        call=callAPI;
    }

    public void FetchList(final ResultCallBackList<T> resultCallback, final ProgressBar progress){

        if (call != null) {

            call.enqueue(new Callback<List<T>>() {
                @Override
                public void onResponse(Call<List<T>> call, Response<List<T>> response) {
                    try {
                        if (response.isSuccessful()) {
                            resultCallback.OnResultReady( response.body());
                        }
                        else
                            Log.d("OnResponse", "Fail to  get List with error: " + response.message());

                    } catch (Exception ex) {
                        Log.d("OnResponse", "Err in Response: " + ex.toString());
                    }
                    finally {
                        progress.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<List<T>> call, Throwable t) {
                    progress.setVisibility(View.INVISIBLE);
                    Log.d("OnResponse", "Fail to Response: " + t.toString());
                }
            });
        }
    }
}
