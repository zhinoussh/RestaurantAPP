package com.android.shahkar.andelosapp.network;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by User on 2/13/2017.
 */
public class RegisterService extends PostObjectService {

    public RegisterService(Call<ResponseBody> call) {
        super(call);
    }

}
