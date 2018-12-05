package com.example.cmala.pokerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private int numberOfCpus = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button);
        final Button cpu1 = findViewById(R.id.radioButton);
        final Button cpu2 = findViewById(R.id.radioButton2);
        final Button cpu3 = findViewById(R.id.radioButton3);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), Game.class);
                myIntent.putExtra("numberCpus", numberOfCpus);
                v.getContext().startActivity(myIntent);
            }
        });

        cpu1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                numberOfCpus = 1;
            }
        });
        cpu2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                numberOfCpus = 2;
            }
        });
        cpu3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                numberOfCpus = 3;
            }
        });
    }

}
