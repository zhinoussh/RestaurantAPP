package com.android.shahkar.andelosapp.database;


import android.database.sqlite.*;
import java.io.*;
import java.sql.SQLException;
import android.content.Context;
import android.widget.Toast;

public class DataBaseHelper extends SQLiteOpenHelper{

    //The Android's default system path of your application database.
    private static final String DB_PATH = "/data/data/com.android.shahkar.andelosapp/databases/";
    private static final String DB_NAME = "restaurantDB.sqlite";
    public static final int DB_VERSION=2;

    private SQLiteDatabase mDataBase;
    private final Context mContext;
    private static DataBaseHelper mInstance = null;

    public static DataBaseHelper getInstance(Context ctx) {

        if (mInstance == null) {
            mInstance = new DataBaseHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
        this.mContext = context;
    }

    public void createDataBase() throws IOException{

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist
        }
        else{
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {

                throw new Error("Error copying database");
            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){
        File databasePath = mContext.getDatabasePath(DB_NAME);
        return databasePath.exists();
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = mContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException{

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        mDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {

        if(mDataBase != null)
            mDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Toast.makeText(mContext,"onCreate Database",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(mContext,"old version:"+ oldVersion+",new version:"+newVersion ,Toast.LENGTH_LONG).show();

//        String sql="CREATE TABLE [ProfileTable] (Id integer PRIMARY KEY AUTOINCREMENT,FirstName text" +
//                ",LastName text,PhoneNumber text,Address text,UserName text)";
//        db.execSQL(sql);
    }

    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.

}
