package com.android.shahkar.andelosapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 1/26/2017.
 */
public class RestaurantCategory implements Parcelable {

    private int CategoryID;
    private String CategoryName;

    public RestaurantCategory(int categoryId, String categoryName) {
        CategoryID = categoryId;
        CategoryName = categoryName;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public int getCategoryId() {
        return CategoryID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.CategoryID);
        dest.writeString(this.CategoryName);
    }

    protected RestaurantCategory(Parcel in) {
        this.CategoryID = in.readInt();
        this.CategoryName = in.readString();
    }

    public static final Parcelable.Creator<RestaurantCategory> CREATOR = new Parcelable.Creator<RestaurantCategory>() {
        @Override
        public RestaurantCategory createFromParcel(Parcel source) {
            return new RestaurantCategory(source);
        }

        @Override
        public RestaurantCategory[] newArray(int size) {
            return new RestaurantCategory[size];
        }
    };
}
