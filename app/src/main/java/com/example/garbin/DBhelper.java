package com.example.garbin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context,"Login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table users(username Text primary key,password Text ,phone Text ,city Text ,address Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String username,String password,String phoneno,String cityad,String addressno) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("phone",phoneno);
        contentValues.put("city",cityad);
        contentValues.put("address",addressno);
        long result = myDB.insert("users",null,contentValues);

        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean checkusername(String username) {

        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ?",new String[] {username});

        if(cursor.getCount()>0) {
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean checkusernamePassword(String username ,String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ? and password =?",new String[] {username,password});
        if (cursor.getCount()>0) {
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean updatepass(String username,String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password",password);
        long result = myDB.update("users",contentValues,"username = ?", new String[] {username});

        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean checkusernamePasswordPhonenoCitynoAddressno(String username ,String password, String phoneno, String cityad, String addressno) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ? and password =? and phone = ? and city = ? and address = ?",new String[] {username,password,phoneno,cityad,addressno});
        if (cursor.getCount()>0) {
            return true;
        }
        else {
            return false;
        }
    }
}