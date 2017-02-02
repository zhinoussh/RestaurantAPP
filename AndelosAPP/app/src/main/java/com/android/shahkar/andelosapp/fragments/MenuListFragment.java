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

    public static MenuListFragment newInstance(int CategoryID) {
        Bundle args = new Bundle();
        args.putInt("CategoryID",CategoryID);
        MenuListFragment fragment = new MenuListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_menu_list, container, false);
        try {

           APIService api = RestService.getAPIService();
            //int CategoryID=getArguments().getInt("CategoryID");
            if (api != null) {
                MenuService service = new MenuService(api.getMenuList(1));
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
            Log.d("OnSubCategory", ex.toString());
        }
        return rootView;
    }

}
