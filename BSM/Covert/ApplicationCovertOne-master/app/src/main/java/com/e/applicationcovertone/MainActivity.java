package com.e.applicationcovertone;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button clickMeButton = findViewById(R.id.buttonClickMe);

        clickMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContactList();
            }
        });

    }

    private void getContactList() {
        int i = 0;
        SharedPreferences prefs = getSharedPreferences("contacts",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {

            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);

                    while (pCur.moveToNext()) {

                        String phoneNumber = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        i++;
                        editor.putString("contacts-" + i + "-number", String.valueOf(i));
                        editor.putString("contacts-" + i + "-name", name);
                        editor.putString("contacts-" + i + "-phoneNumber", phoneNumber);

                        System.out.println("Name: " + name);
                        System.out.println("Phone Number: " + phoneNumber);
                    }
                    pCur.close();
                }

            }
            System.out.println("quantity: " + i);
            editor.putString("contacts-quantity", String.valueOf(i));
            editor.apply();
        }
        if (cur != null) {
            cur.close();
        }
    }

}