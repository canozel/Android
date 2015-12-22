package com.example.canozel.sqlexample_todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by canozel on 21.12.2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME   = "todoDB";
    private static final String TABLE_NAME = "todo";
    private static final String TODO_ID = "id";
    private static final String TODO_NAME = "name";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY,name TEXT" + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void delete(int id) { //id ye göre silme işlemini gerçekleştiriyoruz.

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, TODO_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public void kitapEkle(String name) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TODO_NAME, name);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public ArrayList<HashMap<String, String>> list() {

        //Bu methodda ise tablodaki tüm kitapları alıyoruz

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> todo_list = new ArrayList<HashMap<String, String>>();

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }

                todo_list.add(map);
            } while (cursor.moveToNext());
        }
        db.close();
        return todo_list;
    }
}
