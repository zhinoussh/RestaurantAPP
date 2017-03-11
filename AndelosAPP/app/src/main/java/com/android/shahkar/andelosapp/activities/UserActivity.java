package com.android.shahkar.andelosapp.activities;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.database.ProfileDataSource;
import com.android.shahkar.andelosapp.fragments.ProfileFragment;
import com.android.shahkar.andelosapp.models.UserProfile;
import com.android.shahkar.andelosapp.network.APIService;
import com.android.shahkar.andelosapp.network.LogoutService;
import com.android.shahkar.andelosapp.network.ProfileService;
import com.android.shahkar.andelosapp.network.SaveProfileService;
import com.android.shahkar.andelosapp.network.ServiceGenerator;
import com.android.shahkar.andelosapp.utils.ApplicationConstant;
import com.android.shahkar.andelosapp.network.ResultCallBackObject;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class UserActivity extends AppCompatActivity implements ProfileFragment.ProfileFragmentListener {

    UserProfile profile;
    SharedPreferences preferences;
    ProgressBar progress_user;
    String authToken;
    ProfileDataSource ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Typeface font = Typeface.DEFAULT.createFromAsset(getAssets(), "fonts/Ubuntu-Medium.ttf");

        preferences=getSharedPreferences(
                getResources().getString(R.string.app_name), MODE_PRIVATE);
        authToken = preferences.getString(ApplicationConstant.TOKEN_PREF_KEY, null);

        ds=new ProfileDataSource(this);

        progress_user = (ProgressBar)findViewById(R.id.user_progress);

        //Initialize User Activity with Profile Fragment
        ProfileFragment fragment = getInitialProfileFragment();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.frame_user_content, fragment, "ProfileFragment")
                .commit();

        Button btnProfile=(Button)findViewById(R.id.btn_profile);
        btnProfile.setTypeface(font);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment currentFragment=getFragmentManager().findFragmentById(R.id.frame_user_content);
                if(!(currentFragment instanceof ProfileFragment)) {

                    ProfileFragment fragment = getInitialProfileFragment();
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_user_content, fragment, "ProfileFragment")
                            .commit();
                }
            }
        });

        Button btnFavorite=(Button)findViewById(R.id.btn_favorites);
        btnFavorite.setTypeface(font);
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        Button btnPreviousOrders=(Button)findViewById(R.id.btn_previousOrders);
        btnPreviousOrders.setTypeface(font);
        btnPreviousOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }

    private void logoutProcess() {
        APIService api = ServiceGenerator.createService(authToken);
        Call<ResponseBody> call_logout = api.LogoutUser();
        LogoutService service = new LogoutService(call_logout);
        service.PostObject(new ResultCallBackObject() {
            @Override
            public void OnResultReady(Object return_object, String message) {
                if (message == "Success") {
                    preferences.edit().clear().commit();
                    setResult(RESULT_OK,getIntent());
                    finish();
                } else
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

            }
        }, progress_user);
    }

    private void saveProfileProcess(final UserProfile profile) {
        //Save profile to server
        APIService api=ServiceGenerator.createService(authToken);
        Call<ResponseBody> call_profileService=api.SaveUserProfile(profile);
        SaveProfileService profile_service=new SaveProfileService(call_profileService);
        profile_service.PostObject(new ResultCallBackObject() {
            @Override
            public void OnResultReady(Object return_object, String message) {
                if (message == "Success") {
                    //Save Profile in local db
                    String old_username=preferences.getString(ApplicationConstant.USERNAME_PREF_KEY,"");
                    ds.InsertProfile(profile,old_username);

                    //save new profile values in shared preferences
                    SharedPreferences.Editor pref_editor=preferences.edit();
                    pref_editor.putString(ApplicationConstant.USERNAME_PREF_KEY,profile.getUserName());
                    pref_editor.putString(ApplicationConstant.FIRSTNAME_PREF_KEY,profile.getFirstName());
                    pref_editor.putString(ApplicationConstant.LASTNAME_PREF_KEY,profile.getLastName());
                    pref_editor.apply();
                } else
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

            }
        },progress_user);


    }

    private ProfileFragment getInitialProfileFragment() {
         String username= preferences.getString(ApplicationConstant.USERNAME_PREF_KEY,"");
        //get profile from local db
        profile=ds.getUserProfile(username);

        //if null get from server
        if(profile==null)
        {
            APIService api=ServiceGenerator.createService(authToken);
            Call<UserProfile> call_profile=api.GetUserProfile(username);
            ProfileService profileService=new ProfileService(call_profile);
            profileService.FetchObject(new ResultCallBackObject<UserProfile>() {
                @Override
                public void OnResultReady(UserProfile return_profile, String message) {
                    if (message == "Success" || message=="Empty") {
                        profile=return_profile;
                    } else
                        Toast.makeText(getApplicationContext(),"retrofit: "+ message, Toast.LENGTH_LONG).show();
                }
            },progress_user);

        }

        //if still null get from shared pref
        if(profile==null)
        {
            profile=new UserProfile();
            profile.setFirstName(preferences.getString(ApplicationConstant.FIRSTNAME_PREF_KEY,""));
            profile.setLastName(preferences.getString(ApplicationConstant.LASTNAME_PREF_KEY,""));
            profile.setUserName(preferences.getString(ApplicationConstant.USERNAME_PREF_KEY,""));
        }
        ProfileFragment fragment = ProfileFragment.newInstance(profile);
        return fragment;
    }


    @Override
    public void onFragmentResult(String processName,UserProfile profile) {

        if(processName.equals(ApplicationConstant.SAVE_PROFILE))
            saveProfileProcess(profile);
        else if(processName.equals(ApplicationConstant.LOGOUT))
            logoutProcess();

    }


}
