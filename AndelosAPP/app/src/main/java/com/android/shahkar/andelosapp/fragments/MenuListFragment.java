package com.android.shahkar.andelosapp.fragments;


import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.adapters.MenuListAdapter;
import com.android.shahkar.andelosapp.models.RestaurantMenuItem;
import com.android.shahkar.andelosapp.network.APIService;
import com.android.shahkar.andelosapp.network.RestService;
import com.android.shahkar.andelosapp.network.ResultCallBack;
import com.android.shahkar.andelosapp.network.MenuService;

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

            APIService api = RestService.getAPIService();
            int CategoryID = getArguments().getInt("CategoryID");
            if (api != null && CategoryID > 0) {

                MenuService service = new MenuService(api.getMenuList(CategoryID));
                service.FetchList(new ResultCallBack<RestaurantMenuItem>() {
                    @Override
                    public void OnResultReady(List<RestaurantMenuItem> return_list) {
                        MenuListAdapter da = new MenuListAdapter(getContext()
                                , R.layout.menu_list_item, return_list);
                        setListAdapter(da);
                    }
                });
            }
        } catch (Exception ex) {
            Log.d("OnMenuFragment: ", ex.toString());
        }
        return rootView;
    }

}
