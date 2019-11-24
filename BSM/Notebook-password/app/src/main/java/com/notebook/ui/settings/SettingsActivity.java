package com.notebook.ui.settings;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.notebook.R;
import com.notebook.data.database.UserRepository;
import com.notebook.ui.login.hash.Hash;
import com.notebook.ui.notebook.NotebookActivity;

import static com.notebook.data.database.UserDatabase.Entry.COLUMN_USER_LOGIN;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final String username = getIntent().getStringExtra(COLUMN_USER_LOGIN);
        final EditText oldPassword = findViewById(R.id.oldPassword);
        final EditText newPasswordOne = findViewById(R.id.newPassword1);
        final EditText newPasswordTwo = findViewById(R.id.newPassword2);

        Button changePasswordButton = findViewById(R.id.confirmChangePassword);
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if (arePasswordCorrect(username, oldPassword.getText().toString(), newPasswordOne.getText().toString(), newPasswordTwo.getText().toString())) {
                    UserRepository.changePassword(username, newPasswordOne.getText().toString());
                    String message = getString(R.string.passwords_changed_correctly);
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                } else {
                   // String message = getString(R.string.invalid_password);
                  //  Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(SettingsActivity.this, NotebookActivity.class);
                intent.putExtra(COLUMN_USER_LOGIN, username);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                SettingsActivity.this.startActivity(intent);
                finish();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean arePasswordCorrect(final String username, final String oldPassword, final String newPasswordOne, final String newPasswordTwo) {
        if (UserRepository.getOldPassword(username).equals(Hash.getHash(oldPassword)) && newPasswordOne.equals(newPasswordTwo) && newPasswordOne.length() > 8) {
            return true;
        } else {
            return false;
        }
    }

}
