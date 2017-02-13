package com.android.shahkar.andelosapp.activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.models.User;
import com.android.shahkar.andelosapp.network.APIService;
import com.android.shahkar.andelosapp.network.RegisterService;
import com.android.shahkar.andelosapp.network.RestService;
import com.android.shahkar.andelosapp.utils.ResultCallBackObject;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class SignupActivity extends AppCompatActivity {

    TextView txt_email, txt_password,txt_firstname,txt_lastname,txt_error;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Typeface font_logo = Typeface.DEFAULT.createFromAsset(getAssets(), "fonts/LobsterTwo-Bold.ttf");
        TextView txt_Logo = (TextView) findViewById(R.id.txt_signupLogo);
        txt_Logo.setTypeface(font_logo);

        txt_error= (TextView) findViewById(R.id.txt_error_signUp);
        txt_email = (TextView) findViewById(R.id.txt_signup_email);
        txt_password = (TextView) findViewById(R.id.txt_signup_password);
        txt_firstname = (TextView) findViewById(R.id.txt_signup_firstname);
        txt_lastname = (TextView) findViewById(R.id.txt_signup_lastname);

        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupProcess();
            }
        });

        TextView link_login=(TextView)findViewById(R.id.link_login);
        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             finish();
            }
        });
    }

    private void signupProcess() {
        if (!validateSignup()) {
            onSignupFailed();
            return;
        }

        btn_signup.setEnabled(false);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.signup_progress);
        progressBar.setVisibility(View.VISIBLE);

        APIService api= RestService.getAPIService();
        if(api!=null)
        {
            String email = txt_email.getText().toString();
            String password = txt_password.getText().toString();
            String firstName = txt_firstname.getText().toString();
            String lastName = txt_lastname.getText().toString();

            User user=new User(firstName,lastName,email,password);
            Call<ResponseBody> call_register=api.Register(user);
            RegisterService registerService=new RegisterService(call_register);
            registerService.PostObject(new ResultCallBackObject() {
                @Override
                public void OnResultReady(Object return_object, String message) {
                    if(message=="Success")
                    {
                        setResult(RESULT_OK);
                        finish();
                    }
                    else {
                        txt_error.setText(message);
                        btn_signup.setEnabled(true);
                    }
                }
            },progressBar);

        }

    }

    public void onSignupSuccess() {
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        txt_error.setText("Login failed");
        btn_signup.setEnabled(true);
    }

    public boolean validateSignup() {
        boolean valid = true;

        String firstname = txt_firstname.getText().toString();
        String lastname = txt_lastname.getText().toString();
        String email = txt_email.getText().toString();
        String password = txt_password.getText().toString();

        if (firstname.isEmpty() || firstname.length() < 3) {
            txt_firstname.setError("at least 3 characters");
            valid = false;
        } else {
            txt_firstname.setError(null);
        }

        if (lastname.isEmpty() || lastname.length() < 3) {
            txt_lastname.setError("at least 3 characters");
            valid = false;
        } else {
            txt_lastname.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txt_email.setError("enter a valid email address");
            valid = false;
        } else {
            txt_email.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            txt_password.setError("at least 6 alphanumeric characters");
            valid = false;
        } else {
            txt_password.setError(null);
        }

        return valid;
    }
}

