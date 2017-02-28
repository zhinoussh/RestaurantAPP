package com.android.shahkar.andelosapp.models;


public class Order {

    private int OrderId;
    private int MenuItemId;
    private String MenuItemName;
    private int MenuItemCount;
    private double price;

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
}
