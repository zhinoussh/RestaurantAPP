package com.android.shahkar.andelosapp.activities;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.adapters.CategoryPagerAdapter;
import com.android.shahkar.andelosapp.models.RestaurantCategory;
import com.android.shahkar.andelosapp.network.CategoryService;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private CategoryService categoryServcie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryServcie = new CategoryService();


        categoryServcie.CategoryList(new CategoryService.ResultCallback() {
            @Override
            public void OnResultReady(List<RestaurantCategory> categoryList) {
                if (categoryList != null) {
                    ViewPager pager_category = (ViewPager) findViewById(R.id.pager_category);
                    PagerAdapter da = new CategoryPagerAdapter(getSupportFragmentManager(), categoryList);
                    pager_category.setAdapter(da);
                }
                else
                    Toast.makeText(MainActivity.this,"no category",Toast.LENGTH_LONG).show();
            }
        });

    }

}
