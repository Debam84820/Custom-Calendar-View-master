package com.stacktips.calendar.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.stacktips.calendar.model.AddDataModel;

/**
 * Created by User on 3/13/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;
    private SQLiteDatabase mSqLiteDatabase;

    private static final String DB_PATH = "data/data/com.stacktips.calendar/databases/";
    private static final String DB_NAME = "DEMO.sqlite";

    private static final String DB_DETAILS = DB_PATH+DB_NAME;

    private static final String TABLE_NAME = "demo_table";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        mContext = context;
        mSqLiteDatabase = this.getWritableDatabase();
    }

    public synchronized void close(){
        if (mSqLiteDatabase!= null)
            mSqLiteDatabase.close();
            super.close();
    }

    private void open(){
        try {
            mSqLiteDatabase = SQLiteDatabase.openDatabase(DB_DETAILS, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e){
            Toast.makeText(mContext, "Problem to open db", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" TEXT,"
                +EMAIL+" TEXT NOT NULL UNIQUE,"+PASSWORD+" TEXT"+")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void addData(AddDataModel model){
        open();
        ContentValues values = new ContentValues();
        values.put(NAME, model.getName());
        values.put(EMAIL, model.getEmail());
        values.put(PASSWORD, model.getPassword());

        mSqLiteDatabase.insert(TABLE_NAME, null, values);
        mSqLiteDatabase.close();
    }

    /*public AddDataModel checkDuplicate(){

    }*/

}
