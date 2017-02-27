package com.android.shahkar.andelosapp.network;

import com.android.shahkar.andelosapp.models.AccessToken;

import retrofit2.Call;

public class RegisterService extends FetchObjectService<AccessToken> {

    public RegisterService(Call<AccessToken> call) {
        super(call);
    }

}
