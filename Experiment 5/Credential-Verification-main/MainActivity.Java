package com.example.myapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText idEditText;
    private Button validateButton;
    private Button registerButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        idEditText = findViewById(R.id.idEditText);
        validateButton = findViewById(R.id.validateButton);
        registerButton = findViewById(R.id.registerButton);

        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInputs();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void validateInputs() {
        String username = usernameEditText.getText().toString().trim();
        String id = idEditText.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(id)) {
            Toast.makeText(this, "Both fields must not be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!username.matches("[a-zA-Z]+")) {
            Toast.makeText(this, "Username must contain only alphabets", Toast.LENGTH_SHORT).show();
            return;
        }

        // ID validation pattern with at least one uppercase letter, one digit, and one special character
        if (!id.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,}$")) {
            Toast.makeText(this, "ID must contain at least one uppercase letter, one number, and one special character", Toast.LENGTH_SHORT).show();
            return;
        }

        // Retrieve stored username and ID from SharedPreferences
        String storedUsername = sharedPreferences.getString("Username", null);
        String storedId = sharedPreferences.getString("ID", null);

        // Validate entered username and ID against stored values
        if (username.equals(storedUsername) && id.equals(storedId)) {
            Toast.makeText(this, "Validation Successful!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Validation Unsuccessful!", Toast.LENGTH_SHORT).show();
        }
    }

    private void registerUser() {
        String username = usernameEditText.getText().toString().trim();
        String id = idEditText.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(id)) {
            Toast.makeText(this, "Both fields must not be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!username.matches("[a-zA-Z]+")) {
            Toast.makeText(this, "Username must contain only alphabets", Toast.LENGTH_SHORT).show();
            return;
        }

        // ID validation pattern with at least one uppercase letter, one digit, and one special character
        if (!id.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,}$")) {
            Toast.makeText(this, "ID must contain at least one uppercase letter, one number, and one special character", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save user data to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Username", username);
        editor.putString("ID", id);
        editor.apply();

        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();
    }
}
