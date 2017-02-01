package com.android.shahkar.andelosapp.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.shahkar.andelosapp.fragments.CategoryFragment;
import com.android.shahkar.andelosapp.models.RestaurantCategory;

import java.util.List;

/**
 * Created by User on 1/26/2017.
 */
public class CategoryPagerAdapter extends FragmentStatePagerAdapter {

    private List<RestaurantCategory> lst_category;

    public CategoryPagerAdapter(FragmentManager fm, List<RestaurantCategory> lst_category) {
        super(fm);
        this.lst_category = lst_category;
    }

    @Override
    public Fragment getItem(int position) {
        return CategoryFragment.newInstance(lst_category.get(position));
    }

    @Override
    public int getCount() {
        return lst_category.size();
    }
}
