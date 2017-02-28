package com.android.shahkar.andelosapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.database.DataBaseHandler;
import com.android.shahkar.andelosapp.database.DataBaseHelper;
import com.android.shahkar.andelosapp.models.Order;
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
        final ViewHolder holder;
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) myContext).getLayoutInflater();
                convertView = inflater.inflate(R.layout.menu_list_item, parent, false);

                holder = new ViewHolder();
                holder.txt_MenuName = (TextView) convertView.findViewById(R.id.txt_SubCategoryName);
                holder.txt_MenuPrice = (TextView) convertView.findViewById(R.id.txt_SubCategoryPrice);
                holder.img_Menu = (ImageView) convertView.findViewById(R.id.img_subcategory);
                holder.btn_order = (Button) convertView.findViewById(R.id.btn_order);
                holder.txt_orderNum = (TextView) convertView.findViewById(R.id.txt_orderNum);

                convertView.setTag(holder);
            } else
                holder = (ViewHolder) convertView.getTag();


            final RestaurantMenuItem MenuItem = lst_menu.get(position);
            Typeface face = Typeface.createFromAsset(myContext.getAssets(),
                    "fonts/Ubuntu-Medium.ttf");
            holder.txt_MenuName.setTypeface(face);
            holder.txt_MenuName.setText(MenuItem.getMenuItemName());

            NumberFormat currency_format=NumberFormat.getCurrencyInstance();
            holder.txt_MenuPrice.setText(currency_format.format(MenuItem.getPrice()));
            holder.txt_MenuPrice.setTypeface(face);

            Picasso.with(getContext())
                    .load(PHOTO_BASE_URL + MenuItem.getMenuItemID() + ".jpg")
                    .resize(200,200)
                    .into(holder.img_Menu);

            holder.btn_order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataBaseHandler dbHandler=new DataBaseHandler(myContext);
                    int orderNum=Integer.parseInt(holder.txt_orderNum.getText().toString());
                    Order newOrder=new Order(MenuItem.getMenuItemID()
                            ,MenuItem.getMenuItemName(),orderNum,MenuItem.getPrice());
                    dbHandler.insertOrder(newOrder);
                }
            });
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
        Button btn_order;
        TextView txt_orderNum;
    }

}
