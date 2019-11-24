package com.notebook.data.database;

import android.provider.BaseColumns;

public class UserDatabase {
    public UserDatabase() {
    }

    public static class Entry implements BaseColumns {
        public static final String TABLE_USER = "user";
        public static final String COLUMN_USER_LOGIN = "login";
        public static final String COLUMN_USER_PASSWORD = "password";
        public static final String COLUMN_USER_PASSWORD_IV = "passwordIV";
        public static final String COLUMN_USER_NOTE = "note";
        public static final String COLUMN_USER_NOTE_IV = "noteIV";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Entry.TABLE_USER + " (" +
                    Entry._ID + " INTEGER PRIMARY KEY," +
                    Entry.COLUMN_USER_LOGIN + " TEXT," +
                    Entry.COLUMN_USER_PASSWORD + " TEXT," +
                    Entry.COLUMN_USER_PASSWORD_IV + " TEXT," +
                    Entry.COLUMN_USER_NOTE_IV + " TEXT," +
                    Entry.COLUMN_USER_NOTE + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Entry.TABLE_USER;

    public static final String ENCODING = "PRAGMA encoding = \"UTF-8\"";

}
