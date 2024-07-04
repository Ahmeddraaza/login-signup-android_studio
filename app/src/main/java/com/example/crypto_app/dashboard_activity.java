package com.example.crypto_app;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class dashboard_activity extends AppCompatActivity {
    TextView profile_name, account_balancee;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        profile_name = findViewById(R.id.textView9);
        account_balancee = findViewById(R.id.textView8);

        email = getIntent().getStringExtra("key_email");

        getuserdetails();
    }

    public void getuserdetails(){
        database_model dbhelper = new database_model(this);
        ArrayList<userModal> al = dbhelper.getLoggedInuserdetail(email);
        if (!al.isEmpty()) {
            userModal userModal = al.get(0);
            profile_name.setText(userModal.getFirstname());
            account_balancee.setText(String.valueOf(userModal.getAccountbalance()));
        }
    }
}
