package com.android.shahkar.andelosapp.network;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchObjectService<T> {

    private Call<T> call;

    public FetchObjectService(Call<T> call) {
        this.call = call;
    }

    public void FetchObject(final ResultCallBackObject<T> resultCallBack, final ProgressBar progressBar)
    {
        if(call!=null) {
            call.enqueue(new Callback<T>() {
                @Override
                public void onResponse(Call<T> call, Response<T> response) {
                  try {
                      if(response.isSuccessful()) {
                          resultCallBack.OnResultReady( response.body(),"Success");
                      }
                      else {
                          String error ="Error in Fetch Object";
                          if (response != null && response.errorBody() != null) {
                              error=ParseRetrofitError.getError(response.errorBody().string());
                          }
                          resultCallBack.OnResultReady(null,error);
                      }
                  }
                  catch (Exception ex)
                  {
                      Log.d("OnResponse Fetch Object",  ex.toString());
                      resultCallBack.OnResultReady(null,"Error in fetch object: "+ex.toString());
                  }
                    finally {
                      progressBar.setVisibility(View.INVISIBLE);
                  }
                }

                @Override
                public void onFailure(Call<T> call, Throwable t) {
                    progressBar.setVisibility(View.INVISIBLE);
                    resultCallBack.OnResultReady(null,"Error in fetch object: "+t.toString());
                    Log.d("OnResponse Fetch Object",  t.toString());
                }
            });
        }

    }
}
