package com.example.crypto_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crypto_app.databinding.SignupActivityBinding;

public class signup_activity extends AppCompatActivity {

    private SignupActivityBinding binding;
    private database_model databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SignupActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new database_model(this);

        binding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.editTextEmail.getText().toString().trim();
                String password = binding.editTextPassword.getText().toString().trim();
                String confirmpassword = binding.editTextConfirmPassword.getText().toString().trim();
                String firstname = binding.editTextFirstName.getText().toString().trim();
                String lastname = binding.editTextLastName.getText().toString().trim();
                String ageStr = binding.editTextAge.getText().toString().trim();
                String accountBalanceStr = binding.editTextAccountBalance.getText().toString().trim();
                String phoneNumber = binding.editTextPhone.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty() || confirmpassword.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || ageStr.isEmpty() || accountBalanceStr.isEmpty() || phoneNumber.isEmpty()) {
                    Toast.makeText(signup_activity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(confirmpassword)) {
                        boolean checkUserEmail = databaseHelper.checkEmail(email);
                        if (!checkUserEmail) {
                            int age = Integer.parseInt(ageStr);
                            double accountBalance = Double.parseDouble(accountBalanceStr);

                            boolean insert = databaseHelper.insertData(firstname, lastname, phoneNumber, age, email, accountBalance, password);
                            if (insert) {
                                Toast.makeText(signup_activity.this, "Sign up Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), login_activity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(signup_activity.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(signup_activity.this, "User Already Exists, please login", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(signup_activity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup_activity.this, login_activity.class);
                startActivity(intent);
            }
        });
    }
}
