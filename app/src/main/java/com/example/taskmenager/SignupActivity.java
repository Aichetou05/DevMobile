package com.example.taskmenager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText usernameInput, emailInput, passwordInput;
    Button signupButton;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameInput = findViewById(R.id.signupUsername);
        emailInput = findViewById(R.id.signupEmail);
        passwordInput = findViewById(R.id.signupPassword);
        signupButton = findViewById(R.id.signupButton);

        dbHelper = new DatabaseHelper(this);

        signupButton.setOnClickListener(v -> {
            String username = usernameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            // Vérification des champs vides
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
            // Vérification de la syntaxe email
            else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
            }
            else {
                boolean exists = dbHelper.checkUserExistence(username, email);
                if (exists) {
                    Toast.makeText(this, "User already exists!", Toast.LENGTH_SHORT).show();
                } else {
                    boolean success = dbHelper.insertUser(username, email, password);
                    if (success) {
                        Toast.makeText(this, "Signup successful!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, HomeActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Signup failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
