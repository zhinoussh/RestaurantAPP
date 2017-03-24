package com.android.shahkar.andelosapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.android.shahkar.andelosapp.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDataSource extends DataBaseHandler{

    public OrderDataSource(Context context) {
            super(context);
    }

    public void insertOrder(Order o)
    {
        openDB();
        String select_order="select MenuItemCount from OrderTable where MenuItemId=?";
        String[] select_params=new String[]{String.valueOf(o.getMenuItemId())};
        Cursor cursor=mDatabase.rawQuery(select_order,select_params);
        if(cursor.moveToNext()) {
            int previous_count=cursor.getInt(cursor.getColumnIndex("MenuItemCount"));
            int new_count=previous_count+o.getMenuItemCount();
            ContentValues new_orderValues = new ContentValues();
            new_orderValues.put("MenuItemCount",new_count);
            mDatabase.update("OrderTable", new_orderValues,"MenuItemId=?",select_params);
        }
        else {
            ContentValues orderValues = o.toValues();
            mDatabase.insert("OrderTable", null, orderValues);
        }
        cursor.close();
        closeDB();
    }

    public List<Order> getOrderList(){
        List<Order> orders=new ArrayList<Order>();
        String [] select_columns=new String[]{"OrderId","MenuItemName","MenuItemCount","price"};
        openDB();
        Cursor cursor= mDatabase.query("OrderTable",select_columns,null,null,null,null,null);

        while (cursor.moveToNext())
        {
            Order orderItem=new Order();
            orderItem.setOrderId(cursor.getInt(cursor.getColumnIndex("OrderId")));
            orderItem.setMenuItemName(cursor.getString(cursor.getColumnIndex("MenuItemName")));
            orderItem.setMenuItemCount(cursor.getInt(cursor.getColumnIndex("MenuItemCount")));
            orderItem.setPrice(cursor.getDouble(cursor.getColumnIndex("price")));
            orders.add(orderItem);
        }
        cursor.close();
        closeDB();
        return  orders;
    }

    public void RemoveOrderItem(int orderId) {
        openDB();
        mDatabase.delete("OrderTable","OrderId=?",new String[]{String.valueOf(orderId)});
        closeDB();
    }

    public void Add_Subtract_OrderNumber(int orderId, int number) {
        String rawSql="update OrderTable set MenuItemCount=MenuItemCount+? where OrderId=?";
        openDB();
        mDatabase.execSQL(rawSql,new Object[]{number,orderId});
        closeDB();
    }

    public int getOrderCount() {
        int orderCount=0;
        openDB();
        String select_count="select count(*) from OrderTable";
        Cursor cursor=mDatabase.rawQuery(select_count,null);
        if(cursor.moveToNext())
            orderCount=cursor.getInt(0);
        return orderCount;
    }
}
