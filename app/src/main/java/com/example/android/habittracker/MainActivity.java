package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

import static android.util.Log.wtf;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new HabitDbHelper(this);
        insertHabit();
        displayHabitTable();
    }

    private void displayHabitTable(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] habitProjection = {
                HabitEntry._ID,
                HabitEntry.COL_HABIT_NAME,
                HabitEntry.COL_HABIT_NOTES,
                HabitEntry.COL_HABIT_TYPE,
                HabitEntry.COL_HABIT_VALUE,
                HabitEntry.COL_HABIT_SCORE
        };

        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                habitProjection,
                null,
                null,
                null,
                null,
                null
        );
        String output = HabitEntry._ID + " - "
                + HabitEntry.COL_HABIT_NAME + " - "
                + HabitEntry.COL_HABIT_NOTES + " - "
                + HabitEntry.COL_HABIT_TYPE + " - "
                + HabitEntry.COL_HABIT_VALUE + " - "
                + HabitEntry.COL_HABIT_SCORE;
        try{
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int habitNameColumnIndex = cursor.getColumnIndex(HabitEntry.COL_HABIT_NAME);
            int habitNotesColumnIndex = cursor.getColumnIndex(HabitEntry.COL_HABIT_NOTES);
            int habitTypeColumnIndex = cursor.getColumnIndex(HabitEntry.COL_HABIT_TYPE);
            int habitValueColumnIndex = cursor.getColumnIndex(HabitEntry.COL_HABIT_VALUE);
            int habitScoreColumnIndex = cursor.getColumnIndex(HabitEntry.COL_HABIT_SCORE);

            while (cursor.moveToNext()){
                output += "\n"
                        +cursor.getInt(idColumnIndex) + " - "
                        +cursor.getString(habitNameColumnIndex) + " - "
                        +cursor.getString(habitNotesColumnIndex) + " - "
                        +cursor.getInt(habitTypeColumnIndex) + " - "
                        +cursor.getInt(habitValueColumnIndex) + " - "
                        +cursor.getInt(habitScoreColumnIndex);
            }
        } finally {
            cursor.close();
        }
        wtf("Output", output);
    }

    private void insertHabit(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //dummy data
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COL_HABIT_NAME, "wash dishes");
        values.put(HabitEntry.COL_HABIT_NOTES, "personal note about doing dishes");
        values.put(HabitEntry.COL_HABIT_TYPE, HabitEntry.TYPE_POSITIVE);
        values.put(HabitEntry.COL_HABIT_VALUE, 13);
        db.insert(HabitEntry.TABLE_NAME, null, values);

        //dummy data2
        ContentValues values2 = new ContentValues();
        values2.put(HabitEntry.COL_HABIT_NAME, "stop smoking");
        values2.put(HabitEntry.COL_HABIT_NOTES, "remove score after each");
        values2.put(HabitEntry.COL_HABIT_TYPE, HabitEntry.TYPE_NEGATIVE);
        values2.put(HabitEntry.COL_HABIT_VALUE, 42);
        db.insert(HabitEntry.TABLE_NAME, null, values2);

        //dummy data3
        ContentValues values3 = new ContentValues();
        values3.put(HabitEntry.COL_HABIT_NAME, "Battle gaming backlog");
        values3.put(HabitEntry.COL_HABIT_NOTES, "+ for each game finished, - for each new game bought");
        values3.put(HabitEntry.COL_HABIT_TYPE, HabitEntry.TYPE_BOTH);
        values3.put(HabitEntry.COL_HABIT_VALUE, 1);
        db.insert(HabitEntry.TABLE_NAME, null, values3);
    }
}
