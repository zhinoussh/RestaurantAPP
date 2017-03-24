package com.android.shahkar.andelosapp.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.models.UserProfile;
import com.android.shahkar.andelosapp.network.APIService;
import com.android.shahkar.andelosapp.network.LogoutService;
import com.android.shahkar.andelosapp.network.ResultCallBackObject;
import com.android.shahkar.andelosapp.network.ServiceGenerator;
import com.android.shahkar.andelosapp.utils.ApplicationConstant;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class ProfileFragment extends Fragment {
    ProgressBar progress_logout;
    private static final String PROFILE_KEY = "Profile_Key";
    private ProfileFragmentListener mListener;

    TextView txt_firstname, txt_lastname, txt_address, txt_phone, txt_email;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(UserProfile profile) {

        Bundle args = new Bundle();
        args.putParcelable(PROFILE_KEY, profile);
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (!(context instanceof ProfileFragmentListener)) throw new AssertionError();
        mListener = (ProfileFragmentListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        Typeface font = Typeface.DEFAULT.createFromAsset(getActivity().getAssets()
                , "fonts/Ubuntu-Medium.ttf");
        TextView txt_EditProfile = (TextView) rootView.findViewById(R.id.txt_enter_details);
        txt_firstname = (TextView) rootView.findViewById(R.id.txt_profile_firstname);
        txt_lastname = (TextView) rootView.findViewById(R.id.txt_profile_lastname);
        txt_phone = (TextView) rootView.findViewById(R.id.txt_profile_PhoneNumber);
        txt_address = (TextView) rootView.findViewById(R.id.txt_profile_Address);
        txt_email = (TextView) rootView.findViewById(R.id.txt_profile_email);

        txt_firstname.setTypeface(font);
        txt_lastname.setTypeface(font);
        txt_phone.setTypeface(font);
        txt_address.setTypeface(font);
        txt_email.setTypeface(font);
        txt_EditProfile.setTypeface(font);

        progress_logout = (ProgressBar) getActivity().findViewById(R.id.user_progress);

        Button btn_logout = (Button) rootView.findViewById(R.id.btn_logout);
        btn_logout.setTypeface(font);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


        Button btn_save_profile = (Button) rootView.findViewById(R.id.btn_save_profile);
        btn_save_profile.setTypeface(font);
        btn_save_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_profile();
            }
        });


        UserProfile profile = getArguments().getParcelable(PROFILE_KEY);
        if (profile != null) {
            txt_firstname.setText(profile.getFirstName());
            txt_lastname.setText(profile.getLastName());
            txt_phone.setText(profile.getPhoneNumber());
            txt_address.setText(profile.getAddress());
            txt_email.setText(profile.getUserName());
        }

        return rootView;
    }

    private void save_profile() {
        UserProfile profile=new UserProfile();
        profile.setAddress(txt_address.getText().toString());
        profile.setPhoneNumber(txt_phone.getText().toString());
        profile.setFirstName(txt_firstname.getText().toString());
        profile.setLastName(txt_lastname.getText().toString());
        profile.setUserName(txt_email.getText().toString());

        mListener.onFragmentResult(ApplicationConstant.SAVE_PROFILE,profile);
    }

    private void logout() {
        mListener.onFragmentResult(ApplicationConstant.LOGOUT,null);
    }

    public interface ProfileFragmentListener {
        void onFragmentResult(String processName,UserProfile profile);
    }
}
