package com.android.shahkar.andelosapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.fragments.ErrorDialogFragment;
import com.android.shahkar.andelosapp.utils.NetworkConnectivity;


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
            FrameLayout subCategoryContainer = (FrameLayout) findViewById(R.id.SubCategory_fragment_container);
            isWideScreen = (subCategoryContainer != null);
        }

    }

    private void showConnectionErrorDialog() {
        ErrorDialogFragment dialog=new ErrorDialogFragment();
        dialog.setCancelable(false);
        dialog.show(getFragmentManager(),"CONNECTION ERROR DIALOG");
    }


}
