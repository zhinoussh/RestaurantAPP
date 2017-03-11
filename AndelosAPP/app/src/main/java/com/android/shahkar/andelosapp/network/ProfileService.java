package com.android.shahkar.andelosapp.network;

import com.android.shahkar.andelosapp.models.UserProfile;

import retrofit2.Call;

public class ProfileService extends FetchObjectService<UserProfile> {
    public ProfileService(Call<UserProfile> call) {
        super(call);
    }
}
