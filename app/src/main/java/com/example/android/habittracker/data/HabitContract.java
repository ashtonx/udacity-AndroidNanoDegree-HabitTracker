package com.example.android.habittracker.data;


import android.provider.BaseColumns;

public class HabitContract {
    private HabitContract(){}

    public static final class HabitEntry implements BaseColumns{
        public static final String TABLE_NAME           = "habits";

        public static final String _ID                  = BaseColumns._ID;
        public static final String COL_HABIT_NAME       = "name";
        public static final String COL_HABIT_NOTES      = "notes";
        public static final String COL_HABIT_TYPE       = "type";
        //score value, importance of HABIT, that can be used to count overall user score
        //less important habits make have smaller impact, more important ones have higher impact
        public static final String COL_HABIT_VALUE      = "value";
        public static final String COL_HABIT_SCORE      = "score";

        // values for HABIT type
        // type negative habits will display - and remove score
        public static final int TYPE_NEGATIVE           = 0;
        // type positive habits will display + and add score
        public static final int TYPE_POSITIVE           = 1;
        // type both habits will display both -/+ and add or remove score
        public static final int TYPE_BOTH               = 2;
    }
}
