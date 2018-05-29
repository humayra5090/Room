package com.example.sayma.roomdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * Created by Sayma on 5/17/2018.
 */

public class DataHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "room.db";
    public static final int DATABASE_VERSION = 1;
    public static final String LOAN_TYPE = "type"; // 0 = supplied, 1 = borrowed
    public static final String NAME = "name";
    public static final String AMOUNT = "amount";
    public static final String DATE = "date";
    public static final String RETURN_DATE = "return_date";
    public static final String TRANSACTION_TYPE = "deal_type"; // 0 = pending, 1 = done
    public static final String LOAN_TABLE_NAME = "loan_tracker";
    public static final Uri LOAN_URI = Uri
            .parse("sqlite://com.example.sayma.loantracker/loan_tracker");

    private static DataHelper instance = null;
    private SQLiteDatabase readableDatabase;

    public DataHelper(Context context, String name){
        super(context, name, null, 1);

    }
    SQLiteDatabase db;


    public static DataHelper getInstance(Context c){
        if(instance == null){
            instance = new DataHelper(c, DATABASE_NAME);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists loan_tracker " +
                " ( _id integer primary key autoincrement, type integer not null," +
                " name text not null, amount text not null, date long not null," +
                " return_date long, deal_type integer not null ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists loan_tracker");
        onCreate(sqLiteDatabase);
    }

    public void insert(Context c, String name, String amount, int type){
        try{
            ContentValues cv = new ContentValues();
            cv.put(NAME, name);
            cv.put(AMOUNT, amount);
            cv.put(LOAN_TYPE, type);
            cv.put(DATE, System.currentTimeMillis());
            cv.put(RETURN_DATE, System.currentTimeMillis() + 200000000);
            cv.put(TRANSACTION_TYPE, 0);
            long id = getWritableDatabase().insert(LOAN_TABLE_NAME, null, cv);
            c.getContentResolver().notifyChange(LOAN_URI, null);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Cursor getDataByType(){
        Cursor c = null;
        try{
            c = this.getReadableDatabase().rawQuery("select* from loan_tracker ", null);
        }catch (Exception e){
            e.printStackTrace();
        }

        return c;
    }



}
