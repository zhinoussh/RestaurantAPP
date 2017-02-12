package com.android.shahkar.andelosapp.activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.shahkar.andelosapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Typeface font_AppBar=Typeface.DEFAULT.createFromAsset(getAssets(),"fonts/LobsterTwo-Bold.ttf");
        TextView txt_AppName=(TextView)findViewById(R.id.txt_loginLogo);
        txt_AppName.setTypeface(font_AppBar);
    }
}
