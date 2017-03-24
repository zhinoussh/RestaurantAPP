package com.android.shahkar.andelosapp.models;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

public class UserProfile implements Parcelable {


    private String FirstName;
    private String LastName;
    private String UserName;
    private String PhoneNumber;
    private String Address;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public ContentValues toValues(){
        ContentValues values=new ContentValues(5);

        values.put("FirstName",FirstName);
        values.put("LastName",LastName);
        values.put("PhoneNumber",PhoneNumber);
        values.put("Address",Address);
        values.put("UserName ",UserName);

        return  values;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.FirstName);
        dest.writeString(this.LastName);
        dest.writeString(this.UserName);
        dest.writeString(this.PhoneNumber);
        dest.writeString(this.Address);
    }

    public UserProfile() {
    }

    protected UserProfile(Parcel in) {
        this.FirstName = in.readString();
        this.LastName = in.readString();
        this.UserName = in.readString();
        this.PhoneNumber = in.readString();
        this.Address = in.readString();
    }

    public static final Parcelable.Creator<UserProfile> CREATOR = new Parcelable.Creator<UserProfile>() {
        @Override
        public UserProfile createFromParcel(Parcel source) {
            return new UserProfile(source);
        }

        @Override
        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };
}
