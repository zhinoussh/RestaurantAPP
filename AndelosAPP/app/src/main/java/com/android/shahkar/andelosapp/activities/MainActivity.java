package com.android.shahkar.andelosapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.fragments.ErrorDialogFragment;
import com.android.shahkar.andelosapp.utils.NetworkConnectivity;
import com.android.shahkar.andelosapp.utils.ScreenUtility;


public class MainActivity extends AppCompatActivity {

    public static boolean isWideScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkConnectivity netStatus=new NetworkConnectivity(this);
        if(!netStatus.checkNetworkStatus())
            showConnectionErrorDialog();
        else {
            FrameLayout subCategoryContainer = (FrameLayout) findViewById(R.id.menu_fragment_container);
            isWideScreen = (subCategoryContainer != null);
//            ScreenUtility su=new ScreenUtility(this);
//            if(su.getDpWidth()>=600)
//                isWideScreen=true;
//            else
//                isWideScreen=false;
//            Toast.makeText(this,"wide: "+su.getDpWidth()+isWideScreen,Toast.LENGTH_LONG).show();

        }

    }

    private void showConnectionErrorDialog() {
        ErrorDialogFragment dialog=new ErrorDialogFragment();
        dialog.setCancelable(false);
        dialog.show(getFragmentManager(),"CONNECTION ERROR DIALOG");
    }


}
