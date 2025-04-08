package com.example.taskmenager;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText usernameInput,emailInput, passwordInput;
    Button loginButton;
    TextView singnup,forgotpass;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Lier les composants
        usernameInput = findViewById(R.id.username);
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        singnup = findViewById(R.id.singupText);
        forgotpass = findViewById(R.id.forgetPass);


        // 2. Initialiser la base de données
        dbHelper = new DatabaseHelper(this);

        // 3. Action bouton login
        loginButton.setOnClickListener(v -> {
           String usernamer = usernameInput.getText().toString();
           String email = emailInput.getText().toString();
           String password = passwordInput.getText().toString();

           if(usernamer.isEmpty() || email.isEmpty() || password.isEmpty()){
               Toast.makeText(this,"Please fill all fields", Toast.LENGTH_SHORT).show();
           }else{
               Boolean valid = dbHelper.checkUser(usernamer,email,password);
               if(valid){
                   Toast.makeText(this,"Login Successful!",Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(this,"Invalid please try again!",Toast.LENGTH_SHORT).show();
               }
           }
        });

        // 4. Action pour inscription
        singnup.setOnClickListener(v -> {
            //vers une autre activity 4e nektbou yak youda7 lkm la tm7ouh
            Intent intent = new Intent(this,SignupActivity.class);
            startActivity(intent);
        });

        //yak tevehmou 4i parti l mot de passe oublier
        forgotpass.setOnClickListener(v -> {
            Toast.makeText(this,"Password recovery no implemented ",Toast.LENGTH_SHORT).show();
        });
    }
}