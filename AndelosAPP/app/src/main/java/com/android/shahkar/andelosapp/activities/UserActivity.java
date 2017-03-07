package com.android.shahkar.andelosapp.activities;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.fragments.ProfileFragment;
import com.android.shahkar.andelosapp.network.APIService;
import com.android.shahkar.andelosapp.network.LogoutService;
import com.android.shahkar.andelosapp.network.ServiceGenerator;
import com.android.shahkar.andelosapp.utils.ApplicationConstant;
import com.android.shahkar.andelosapp.network.ResultCallBackObject;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class UserActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Typeface font = Typeface.DEFAULT.createFromAsset(getAssets(), "fonts/Ubuntu-Medium.ttf");

        //Initialize User Activity with Profile Fragment
        ProfileFragment fragment = new ProfileFragment();
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
                    ProfileFragment fragment = new ProfileFragment();
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


}
