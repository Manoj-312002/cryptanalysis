package com.example.learn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhandler extends SQLiteOpenHelper {
    public DBhandler(Context cnt){
        super(cnt , "cm" , null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cr = "CREATE TABLE tb( n INTEGER PRIMARY kEY , s TEXT )";
        db.execSQL(cr);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS tb");
        onCreate(db);
    }

    void add(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("s" , "Manoj");
        cv.put("n" , 1);
        db.insert("tb" , null , cv);
    }

    String get(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.query("tb" , new String[] {"n" , "s"} ,
                   "n=?" , new String[]{"1"}  , null ,
                    null , null , null);

        if( cr != null ) cr.moveToFirst();
        return cr.getString(0) + ":" + cr.getString(1);
    }
}
