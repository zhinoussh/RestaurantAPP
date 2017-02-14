package com.android.shahkar.andelosapp.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.network.APIService;
import com.android.shahkar.andelosapp.network.LogoutService;
import com.android.shahkar.andelosapp.network.ServiceGenerator;
import com.android.shahkar.andelosapp.utils.ApplicationConstant;
import com.android.shahkar.andelosapp.utils.ResultCallBackObject;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class UserActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        Button btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutProcess();
            }
        });
    }

    private void logoutProcess() {

        ProgressBar progress = (ProgressBar) findViewById(R.id.logout_progress);

        final SharedPreferences pref = getSharedPreferences(getResources().getString(R.string.app_name), MODE_PRIVATE);
        String authToken = pref.getString(ApplicationConstant.TOKEN_PREF_KEY, null);
        Toast.makeText(getApplicationContext(), authToken, Toast.LENGTH_LONG).show();

        APIService api = ServiceGenerator.createService(authToken);
        Call<ResponseBody> call_logout = api.LogoutUser();
        LogoutService service = new LogoutService(call_logout);
        service.PostObject(new ResultCallBackObject() {
            @Override
            public void OnResultReady(Object return_object, String message) {
                if (message == "Success") {
                    pref.edit().clear().commit();
                    setResult(RESULT_OK, getIntent());
                    finish();
                } else
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

            }
        }, progress);

    }
}
