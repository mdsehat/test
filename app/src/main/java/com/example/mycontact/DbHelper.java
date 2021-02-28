package com.example.mycontact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.shapes.OvalShape;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "tb_Contact";
    private static final String DB_NAME = "db_Contact";
    private static final int VERSION = 1;
    private static final String CMD = "CREATE TABLE IF NOT EXISTS '" + TABLE_NAME + "' ( '"
            + Contact.KEY_NAME + "' TEXT, '"
            +Contact.KEY_NUMBER + "' TEXT, '"
            +Contact.KEY_PHOTO + "' TEXT, '"
            +Contact.KEY_ID + "' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)";

    private static final String[] columns = {Contact.KEY_ID,Contact.KEY_NAME,Contact.KEY_NUMBER,Contact.KEY_PHOTO};
    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CMD);
        Log.i("database","db created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void InsertToDB(Contact ct){
        ContentValues values = new ContentValues();
        values.put(Contact.KEY_NAME,ct.getName());
        values.put(Contact.KEY_NUMBER,ct.getNumber());
        values.put(Contact.KEY_PHOTO,ct.getPhoto());
        values.put(Contact.KEY_ID,ct.getId());
        // database
        SQLiteDatabase sb = getWritableDatabase();
        sb.insert(TABLE_NAME,null,values);
        Log.i("database","inserted to database");
        if (sb.isOpen()) sb.close();
    }

    public List<Contact> getAllObject(){
        List<Contact> ctList = new ArrayList<>();
        SQLiteDatabase sb = getReadableDatabase();
        Cursor cursor = sb.rawQuery("SELECT * FROM '" + TABLE_NAME + "'",null);
        if (cursor.moveToFirst()){
            do {
                Contact ct = new Contact();
                ct.setName(cursor.getString(cursor.getColumnIndex(Contact.KEY_NAME)));
                ct.setNumber(cursor.getString(cursor.getColumnIndex(Contact.KEY_NUMBER)));
                ct.setPhoto(cursor.getString(cursor.getColumnIndex(Contact.KEY_PHOTO)));
                ct.setId(cursor.getInt(cursor.getColumnIndex(Contact.KEY_ID)));
                ctList.add(ct);
            }while (cursor.moveToNext());
        }
        cursor.close();
        if (sb.isOpen()) sb.close();
        return ctList;
    }

    public void DeleteObject(int id){
        SQLiteDatabase sb = getWritableDatabase();
        sb.delete(TABLE_NAME,Contact.KEY_ID + " = ?",new String[] {String.valueOf(id)});
        Log.i("database","item is deleted");
        if (sb.isOpen()) sb.close();
    }

    public void Update(int id,ContentValues values){
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME,values,Contact.KEY_ID + " = ?",new String[] {String.valueOf(id)});
        Log.i("database","db is updated");
        if (db.isOpen()) db.close();
    }

    public List<Contact> getObeject(String selection,String[] slcArgs,String orderBy){
        SQLiteDatabase db = getReadableDatabase();
        List<Contact> ctList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME,columns,selection,slcArgs,null,null,orderBy);
        if (cursor.moveToFirst()){
            do {
                Contact ct = new Contact();
                ct.setName(cursor.getString(cursor.getColumnIndex(Contact.KEY_NAME)));
                ct.setNumber(cursor.getString(cursor.getColumnIndex(Contact.KEY_NUMBER)));
                ct.setPhoto(cursor.getString(cursor.getColumnIndex(Contact.KEY_PHOTO)));
                ct.setId(cursor.getInt(cursor.getColumnIndex(Contact.KEY_ID)));
                ctList.add(ct);
            }while (cursor.moveToNext());
        }
        cursor.close();
        if (db.isOpen()) db.close();
        return ctList;
    }
}
