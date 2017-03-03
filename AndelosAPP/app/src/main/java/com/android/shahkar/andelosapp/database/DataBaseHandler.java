package com.android.shahkar.andelosapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.shahkar.andelosapp.models.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/28/2017.
 */
public class DataBaseHandler {

    private Context mContext;
    private SQLiteDatabase mDatabase;
    SQLiteOpenHelper mDbHelper;

    public DataBaseHandler(Context context)
    {
        mContext=context;
        mDbHelper=DataBaseHelper.getInstance(mContext);
    }

    private void openDB() {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    private void closeDB()
    {
        mDbHelper.close();
    }

    public void insertOrder(Order o)
    {
        openDB();
        ContentValues orderValues=o.toValues();
        mDatabase.insert("OrderTable",null,orderValues);
        closeDB();
    }

    public List<Order> getOrderList(){
        List<Order> orders=new ArrayList<Order>();
        String [] select_columns=new String[]{"MenuItemName","MenuItemCount","price"};
        Cursor cursor= mDatabase.query("OrderTable",select_columns,null,null,null,null,null);

        while (cursor.moveToNext())
        {
            Order orderItem=new Order();
            orderItem.setMenuItemName(cursor.getString(cursor.getColumnIndex("MenuItemName")));
            orderItem.setMenuItemCount(cursor.getInt(cursor.getColumnIndex("MenuItemCount")));
            orderItem.setPrice(cursor.getDouble(cursor.getColumnIndex("price")));
            orders.add(orderItem);
        }
        return  orders;
    }

}
