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
import com.android.shahkar.andelosapp.network.APIService;
import com.android.shahkar.andelosapp.network.LogoutService;
import com.android.shahkar.andelosapp.network.ResultCallBackObject;
import com.android.shahkar.andelosapp.network.ServiceGenerator;
import com.android.shahkar.andelosapp.utils.ApplicationConstant;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class ProfileFragment extends Fragment {
    ProgressBar progress_logout;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_profile, container, false);

        Typeface font = Typeface.DEFAULT.createFromAsset(getActivity().getAssets()
                , "fonts/Ubuntu-Medium.ttf");

        progress_logout = (ProgressBar)rootView.findViewById(R.id.user_progress);

        Button btn_logout = (Button)rootView.findViewById(R.id.btn_logout);
        btn_logout.setTypeface(font);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutProcess();
            }
        });

        TextView txt_EditProfile=(TextView)rootView.findViewById(R.id.txt_enter_details);
        txt_EditProfile.setTypeface(font);

        return rootView;
    }

    private void logoutProcess() {

        final SharedPreferences pref = getActivity().getSharedPreferences(
                getResources().getString(R.string.app_name), Context.MODE_PRIVATE);

        String authToken = pref.getString(ApplicationConstant.TOKEN_PREF_KEY, null);
        APIService api = ServiceGenerator.createService(authToken);
        Call<ResponseBody> call_logout = api.LogoutUser();
        LogoutService service = new LogoutService(call_logout);
        service.PostObject(new ResultCallBackObject() {
            @Override
            public void OnResultReady(Object return_object, String message) {
                if (message == "Success") {
                    pref.edit().clear().commit();
                    getActivity().setResult(getActivity().RESULT_OK, getActivity().getIntent());
                    getActivity().finish();
                } else
                    Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();

            }
        }, progress_logout);

    }

}
