package com.android.shahkar.andelosapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.models.RestaurantMenuItem;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by User on 2/1/2017.
 */
public class MenuListAdapter extends ArrayAdapter<RestaurantMenuItem> {

    private Context myContext;
    private List<RestaurantMenuItem> lst_menu;
    private static final String PHOTO_BASE_URL="http://10.0.2.2:8001/Upload/Menu/";


    public MenuListAdapter(Context context, int resource, List<RestaurantMenuItem> menuList) {
        super(context, resource, menuList);
        myContext=context;
        lst_menu = menuList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) myContext).getLayoutInflater();
                convertView = inflater.inflate(R.layout.menu_list_item, parent, false);

                holder = new ViewHolder();
                holder.txt_MenuName = (TextView) convertView.findViewById(R.id.txt_SubCategoryName);
                holder.txt_MenuPrice = (TextView) convertView.findViewById(R.id.txt_SubCategoryPrice);
                holder.img_Menu = (ImageView) convertView.findViewById(R.id.img_subcategory);

                convertView.setTag(holder);
            } else
                holder = (ViewHolder) convertView.getTag();


            RestaurantMenuItem MenuItem = lst_menu.get(position);
            holder.txt_MenuName.setText(MenuItem.getMenuItemName());

            NumberFormat currency_format=NumberFormat.getCurrencyInstance();
            holder.txt_MenuPrice.setText(currency_format.format(MenuItem.getPrice()));

            Picasso.with(getContext())
                    .load(PHOTO_BASE_URL + MenuItem.getMenuItemID() + ".jpg")
                    .into(holder.img_Menu);
        }
        catch (Exception ex)
        {
            Log.d("OnMenuAdapter: ", ex.toString());

        }
        return convertView;
    }

    static class ViewHolder
    {
        TextView txt_MenuPrice;
        TextView txt_MenuName;
        ImageView img_Menu;
    }

}
