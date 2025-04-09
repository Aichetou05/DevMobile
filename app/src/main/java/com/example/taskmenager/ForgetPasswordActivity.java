package com.example.taskmenager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ForgetPasswordActivity extends AppCompatActivity {
    EditText resetEmail;
    Button resetButton;
    DatabaseHelper dphelper;
    String generatedCode;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        resetEmail = findViewById(R.id.resetEmail);
        resetButton = findViewById(R.id.resetButton);
        dphelper = new DatabaseHelper(this);

        resetButton.setOnClickListener(v -> {
            String email = resetEmail.getText().toString().trim();
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!dphelper.checkEmailExists(email)) {
                Toast.makeText(this, "Email not found", Toast.LENGTH_SHORT).show();
                return;
            }

            // Génération d’un code à 6 chiffres
            generatedCode = String.valueOf(100000 + new Random().nextInt(900000));

            // À ce stade, vous pouvez envoyer le code par email via un serveur (non couvert ici)
            Toast.makeText(this, "Code envoyé : " + generatedCode, Toast.LENGTH_LONG).show();

            // Lancement de la page de vérification
            Intent intent = new Intent(ForgetPasswordActivity.this, VerifyCodeActivity.class);
            intent.putExtra("email", email);
            intent.putExtra("code", generatedCode);
            startActivity(intent);

        });
    }
}
