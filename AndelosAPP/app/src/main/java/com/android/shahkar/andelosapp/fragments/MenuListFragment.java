package com.android.shahkar.andelosapp.fragments;


import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.adapters.MenuListAdapter;
import com.android.shahkar.andelosapp.models.RestaurantMenuItem;
import com.android.shahkar.andelosapp.network.APIService;
import com.android.shahkar.andelosapp.network.ServiceGenerator;
import com.android.shahkar.andelosapp.network.MenuService;
import com.android.shahkar.andelosapp.utils.ResultCallBackList;

import java.util.List;


public class MenuListFragment extends ListFragment {

    public MenuListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_menu_list, container, false);
        try {
            ProgressBar p= (ProgressBar) rootView.findViewById(R.id.menu_progress);
            p.setVisibility(View.VISIBLE);

            APIService api = ServiceGenerator.createService();
            int CategoryID = getArguments().getInt("CategoryID");
            if (api != null && CategoryID > 0) {

                MenuService service = new MenuService(api.getMenuList(CategoryID));
                service.FetchList(new ResultCallBackList<RestaurantMenuItem>() {
                    @Override
                    public void OnResultReady(List<RestaurantMenuItem> return_list) {
                        MenuListAdapter da = new MenuListAdapter(getContext()
                                , R.layout.menu_list_item, return_list);
                        setListAdapter(da);
                    }
                },p);
            }
        } catch (Exception ex) {
            Log.d("OnMenuFragment: ", ex.toString());
        }
        return rootView;
    }

}
