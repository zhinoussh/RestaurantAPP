package com.android.shahkar.andelosapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHandler {

    private Context mContext;
    protected SQLiteDatabase mDatabase;
    SQLiteOpenHelper mDbHelper;

    public DataBaseHandler(Context context)
    {
        mContext=context;
        mDbHelper=DataBaseHelper.getInstance(mContext);
    }

    protected void openDB() {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    protected void closeDB()
    {
        mDbHelper.close();
    }


}
