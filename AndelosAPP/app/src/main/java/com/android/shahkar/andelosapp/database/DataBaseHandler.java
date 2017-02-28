package com.android.shahkar.andelosapp.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 2/28/2017.
 */
public class DataBaseHandler {

    SQLiteOpenHelper dbhelper;

    public DataBaseHandler(DataBaseHelper dbHelper)
    {
        dbhelper=dbHelper;
    }

    public SQLiteDatabase openDB()
    {
        return dbhelper.getWritableDatabase();
    }

    public void closeDB()
    {
        dbhelper.close();
    }

}
