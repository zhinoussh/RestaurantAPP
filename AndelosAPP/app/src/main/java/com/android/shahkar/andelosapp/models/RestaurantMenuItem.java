package com.android.shahkar.andelosapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 2/1/2017.
 */
public class RestaurantMenuItem implements Parcelable {

    private int SubCategoryID;
    private int CategoryID;
    private String SubCategoryName;
    private double price;
    private String Description;

    public RestaurantMenuItem(int subCategoryID, int categoryID, String subCategoryName, double price, String description) {
        SubCategoryID = subCategoryID;
        CategoryID = categoryID;
        SubCategoryName = subCategoryName;
        this.price = price;
        Description = description;
    }

    public int getMenuItemID() {
        return SubCategoryID;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public String getMenuItemName() {
        return SubCategoryName;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return Description;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.SubCategoryID);
        dest.writeInt(this.CategoryID);
        dest.writeString(this.SubCategoryName);
        dest.writeDouble(this.price);
        dest.writeString(this.Description);
    }

    protected RestaurantMenuItem(Parcel in) {
        this.SubCategoryID = in.readInt();
        this.CategoryID = in.readInt();
        this.SubCategoryName = in.readString();
        this.price = in.readDouble();
        this.Description = in.readString();
    }

    public static final Parcelable.Creator<RestaurantMenuItem> CREATOR = new Parcelable.Creator<RestaurantMenuItem>() {
        @Override
        public RestaurantMenuItem createFromParcel(Parcel source) {
            return new RestaurantMenuItem(source);
        }

        @Override
        public RestaurantMenuItem[] newArray(int size) {
            return new RestaurantMenuItem[size];
        }
    };
}
