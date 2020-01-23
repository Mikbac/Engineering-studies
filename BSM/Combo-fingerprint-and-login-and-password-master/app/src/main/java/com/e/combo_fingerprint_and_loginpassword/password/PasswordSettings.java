package com.e.combo_fingerprint_and_loginpassword.password;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.e.combo_fingerprint_and_loginpassword.R;
import com.e.combo_fingerprint_and_loginpassword.global.Hash;

public class PasswordSettings extends AppCompatActivity {
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_settings);
        sharedPref = getSharedPreferences("notebook", MODE_PRIVATE);

        final String password = sharedPref.getString("notebook-password", Hash.getHash("nimda"));

        Button confirmChangePasswordButton = findViewById(R.id.confirmChangePassword);
        confirmChangePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor mEditor = sharedPref.edit();

                EditText oldPassword = findViewById(R.id.oldPassword);
                EditText newPasswordOne = findViewById(R.id.newPassword1);
                EditText newPasswordTwo = findViewById(R.id.newPassword2);
                
                if ((String.valueOf(newPasswordOne.getText()).equals(String.valueOf(newPasswordTwo.getText()))) ) {System.out.println("111111111111111");}
                if (Hash.getHash(String.valueOf(oldPassword.getText())).equals(password)) {System.out.println("222222222222222");}



                if ((String.valueOf(newPasswordOne.getText()).equals(String.valueOf(newPasswordTwo.getText()))) && (Hash.getHash(String.valueOf(oldPassword.getText())).equals(password))) {

                    mEditor.putString("notebook-password", Hash.getHash(String.valueOf(newPasswordOne.getText()))).apply();

                    String message = getString(R.string.passwords_changed_correctly);
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(PasswordSettings.this, PasswordNotebook.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PasswordSettings.this.startActivity(intent);
                }
            }
        });

    }
}
