package com.android.shahkar.andelosapp.activities;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.models.AccessToken;
import com.android.shahkar.andelosapp.network.APIService;
import com.android.shahkar.andelosapp.network.LoginService;
import com.android.shahkar.andelosapp.network.RestService;
import com.android.shahkar.andelosapp.utils.ResultCallBackObject;


import org.json.JSONObject;

import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {

    TextView txt_username, txt_password, txt_error;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Typeface font_AppBar = Typeface.DEFAULT.createFromAsset(getAssets(), "fonts/LobsterTwo-Bold.ttf");
        TextView txt_AppName = (TextView) findViewById(R.id.txt_loginLogo);
        txt_AppName.setTypeface(font_AppBar);

        txt_username = (TextView) findViewById(R.id.txt_username);
        txt_password = (TextView) findViewById(R.id.txt_password);
        txt_error = (TextView) findViewById(R.id.txt_error_login);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProcess();
            }
        });
    }

    private void loginProcess() {
        if (!validateLogin()) {
            onLoginFailed();
            return;
        }

        btn_login.setEnabled(false);

        String username = txt_username.getText().toString();
        String password = txt_password.getText().toString();

        ProgressBar p = (ProgressBar) findViewById(R.id.login_progress);
        p.setVisibility(View.VISIBLE);

        APIService api = RestService.getAPIService();
        if (api != null) {
            Call<AccessToken> call_accessToken = api.getAccessToken(username, password, "password");
            LoginService login_service = new LoginService(call_accessToken);
            login_service.FetchObject(new ResultCallBackObject<AccessToken>() {
                @Override
                public void OnResultReady(AccessToken token, String message) {
                    if (token != null)
                        onLoginSuccess(token);
                    else {
                        txt_error.setText(message);
                        btn_login.setEnabled(true);
                    }
                }
            }, p);

        }
    }

    private boolean validateLogin() {
        boolean valid = true;

        String username = txt_username.getText().toString();
        String password = txt_password.getText().toString();

        if (username.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            txt_username.setError("enter a valid email address");
            valid = false;
        } else {
            txt_username.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            txt_password.setError("must be at least 6 alphanumeric characters");
            valid = false;
        } else {
            txt_password.setError(null);
        }

        return valid;
    }

    public void onLoginSuccess(AccessToken token) {
        btn_login.setEnabled(true);
        SharedPreferences.Editor pref_editor = getSharedPreferences(
                getResources().getString(R.string.app_name), MODE_PRIVATE).edit();
        pref_editor.putString(token.getUserName(), token.getAccessToken());
        //finish();
        txt_error.setText( token.getAccessToken());

    }

    public void onLoginFailed() {
        txt_error.setText("username or password is incorrect");
        btn_login.setEnabled(true);
    }
}
