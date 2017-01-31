package com.android.shahkar.andelosapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.models.RestaurantCategory;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    private  static final String CATEGORY_KEY="Category_Key";
    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance(RestaurantCategory cat) {

        Bundle args = new Bundle();
        args.putParcelable(CATEGORY_KEY,cat);
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_category, container, false);

        Bundle args =getArguments();
        RestaurantCategory cat=args.getParcelable(CATEGORY_KEY);
        TextView txt_categoryName=(TextView)rootView.findViewById(R.id.txt_CategoryName);
        txt_categoryName.setText(cat.getCategoryName());

        // Inflate the layout for this fragment
        return rootView;
    }

}
