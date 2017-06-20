package com.example.shruti.datastorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Shruti on 04-04-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME= "Item.db";
    public static final String TABLE_NAME= "Item_table";
    public static final String COL1= "NAME";
    public static final String COL2= "DESCRIPTION";
    public static final String COL3= "PRICE";
    public static final String COL4= "REVIEW";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("My message", "Db created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE= "CREATE TABLE " + TABLE_NAME + "( NAME TEXT, DESCRIPTION TEXT, PRICE TEXT, REVIEW TEXT)";
        db.execSQL(CREATE_TABLE);
        Log.d("Table created", "tb created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE" + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(DatabaseHelper dbh ,String name, String description, String price, String review){
        SQLiteDatabase db= dbh.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL1, name);
        contentValues.put(COL2, description);
        contentValues.put(COL3, price);
        contentValues.put(COL4, review);
        long result= db.insert(TABLE_NAME, null, contentValues);
        if (result== -1)
            return false;
        else
            return true;
    }

    public Cursor getData(SQLiteDatabase db, String name)
    {
        Log.d("Search item:", name);
        db=this.getReadableDatabase();

        String[] columns= {COL1, COL2, COL3, COL4};
        String selection= COL1 +" LIKE? ";
        String[] selectionArgs= new String[] {name+"%"};
        Cursor cursor= db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        Log.d("search result", String.valueOf(cursor));
        Log.d("search name", String.valueOf(cursor.getColumnIndex(COL1)));
        Log.d("search desc", String.valueOf(cursor.getColumnIndex(COL2)));
        Log.d("search price", String.valueOf(cursor.getColumnIndex(COL3)));
        Log.d("search review", String.valueOf(cursor.getColumnIndex(COL4)));

        return cursor;
    }
}
