package com.android.shahkar.andelosapp.network;

import com.android.shahkar.andelosapp.models.AccessToken;

import retrofit2.Call;

/**
 * Created by User on 2/12/2017.
 */
public class LoginService extends FetchObjectService<AccessToken>{

    public LoginService(Call<AccessToken> call) {
        super(call);
    }
}
