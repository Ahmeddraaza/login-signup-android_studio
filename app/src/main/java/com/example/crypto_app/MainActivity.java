package com.example.crypto_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
private ImageView next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();
        setvariable();


    }

    private void initview(){
        next = findViewById(R.id.imageView3);
    }

    private void setvariable(){

        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, login_activity.class));

            }
        });
    }
}





