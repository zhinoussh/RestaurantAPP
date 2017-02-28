package com.android.shahkar.andelosapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.shahkar.andelosapp.models.Order;

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

}
