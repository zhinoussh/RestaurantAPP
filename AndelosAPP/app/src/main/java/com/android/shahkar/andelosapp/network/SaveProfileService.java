package com.android.shahkar.andelosapp.network;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class SaveProfileService extends PostObjectService {
    public SaveProfileService(Call<ResponseBody> call) {
        super(call);
    }
}
