package com.android.shahkar.andelosapp.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.adapters.CategoryPagerAdapter;
import com.android.shahkar.andelosapp.models.RestaurantCategory;
import com.android.shahkar.andelosapp.network.APIService;
import com.android.shahkar.andelosapp.network.CategoryService;
import com.android.shahkar.andelosapp.network.RestService;
import com.android.shahkar.andelosapp.network.ResultCallBack;

import java.util.List;

import retrofit2.Call;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryPageFragment extends Fragment {

    private static int swap_position=0;
    private ViewPager pager_category;
    private int categoryCount=0;

    public CategoryPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View fragmentView=inflater.inflate(R.layout.fragment_category_page, container, false);

        ProgressBar p= (ProgressBar) fragmentView.findViewById(R.id.category_progress);
        p.setVisibility(View.VISIBLE);
        APIService api= RestService.getAPIService();
        if(api!=null) {
            CategoryService service = new CategoryService(api.getCategoryList());
            service.FetchList(new ResultCallBack<RestaurantCategory>() {
                @Override
                public void OnResultReady(List<RestaurantCategory> categoryList) {
                    if (categoryList != null) {
                        categoryCount = categoryList.size();
                        pager_category = (ViewPager) fragmentView.findViewById(R.id.pager_category);
                        PagerAdapter da = new CategoryPagerAdapter(getFragmentManager(), categoryList);
                        pager_category.setAdapter(da);
                    } else
                        Toast.makeText(getActivity().getBaseContext(), "no category", Toast.LENGTH_LONG).show();
                }
            },p);
        }

        TextView txt_swap=(TextView)fragmentView.findViewById(R.id.txt_swap);
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/Ubuntu-Medium.ttf");
        txt_swap.setTypeface(face);
        Button swapRightButton=(Button)fragmentView.findViewById(R.id.btn_swap_right);
        swapRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swap_position<categoryCount-1)
                    swap_position++;
                pager_category.setCurrentItem(swap_position);
            }
        });

        Button swapLeftButton=(Button)fragmentView.findViewById(R.id.btn_swap_left);
        swapLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swap_position>0)
                    swap_position--;
                pager_category.setCurrentItem(swap_position);
            }
        });

        // Inflate the layout for this fragment
        return fragmentView;
    }


}
