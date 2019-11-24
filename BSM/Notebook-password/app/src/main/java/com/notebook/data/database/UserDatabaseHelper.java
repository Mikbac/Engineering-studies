package com.notebook.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.notebook.data.database.UserDatabase.Entry.TABLE_USER;
import static com.notebook.data.database.UserDatabase.SQL_CREATE_ENTRIES;
import static com.notebook.data.database.UserDatabase.SQL_DELETE_ENTRIES;
import static com.notebook.data.database.UserDatabase.ENCODING;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "UserDatabase.db";

    public UserDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ENCODING);
      //  db.execSQL("delete from " + TABLE_USER);
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
