package com.example.kaiwang.soapbox;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kaiwang on 25.8.2015.
 */
public class UserDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "USERINFO.DB";
    private static final int DATABASE_VSERSION = 1;
    private static final String CREATE_QUERY =
            "CREATE TABLE "+ UserInformation.NewUserInfo.TABLE_NAME+"("+ UserInformation.NewUserInfo.USER_NAME+" TEXT,"+
                    UserInformation.NewUserInfo.USER_ID+" TEXT);";
    public UserDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VSERSION);
        Log.e("DATABASE_OPERATIONS", "Database created / opened ...");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE_OPERATIONS", "Table created ...");

    }

    public void addInformation(String name, String id, SQLiteDatabase db){

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserInformation.NewUserInfo.USER_NAME, name);
        contentValues.put(UserInformation.NewUserInfo.USER_ID, id);
        db.insert(UserInformation.NewUserInfo.TABLE_NAME, null, contentValues);
        Log.e("DATABASE_OPERATIONS", "One row inserted ...");
    }

    public Cursor getInformation(SQLiteDatabase db){

        Cursor cursor;
        String[] projections = {UserInformation.NewUserInfo.USER_NAME, UserInformation.NewUserInfo.USER_ID};
        cursor = db.query(UserInformation.NewUserInfo.TABLE_NAME, projections, null, null, null, null, null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
