package com.android.shahkar.andelosapp.activities;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.adapters.CategoryPagerAdapter;
import com.android.shahkar.andelosapp.models.RestaurantCategory;
import com.android.shahkar.andelosapp.network.CategoryService;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private CategoryService categoryServcie;
    private static int swap_position=0;
    private ViewPager pager_category;
    private int categoryCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryServcie = new CategoryService();
        categoryServcie.CategoryList(new CategoryService.ResultCallback() {
            @Override
            public void OnResultReady(List<RestaurantCategory> categoryList) {
                if (categoryList != null) {
                    categoryCount=categoryList.size();
                    pager_category = (ViewPager) findViewById(R.id.pager_category);
                    PagerAdapter da = new CategoryPagerAdapter(getSupportFragmentManager(), categoryList);
                    pager_category.setAdapter(da);
                }
                else
                    Toast.makeText(MainActivity.this,"no category",Toast.LENGTH_LONG).show();
            }
        });

    }

    public void btnSwapRight_Click(View view) {
       if(swap_position<categoryCount-1)
            swap_position++;
        pager_category.setCurrentItem(swap_position);
    }

    public void btnSwapLeft_Click(View view) {
        if(swap_position>0)
            swap_position--;
        pager_category.setCurrentItem(swap_position);
    }
}
