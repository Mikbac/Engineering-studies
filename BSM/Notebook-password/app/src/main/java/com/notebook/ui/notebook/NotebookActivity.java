package com.notebook.ui.notebook;

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
import com.notebook.ui.login.LoginActivity;
import com.notebook.ui.login.LoginViewModel;
import com.notebook.ui.settings.SettingsActivity;

import static com.notebook.data.database.UserDatabase.Entry.COLUMN_USER_LOGIN;

public class NotebookActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook);

        final EditText note = findViewById(R.id.note);
        final String username = getIntent().getStringExtra(COLUMN_USER_LOGIN);

        note.setText(UserRepository.getNote(username));

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
                UserRepository.saveNote(username, note.getText().toString());
            }
        });

        Button changePasswordButton = findViewById(R.id.changePasswordButton);
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotebookActivity.this, SettingsActivity.class);
                intent.putExtra(COLUMN_USER_LOGIN, username);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                NotebookActivity.this.startActivity(intent);
                finish();
            }
        });

    }

    public void logout() {
        String goodbye = getString(R.string.bye);
        Toast.makeText(getApplicationContext(), goodbye, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(NotebookActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        finish();
    }

}
