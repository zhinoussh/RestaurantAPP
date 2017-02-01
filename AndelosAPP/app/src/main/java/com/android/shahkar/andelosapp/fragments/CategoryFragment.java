package com.android.shahkar.andelosapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.models.RestaurantCategory;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    private  static final String CATEGORY_KEY="Category_Key";
    private static final String PHOTO_BASE_URL="http://10.0.2.2:8001/Upload/Category/";

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
        try {
            Bundle args = getArguments();
            RestaurantCategory cat = args.getParcelable(CATEGORY_KEY);

            TextView txt_categoryName = (TextView) rootView.findViewById(R.id.txt_CategoryName);
            txt_categoryName.setText(cat.getCategoryName());

            ImageView img_category = (ImageView) rootView.findViewById(R.id.img_category);

            Context c = getActivity().getApplicationContext();

            Picasso.with(c)
                    .load(PHOTO_BASE_URL+cat.getCategoryId()+".jpg")
                    .fit().into(img_category);

        }
        catch (Exception ex)
        {
            Log.d("OnViewPager","Error in getting category: "+ex.toString());
        }
        // Inflate the layout for this fragment
        return rootView;
    }

}
