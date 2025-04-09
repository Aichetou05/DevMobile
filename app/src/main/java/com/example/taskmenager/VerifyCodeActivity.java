package com.example.taskmenager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class VerifyCodeActivity extends AppCompatActivity{
    EditText codeInput;
    Button verifyButton;

    String sentCode;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);

        codeInput = findViewById(R.id.codeInput);
        verifyButton = findViewById(R.id.verifyCodeButton);

        email = getIntent().getStringExtra("email");
        sentCode = getIntent().getStringExtra("code");

        verifyButton.setOnClickListener(v -> {
            String inputCode = codeInput.getText().toString().trim();

            if (inputCode.equals(sentCode)) {
                Intent intent = new Intent(VerifyCodeActivity.this, ResetPasswordActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Incorrect code", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
