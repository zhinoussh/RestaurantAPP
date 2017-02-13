package com.android.shahkar.andelosapp.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.AvoidXfermode;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.activities.LoginActivity;

public class TopbarFragment extends Fragment {


    public TopbarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView= inflater.inflate(R.layout.fragment_topbar, container, false);

        Typeface font_AppBar=Typeface.DEFAULT.createFromAsset(getActivity().getAssets(),"fonts/LobsterTwo-Bold.ttf");
        TextView txt_AppName=(TextView)rootView.findViewById(R.id.txt_AppName);
        txt_AppName.setTypeface(font_AppBar);

        Typeface font_welcome=Typeface.DEFAULT.createFromAsset(getActivity().getAssets(),"fonts/Ubuntu-Medium.ttf");
        TextView txt_welcome=(TextView)rootView.findViewById(R.id.txt_welcome);
        txt_welcome.setTypeface(font_welcome);

        Button btn_login=(Button)rootView.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent=new Intent(getContext(), LoginActivity.class);
                getContext().startActivity(loginIntent);
            }
        });

        SharedPreferences prefs=getActivity().getSharedPreferences(
                getResources().getString(R.string.app_name), getActivity().MODE_PRIVATE);
        String firstName= prefs.getString("firstname","");
        if(TextUtils.isEmpty(firstName)) {
            btn_login.setVisibility(View.VISIBLE);
            txt_welcome.setVisibility(View.INVISIBLE);
        }
        else
        {
            btn_login.setVisibility(View.INVISIBLE);
            txt_welcome.setVisibility(View.VISIBLE);
            txt_welcome.setText("Hi "+firstName+"!");
        }






        return rootView;
    }

}
