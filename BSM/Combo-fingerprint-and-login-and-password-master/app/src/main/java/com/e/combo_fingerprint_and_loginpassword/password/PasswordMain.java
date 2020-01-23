package com.e.combo_fingerprint_and_loginpassword.password;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.e.combo_fingerprint_and_loginpassword.R;
import com.e.combo_fingerprint_and_loginpassword.global.Hash;

public class PasswordMain extends AppCompatActivity {

    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_main);
        sharedPref = getSharedPreferences("notebook", MODE_PRIVATE);
        final String username = sharedPref.getString("notebook-username", "admin");
        final String password = sharedPref.getString("notebook-password", Hash.getHash("nimda"));

        Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                EditText textLogin = findViewById(R.id.username);
                EditText textPassword = findViewById(R.id.password);

                System.out.println("-------------------");
                System.out.println(textPassword.getText());
                System.out.println(Hash.getHash("nimda"));
                System.out.println(password);

                if ((String.valueOf(textLogin.getText()).equals(username)) && (Hash.getHash(String.valueOf(textPassword.getText())).equals(password))) {
                    Intent intent = new Intent(PasswordMain.this, PasswordNotebook.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PasswordMain.this.startActivity(intent);
                }

            }
        });

    }
}
