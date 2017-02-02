package com.android.shahkar.andelosapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.models.RestaurantMenuItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by User on 2/1/2017.
 */
public class MenuListAdapter extends ArrayAdapter<RestaurantMenuItem> {

    private List<RestaurantMenuItem> lst_menu;
    private static final String PHOTO_BASE_URL="http://10.0.2.2:8001/Upload/Menu/";


    public MenuListAdapter(Context context, int resource, List<RestaurantMenuItem> menuList) {
        super(context, resource, menuList);

        lst_menu = menuList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            convertView = inflater.inflate(R.layout.menu_list_item, parent, false);
        }

        RestaurantMenuItem subCat = lst_menu.get(position);

        TextView txt_SubCategoryName = (TextView) convertView.findViewById(R.id.txt_SubCategoryName);
        txt_SubCategoryName.setText(subCat.getMenuItemName());

        TextView txt_SubCategoryPrice = (TextView) convertView.findViewById(R.id.txt_SubCategoryPrice);
        txt_SubCategoryPrice.setText(subCat.getPrice()+"");

        ImageView img_subCategory=(ImageView)convertView.findViewById(R.id.img_subcategory);
        Picasso.with(getContext())
                .load(PHOTO_BASE_URL+subCat.getMenuItemID()+".jpg")
                .into(img_subCategory);

        return convertView;
    }
}
