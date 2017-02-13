package com.android.shahkar.andelosapp.network;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.shahkar.andelosapp.utils.ResultCallBackObject;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostObjectService {

    private Call<ResponseBody> call;

    public PostObjectService(Call<ResponseBody> call) {
        this.call = call;
    }

    public void PostObject(final ResultCallBackObject resultCallBack, final ProgressBar progressBar) {

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful()) {
                        resultCallBack.OnResultReady(null, "Success");
                    } else {
                        String result="ERROR in Sign up";
                        if (response != null && response.errorBody() != null) {
                            JSONObject jsonObject = new JSONObject(response.errorBody().string());

                            if(jsonObject.has("error_description"))
                                result = jsonObject.getString("error_description");
                            else if(jsonObject.has("Message"))
                                result = jsonObject.getString("Message");

                        }
                        resultCallBack.OnResultReady(null, result);
                    }
                } catch (Exception ex) {
                    Log.d("OnResponse Post Object", ex.toString());
                    resultCallBack.OnResultReady(null, "Error in post object: " + ex.toString());
                } finally {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                resultCallBack.OnResultReady(null, "Error in post object: " + t.toString());
                Log.d("OnResponse Post Object", t.toString());
            }
        });


    }
}
