package com.android.shahkar.andelosapp.models;


import android.content.ContentValues;

public class Order {

    private int OrderId;
    private int MenuItemId;
    private String MenuItemName;
    private int MenuItemCount;
    private double price;

    public Order() {
    }

    public Order(int menuItemId, String menuItemName, int menuItemCount, double price) {
       MenuItemId = menuItemId;
        MenuItemName = menuItemName;
        MenuItemCount = menuItemCount;
        this.price = price;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getMenuItemId() {
        return MenuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        MenuItemId = menuItemId;
    }

    public String getMenuItemName() {
        return MenuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        MenuItemName = menuItemName;
    }

    public int getMenuItemCount() {
        return MenuItemCount;
    }

    public void setMenuItemCount(int menuItemCount) {
        MenuItemCount = menuItemCount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ContentValues toValues(){
        ContentValues values=new ContentValues(5);

        values.put("MenuItemId",MenuItemId);
        values.put("MenuItemName",MenuItemName);
        values.put("MenuItemCount",MenuItemCount);
        values.put("price",price);

        return  values;
    }


}
