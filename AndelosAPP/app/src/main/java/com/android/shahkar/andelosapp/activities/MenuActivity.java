package com.android.shahkar.andelosapp.activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.fragments.MenuListFragment;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Typeface font_AppBar=Typeface.createFromAsset(getAssets(),"fonts/LobsterTwo-Bold.ttf");
        TextView txt_AppName=(TextView)findViewById(R.id.txt_AppName);
        txt_AppName.setTypeface(font_AppBar);

        try {
            int CategoryID =getIntent().getIntExtra("CategoryID",0);
            Bundle b=new Bundle();
            b.putInt("CategoryID",CategoryID);
            MenuListFragment menu_fragment =new MenuListFragment();
            menu_fragment.setArguments(b);
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.menu_fragment_container, menu_fragment)
                    .commit();
        }
        catch (Exception ex)
        {
            Log.d("Load Menu Fragment",ex.toString());
        }
    }
}
