package com.android.shahkar.andelosapp.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.database.DataBaseHelper;
import com.android.shahkar.andelosapp.fragments.ErrorDialogFragment;
import com.android.shahkar.andelosapp.fragments.TopbarFragment;
import com.android.shahkar.andelosapp.network.NetworkConnectivity;
import java.io.IOException;
import java.sql.SQLException;


public class MainActivity extends AppCompatActivity {

    public static boolean isWideScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TopbarFragment topbarFragment=new TopbarFragment();
        getFragmentManager().beginTransaction().add(R.id.topbar_fragment_container
        ,topbarFragment).commit();

        NetworkConnectivity netStatus = new NetworkConnectivity(this);
        if (!netStatus.checkNetworkStatus())
            showConnectionErrorDialog();
        else {

            try {
                InitializeDataBase();
            } catch (SQLException e) {
                Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
            }

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

    @Override
    protected void onResume() {
        super.onResume();
        TopbarFragment topbarFragment=new TopbarFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.topbar_fragment_container,topbarFragment).commit();
    }

    private void InitializeDataBase() throws SQLException, IOException {
        DataBaseHelper dbHelper = DataBaseHelper.getInstance(this);
        dbHelper.createDataBase();
        dbHelper.openDataBase();
    }


    private void showConnectionErrorDialog() {
        ErrorDialogFragment dialog = new ErrorDialogFragment();
        dialog.setCancelable(false);
        dialog.show(getFragmentManager(), "CONNECTION ERROR DIALOG");
    }


}
