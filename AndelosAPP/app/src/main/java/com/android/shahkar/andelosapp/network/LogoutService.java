package com.android.shahkar.andelosapp.network;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class LogoutService extends PostObjectService {

    public LogoutService(Call<ResponseBody> call) {
        super(call);
    }
}
