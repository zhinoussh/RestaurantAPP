package com.android.shahkar.andelosapp.activities;

import android.content.Intent;
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
import com.android.shahkar.andelosapp.network.ServiceGenerator;
import com.android.shahkar.andelosapp.utils.ApplicationConstant;
import com.android.shahkar.andelosapp.utils.ResultCallBackObject;
import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {

    TextView txt_username, txt_password, txt_error;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Typeface font_logo = Typeface.DEFAULT.createFromAsset(getAssets(), "fonts/LobsterTwo-Bold.ttf");
        TextView txt_loginLogo = (TextView) findViewById(R.id.txt_loginLogo);
        txt_loginLogo.setTypeface(font_logo);

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

        TextView link_signup=(TextView)findViewById(R.id.link_signup);
        link_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent=new Intent(getApplicationContext(),SignupActivity.class);
                startActivityForResult(signupIntent,ApplicationConstant.SIGNUP_REQUEST);
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

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.login_progress);
        progressBar.setVisibility(View.VISIBLE);

        APIService api = ServiceGenerator.createService();
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
            }, progressBar);

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

        SharedPreferences.Editor pref_editor = getSharedPreferences(
                getResources().getString(R.string.app_name), MODE_PRIVATE).edit();
        pref_editor.putString(ApplicationConstant.USERNAME_PREF_KEY,token.getUserName());
        pref_editor.putString(ApplicationConstant.TOKEN_PREF_KEY, token.getAccessToken());
        pref_editor.putString(ApplicationConstant.FIRSTNAME_PREF_KEY, token.getfirstName());
        pref_editor.apply();
        setResult(RESULT_OK,getIntent());
        finish();
    }

    public void onLoginFailed() {
        txt_error.setText("username or password is incorrect");
        btn_login.setEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode== ApplicationConstant.SIGNUP_REQUEST)
        {
            //log user in automatically
            if(resultCode==RESULT_OK) {

                AccessToken token = data.getParcelableExtra("token");
                if (token != null) {
                    onLoginSuccess(token);
                }
            }
        }
    }
}
