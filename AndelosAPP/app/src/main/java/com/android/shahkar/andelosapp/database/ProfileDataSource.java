package com.android.shahkar.andelosapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.android.shahkar.andelosapp.models.UserProfile;

public class ProfileDataSource extends DataBaseHandler {

    public ProfileDataSource(Context context) {
        super(context);
    }

    public UserProfile getUserProfile(String username){
        UserProfile profile=null;
        String[] select_columns=new String[]{"FirstName","LastName","PhoneNumber ","Address"};
        try {
            openDB();
            Cursor cursor = mDatabase.query("ProfileTable", select_columns, "UserName=?", new String[]{username}, null, null, null);
            if (cursor.moveToFirst()) {
                profile = new UserProfile();
                profile.setFirstName(cursor.getString(cursor.getColumnIndex("FirstName")));
                profile.setLastName(cursor.getString(cursor.getColumnIndex("LastName")));
                profile.setPhoneNumber(cursor.getString(cursor.getColumnIndex("PhoneNumber")));
                profile.setAddress(cursor.getString(cursor.getColumnIndex("Address")));
            }
            cursor.close();
            closeDB();
            return  profile;
        }
        catch (Exception ex)
        {
            closeDB();
            return null;
        }
    }

    public void InsertProfile(UserProfile profile,String old_username)
    {
        openDB();
        String select_profile="select Id from ProfileTable where UserName=?";
        Cursor cursor=mDatabase.rawQuery(select_profile,new String[]{old_username});
        if(cursor.moveToNext())
        {
            int profileID=cursor.getInt(cursor.getColumnIndex("Id"));
            ContentValues newValues=profile.toValues();
            mDatabase.update("ProfileTable",newValues,"Id=?",new String[]{String.valueOf(profileID)});
        }
        else {
            ContentValues insert_values=profile.toValues();
            mDatabase.insert("ProfileTable",null,insert_values);
        }
        cursor.close();
        closeDB();
    }
}
