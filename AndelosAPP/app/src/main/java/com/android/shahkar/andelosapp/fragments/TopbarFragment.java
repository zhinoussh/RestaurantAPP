package com.android.shahkar.andelosapp.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.activities.CheckoutActivity;
import com.android.shahkar.andelosapp.activities.LoginActivity;
import com.android.shahkar.andelosapp.activities.UserActivity;
import com.android.shahkar.andelosapp.utils.ApplicationConstant;
import com.android.shahkar.andelosapp.network.NetworkConnectivity;

public class TopbarFragment extends Fragment {

    Button btn_login,btn_checkout,btn_back;
    TextView txt_welcome;

    public TopbarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_topbar, container, false);

        Typeface font_AppBar = Typeface.DEFAULT.createFromAsset(getActivity().getAssets(), "fonts/LobsterTwo-Bold.ttf");
        TextView txt_AppName = (TextView) rootView.findViewById(R.id.txt_AppName);
        txt_AppName.setTypeface(font_AppBar);

        Typeface font_welcome = Typeface.DEFAULT.createFromAsset(getActivity().getAssets(), "fonts/Ubuntu-Medium.ttf");
        txt_welcome = (TextView) rootView.findViewById(R.id.txt_welcome);
        txt_welcome.setTypeface(font_welcome);
        txt_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UserIntent = new Intent(getContext(), UserActivity.class);
                startActivityForResult(UserIntent, ApplicationConstant.USER_REQUEST);
            }
        });

        btn_login = (Button) rootView.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(getContext(), LoginActivity.class);
                startActivityForResult(loginIntent, ApplicationConstant.LOGIN_REQUEST);
            }
        });

        btn_checkout = (Button) rootView.findViewById(R.id.btn_checkout);
        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checkoutIntent = new Intent(getContext(), CheckoutActivity.class);
                startActivity(checkoutIntent);
            }
        });
        btn_back = (Button) rootView.findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        setTopBar();

        return rootView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ApplicationConstant.LOGIN_REQUEST) {
            if (resultCode == getActivity().RESULT_OK) {
                setTopBar();
            }
        } else if (requestCode == ApplicationConstant.USER_REQUEST) {
            if (resultCode == getActivity().RESULT_OK) {
                setTopBar();
            }

        }
    }

    private void setTopBar() {

        NetworkConnectivity net = new NetworkConnectivity(getActivity());

        if (net.isLoggedIn()) {
            SharedPreferences prefs = getActivity().getSharedPreferences(
                    getResources().getString(R.string.app_name), getActivity().MODE_PRIVATE);
            String firstName = prefs.getString(ApplicationConstant.FIRSTNAME_PREF_KEY, "");

            btn_login.setVisibility(View.INVISIBLE);
            txt_welcome.setVisibility(View.VISIBLE);
            txt_welcome.setText("Hi " + firstName + "!");
        } else {
            btn_login.setVisibility(View.VISIBLE);
            txt_welcome.setVisibility(View.INVISIBLE);
        }

        if (isAdded()) {
            String activityName = getActivity().getClass().getSimpleName();
            if(activityName.equals("CheckoutActivity")) {
                btn_checkout.setVisibility(View.INVISIBLE);
                btn_back.setVisibility(View.VISIBLE);
            }
            else
            {
                btn_checkout.setVisibility(View.VISIBLE);
                btn_back.setVisibility(View.INVISIBLE);
            }
        }
    }

}
