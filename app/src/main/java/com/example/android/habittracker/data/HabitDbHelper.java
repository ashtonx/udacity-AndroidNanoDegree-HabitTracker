package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittracker.data.HabitContract.HabitEntry;

public class HabitDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "habit.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES_HABIT = "CREATE TABLE "
            + HabitEntry.TABLE_NAME         + " ("
            + HabitEntry._ID                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + HabitEntry.COL_HABIT_NAME     + " TEXT NOT NULL,"
            + HabitEntry.COL_HABIT_NOTES    + " TEXT,"
            + HabitEntry.COL_HABIT_TYPE     + " INTEGER NOT NULL,"
            + HabitEntry.COL_HABIT_VALUE    + " INTEGER NOT NULL DEFAULT 0,"
            + HabitEntry.COL_HABIT_SCORE    + " INTEGER NOT NULL DEFAULT 0);";
    private static final String SQL_DELETE_ENTRIES_HABIT = "DROP TABLE IF EXISTS "
            + HabitEntry.TABLE_NAME;

    public HabitDbHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_HABIT);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DELETE_ENTRIES_HABIT);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

}
