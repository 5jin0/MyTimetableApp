package com.example.mytimetable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "timetable.db";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE schedule (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "day TEXT, " +
            "time TEXT, " +
            "subject TEXT)"
        );
    }

    public void deleteSchedule(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("schedule", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS schedule");
        onCreate(db);
    }
}
