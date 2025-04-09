package com.example.taskmenager;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText newPassword;
    Button updatePasswordBtn;
    DatabaseHelper dbHelper;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        newPassword = findViewById(R.id.newPassword);
        updatePasswordBtn = findViewById(R.id.resetButton);
        dbHelper = new DatabaseHelper(this);
        email = getIntent().getStringExtra("email");

        updatePasswordBtn.setOnClickListener(v -> {
            String password = newPassword.getText().toString().trim();
            if (password.length() < 6) {
                Toast.makeText(this, "Password too short", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean updated = dbHelper.updatePassword(email, password);
            if (updated) {
                Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                finish(); // Back to login
            } else {
                Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
