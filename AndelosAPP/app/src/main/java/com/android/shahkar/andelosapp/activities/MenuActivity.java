package com.android.shahkar.andelosapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.fragments.MenuListFragment;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

//        int CategoryID= (int) getIntent().getExtras().get("CategoryID");
//        MenuListFragment menu_fragment=MenuListFragment.newInstance(CategoryID);
//        getFragmentManager()
//                .beginTransaction()
//                .add(R.id.fragment_menu,menu_fragment)
//                .commit();
    }
}
