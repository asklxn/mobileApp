package com.example.sleepit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SleepDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "sleep.db";
    public static final int DATABASE_VERSION = 2;

    public static final String TABLE_NAME = "sleep_records";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SLEEP_TIME = "sleep_time";
    public static final String COLUMN_WAKE_TIME = "wake_time";
    public static final String COLUMN_DATE = "date";

    public SleepDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS SleepRecords (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date TEXT, " +
                "sleep_time TEXT, " +   // ✅ 수정: 컬럼 이름을 snake_case로
                "wake_time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

}
