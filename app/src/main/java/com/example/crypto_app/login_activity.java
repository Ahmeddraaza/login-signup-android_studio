package com.example.crypto_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.crypto_app.databinding.LoginActivityBinding;

public class login_activity extends AppCompatActivity {

    LoginActivityBinding binding;
    database_model databaseHelper;

    private EditText editTextUsername, editTextPassword;
    private TextView textViewRegister;

    private AppCompatButton loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new database_model(this);

        editTextUsername = findViewById(R.id.editTextText);
        editTextPassword = findViewById(R.id.editTextText2);
        textViewRegister = findViewById(R.id.textView7);
        loginbtn = findViewById(R.id.button);

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_activity.this, signup_activity.class));
            }
        });

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(login_activity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean loginSuccessful = databaseHelper.checkEmail(username);
                    if (loginSuccessful) {
                        Toast.makeText(login_activity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login_activity.this, dashboard_activity.class);
                        intent.putExtra("key_email", username);
                        startActivity(intent);
                    } else {
                        Toast.makeText(login_activity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
