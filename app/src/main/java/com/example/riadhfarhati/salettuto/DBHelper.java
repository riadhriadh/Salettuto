package com.example.riadhfarhati.salettuto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by i on 25/05/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_TABLE_NAME = "salets";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_JOUR = "date";
    public static final String CONTACTS_COLUMN_fajr = "fajr";
    public static final String CONTACTS_COLUMN_shurooq = "shurooq";
    public static final String CONTACTS_COLUMN_dhuhr = "dhuhr";
    public static final String CONTACTS_COLUMN_asr = "asr";
    public static final String CONTACTS_COLUMN_maghrib = "maghrib";
    public static final String CONTACTS_COLUMN_isha = "isha";
    public static final String CONTACTS_TABLE_NAMEIFO = "info";
    public static final String CONTACTS_COLUMN_IDD = "id";
    public static final String CONTACTS_COLUMN_ANNE = "anne";
    public static final String CONTACTS_COLUMN_GOUVERNERA = "gouvern";
    public static final String CONTACTS_COLUMN_PAYE = "paye";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
        db.execSQL(
                "create table salets " +
                        "(id integer primary key, date text,fajr text,shurooq text, dhuhr text,asr text,maghrib text,isha text)"
        );
            db.execSQL(
                    "create table info " +
                            "(id integer primary key, anne text,gouvern text,paye text)"
            );
    }catch (SQLException e){
        Log.e("dbex",e.toString());
    }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS salets");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS info");
        onCreate(db);

    }

    public boolean insertSalets(String date, String fajr, String shurooq, String dhuhr, String asr, String maghrib, String isha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("fajr", fajr);
        contentValues.put("shurooq", shurooq);
        contentValues.put("dhuhr", dhuhr);
        contentValues.put("asr", asr);
        contentValues.put("maghrib", maghrib);
        contentValues.put("isha", isha);
try {
    db.insert("salets", null, contentValues);
    return true;
}catch (Exception e){
    Log.e("erreur",e.toString());
    return false;
}


    }


    public boolean insertInfo(String anne, String gouvern, String paye) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS info");
        onCreate(db);
        ContentValues contentValues = new ContentValues();
        contentValues.put("anne", anne);
        contentValues.put("gouvern", gouvern);
        contentValues.put("paye", paye);

        try {
            db.insert("info", null, contentValues);
            return true;
        }catch (Exception e){
            Log.e("erreur",e.toString());
            return false;
        }


    }









    public Cursor getDatainfo() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM info ", null );
        return res;
    }
    public Cursor getData(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM salets WHERE date ='"+date+"'", null );
        return res;
    }

}