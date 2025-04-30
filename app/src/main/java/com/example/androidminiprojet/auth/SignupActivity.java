package com.example.androidminiprojet.auth;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidminiprojet.R;
import com.example.androidminiprojet.models.User;
import com.example.androidminiprojet.network.ApiClient;
import com.example.androidminiprojet.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    EditText nameInput, emailInput, passwordInput;
    Button signupBtn;
    TextView loginText = findViewById(R.id.loginText);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nameInput = findViewById(R.id.signupUsername);
        emailInput = findViewById(R.id.signupEmail);
        passwordInput = findViewById(R.id.signupPassword);
        signupBtn = findViewById(R.id.signupButton);

        signupBtn.setOnClickListener(v -> signupUser());
        loginText.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void signupUser() {
        String name = nameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        User user = new User(name, email, password);

        ApiClient.getService().signup(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "Inscription réussie", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                } else {
                    Toast.makeText(SignupActivity.this, "Erreur d'inscription", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "Erreur de connexion", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
