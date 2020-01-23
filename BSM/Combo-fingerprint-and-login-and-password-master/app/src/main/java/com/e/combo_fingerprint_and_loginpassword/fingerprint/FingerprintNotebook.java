package com.e.combo_fingerprint_and_loginpassword.fingerprint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.e.combo_fingerprint_and_loginpassword.MainActivity;
import com.e.combo_fingerprint_and_loginpassword.R;
import com.e.combo_fingerprint_and_loginpassword.global.Encryption;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class FingerprintNotebook extends AppCompatActivity {

    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint_notebook);
        sharedPref = getSharedPreferences("notebook", MODE_PRIVATE);
        String note = sharedPref.getString("notebook-note", "");
        String iv = sharedPref.getString("iv", "");

        if (!iv.equals("")) {
            String[] split = iv.substring(1, iv.length() - 1).split(", ");
            byte[] array = new byte[split.length];
            for (int i = 0; i < split.length; i++) {
                array[i] = Byte.parseByte(split[i]);
            }
            Encryption.setIv(array);
        }

        final EditText noteText = findViewById(R.id.note);
        try {
            noteText.setText(Encryption.encrypt(note));
        } catch (KeyStoreException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchPaddingException | UnrecoverableEntryException | IOException | NoSuchAlgorithmException | CertificateException e) {
            e.printStackTrace();
        }

        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor mEditor = sharedPref.edit();
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        mEditor.putString("notebook-note", Encryption.crypt(String.valueOf(noteText.getText()))).apply();
                    }
                } catch (InvalidAlgorithmParameterException | NoSuchProviderException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
                    e.printStackTrace();
                }
                mEditor.putString("iv", Arrays.toString(Encryption.getIv())).apply();
            }
        });

    }

    public void logout() {
        String goodbye = getString(R.string.bye);
        Toast.makeText(getApplicationContext(), goodbye, Toast.LENGTH_LONG).show();
        FingerprintHandler.stopFingerAuth();

        Intent intent = new Intent(FingerprintNotebook.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        FingerprintNotebook.this.startActivity(intent);
    }

}
