package com.example.carapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnins,btndel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btndel=findViewById(R.id.btngo);
        btnins=findViewById(R.id.btnins);

        btnins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntnet =new Intent(getApplicationContext(),insert.class);
                startActivity(myIntnet);
            }
        });
        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntnet =new Intent(getApplicationContext(),delete.class);
                startActivity(myIntnet);
            }
        });
    }
}
