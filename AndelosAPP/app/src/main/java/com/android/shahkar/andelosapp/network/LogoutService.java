package com.android.shahkar.andelosapp.network;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by User on 2/14/2017.
 */
public class LogoutService extends PostObjectService {

    public LogoutService(Call<ResponseBody> call) {
        super(call);
    }
}
