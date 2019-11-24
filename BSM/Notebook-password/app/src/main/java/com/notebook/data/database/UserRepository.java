package com.notebook.data.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.provider.BaseColumns;

import androidx.annotation.RequiresApi;

import com.notebook.ui.login.Encryption.Encryption;
import com.notebook.ui.login.hash.Hash;

import java.util.Arrays;

import static android.provider.BaseColumns._ID;
import static com.notebook.Application.getContext;
import static com.notebook.data.database.UserDatabase.Entry.COLUMN_USER_LOGIN;
import static com.notebook.data.database.UserDatabase.Entry.COLUMN_USER_NOTE;
import static com.notebook.data.database.UserDatabase.Entry.COLUMN_USER_NOTE_IV;
import static com.notebook.data.database.UserDatabase.Entry.COLUMN_USER_PASSWORD;
import static com.notebook.data.database.UserDatabase.Entry.COLUMN_USER_PASSWORD_IV;
import static com.notebook.data.database.UserDatabase.Entry.TABLE_USER;

public class UserRepository {

    private static UserDatabaseHelper userDatabaseHelper = new UserDatabaseHelper(getContext());

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void initialDatabaseWithFirstUser() {
        SQLiteDatabase db = userDatabaseHelper.getWritableDatabase();
        db.delete(TABLE_USER, null, null);
        ContentValues values = new ContentValues();

        String password = "sekurak12345678";
        String note = "The pug is a breed of dog with physically distinctive features of a wrinkly," +
                " short-muzzled face, and curled tail. The breed has a fine, glossy coat that comes in a variety of colours, " +
                " most often fawn or black, and a compact square body with well-developed muscles.";


        byte[] passwordToSave = Encryption.encrypt(Hash.getHash(password));
        values.put(UserDatabase.Entry.COLUMN_USER_LOGIN, "Pankracy");
        values.put(UserDatabase.Entry.COLUMN_USER_PASSWORD, passwordToSave);
        values.put(UserDatabase.Entry.COLUMN_USER_PASSWORD_IV, Encryption.iv.clone());
        values.put(UserDatabase.Entry.COLUMN_USER_NOTE, Encryption.encrypt(note));
        values.put(UserDatabase.Entry.COLUMN_USER_NOTE_IV, Encryption.iv.clone());
        long newUser = db.insert(UserDatabase.Entry.TABLE_USER, null, values);
    }

    public static boolean isUserExist(final String username) {
        SQLiteDatabase db = userDatabaseHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                COLUMN_USER_LOGIN,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_PASSWORD_IV,
                COLUMN_USER_NOTE,
                COLUMN_USER_NOTE_IV
        };

        String selection = COLUMN_USER_LOGIN + " = ?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(
                TABLE_USER,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(cursor.getColumnIndex(COLUMN_USER_LOGIN)).equals(username)) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void createNewUser(final String username, final String password) {
        SQLiteDatabase db = userDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserDatabase.Entry.COLUMN_USER_LOGIN, username);
        values.put(UserDatabase.Entry.COLUMN_USER_PASSWORD, Encryption.encrypt(Hash.getHash(password)));
        values.put(UserDatabase.Entry.COLUMN_USER_NOTE, Encryption.encrypt("Welcome to the Rabbit Hole!"));
        long newUser = db.insert(UserDatabase.Entry.TABLE_USER, null, values);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void changePassword(final String username, final String newPasswordOne) {
        Long id = getId(username);
        SQLiteDatabase db = userDatabaseHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER_PASSWORD, Encryption.encrypt(Hash.getHash(newPasswordOne)));
        cv.put(COLUMN_USER_PASSWORD_IV, Encryption.iv);
        db.update(TABLE_USER, cv, BaseColumns._ID + "=" + id, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getOldPassword(final String username) {
        SQLiteDatabase db = userDatabaseHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                COLUMN_USER_LOGIN,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_PASSWORD_IV,
                COLUMN_USER_NOTE,
                COLUMN_USER_NOTE_IV
        };
        String selection = COLUMN_USER_LOGIN + " = ?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query(
                TABLE_USER,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) {
            do {
                return Encryption.decrypt(cursor.getBlob(cursor.getColumnIndex(COLUMN_USER_PASSWORD)), cursor.getBlob(cursor.getColumnIndex(COLUMN_USER_PASSWORD_IV)));
            } while (cursor.moveToNext());
        }
        return null;
    }

    public static Long getId(final String username) {
        SQLiteDatabase db = userDatabaseHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                COLUMN_USER_LOGIN,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_PASSWORD_IV,
                COLUMN_USER_NOTE,
                COLUMN_USER_NOTE_IV
        };
        String selection = COLUMN_USER_LOGIN + " = ?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query(
                TABLE_USER,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        Long id = null;
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getLong(cursor.getColumnIndex(_ID));
            } while (cursor.moveToNext());
        }
        return id;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void saveNote(final String username, final String note) {
        Long id = getId(username);
        SQLiteDatabase db = userDatabaseHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER_NOTE, Encryption.encrypt(note));
        cv.put(COLUMN_USER_NOTE_IV, Encryption.iv);
        db.update(TABLE_USER, cv, BaseColumns._ID + "=" + id, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean isCorrectPassword(final String username, final String password) {
        SQLiteDatabase db = userDatabaseHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                COLUMN_USER_LOGIN,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_PASSWORD_IV,
                COLUMN_USER_NOTE,
                COLUMN_USER_NOTE_IV
        };
        String selection = COLUMN_USER_LOGIN + " = ?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query(
                TABLE_USER,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                if (Encryption.decrypt(cursor.getBlob(cursor.getColumnIndex(COLUMN_USER_PASSWORD)), cursor.getBlob(cursor.getColumnIndex(COLUMN_USER_PASSWORD_IV))).equals(Hash.getHash(password))) {
                    return true;
                } else {
                    return false;
                }
            } while (cursor.moveToNext());
        }

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getNote(final String username) {
        SQLiteDatabase db = userDatabaseHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                COLUMN_USER_LOGIN,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_PASSWORD_IV,
                COLUMN_USER_NOTE,
                COLUMN_USER_NOTE_IV
        };
        String selection = COLUMN_USER_LOGIN + " = ?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query(
                TABLE_USER,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        String note = null;
        if (cursor.moveToFirst()) {
            do {
                note = Encryption.decrypt(cursor.getBlob(cursor.getColumnIndex(COLUMN_USER_NOTE)), cursor.getBlob(cursor.getColumnIndex(COLUMN_USER_NOTE_IV)));
            } while (cursor.moveToNext());
        }
        return note;
    }

}
