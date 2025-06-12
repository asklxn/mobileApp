package com.example.sleepit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SleepDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "sleepdata.db";
    public static final int DATABASE_VERSION = 1;

    public SleepDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS sleep_records (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sleep_time TEXT," +
                "wake_time TEXT," +
                "duration TEXT," +
                "date TEXT" +
                ");";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS sleep_records");
        onCreate(db);
    }
}
