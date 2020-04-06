/*
Author : Vy Dao
Course : CSIS 365
Assignment: Major Project 2
Due Date : 4/5/2020
Date handed : 4/5/2020
Description: The UserSQLHelper file where it handle all SQL Little
 */
package vy.com.covid19.covid19tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class UserSQLHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "User.db";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "UserTable";
    public static final String USER_ID = "ID";
    public static final String USER_USERNAME = "Username";
    public static final String USER_PASSWORD = "Password";
    public static final String USER_GENDER = "Gender"; //rdb
    public static final String USER_BIRTHDAY = "DOB"; //dtp
    public static final String USER_STATE = "State"; //Spinner
    public static final String USER_FAMILY_MEMBER = "AGE";

    public UserSQLHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + USER_ID + " INTEGER PRIMARY KEY, " + USER_USERNAME + " TEXT, "
            + USER_PASSWORD + " TEXT, " + USER_GENDER + " TEXT, "
            + USER_BIRTHDAY + " TEXT, "
            + USER_STATE + " TEXT, " + USER_FAMILY_MEMBER + " TEXT" + ")";
    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            //Create table
            db.execSQL(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Checking insert data
    public boolean addUserData(String tmpUsername, String tmpPassword, String tmpGender, String tmpBirthday, String tmpState,String tmpFamilyMember){
        SQLiteDatabase db = this.getWritableDatabase(); //get Write access
        ContentValues contentValues = new ContentValues(); // content values package to put data
        contentValues.put(USER_USERNAME,tmpUsername);
        contentValues.put(USER_PASSWORD,tmpPassword);
        contentValues.put(USER_GENDER,tmpGender);
        contentValues.put(USER_BIRTHDAY,tmpBirthday);
        contentValues.put(USER_STATE,tmpState);
        contentValues.put(USER_FAMILY_MEMBER,tmpFamilyMember);
        long insert_result = db.insert(TABLE_NAME,null,contentValues);
        if (insert_result > 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
