package com.e.combo_fingerprint_and_loginpassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.e.combo_fingerprint_and_loginpassword.fingerprint.FingerprintMainActivity;
import com.e.combo_fingerprint_and_loginpassword.password.PasswordMain;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button fingerprintButton = findViewById(R.id.mainFingerprint);
        fingerprintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = getString(R.string.fingerprint);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, FingerprintMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                MainActivity.this.startActivity(intent);
            }
        });

        Button passwordButton = findViewById(R.id.mainLoginAndPassword);
        passwordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = getString(R.string.fingerprint);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, PasswordMain.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
