package com.android.shahkar.andelosapp.network;

import com.android.shahkar.andelosapp.models.AccessToken;

import retrofit2.Call;

public class LoginService extends FetchObjectService<AccessToken>{

    public LoginService(Call<AccessToken> call) {
        super(call);
    }

}


