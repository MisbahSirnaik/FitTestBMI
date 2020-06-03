package com.example.fittestbmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    //declare
    TextView tvwelcome;
    SharedPreferences sp;
    Button btnBMI,btnTodo,btnMotivation,btnStopWatch, btnExercise,btnRateUs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //bind
        tvwelcome=findViewById(R.id.tvwelcome);
        //btn bind
        btnBMI=findViewById(R.id.btnBMI);
        btnTodo=findViewById(R.id.btnTodo);
        btnMotivation=findViewById(R.id.btnMotivation);
        btnStopWatch=findViewById(R.id.btnStopWatch);
        btnExercise=findViewById(R.id.btnExercise);
        btnRateUs=findViewById(R.id.btnRateUs);
        //shared prefs
        sp=getSharedPreferences("myp1",MODE_PRIVATE);
        String  name=sp.getString("name","");
        tvwelcome.setText("Heyyy  "+name + "...");

        //ONCLICKSS
        // button BMI
        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(MainActivity.this,Bmi1Activity.class);
                startActivity(intent);
            }
        });



        //Button TODO LIST
        btnTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,

                        "A goal without a plan is just a wish... \n So Let's Plan ",
                        Toast.LENGTH_LONG).show();

                Intent intent=new Intent(MainActivity.this,TodoListActivity.class);
                startActivity(intent);
            }
        });
        //Button MOTIVATION
        btnMotivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,

                        "Slide the image for more Motivation ...\n  ",
                        Toast.LENGTH_LONG).show();

                Intent intent=new Intent(MainActivity.this,MotivationActivity.class);
                startActivity(intent);
            }
        });
        //Button FitVideos
        btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this, GetFit.class);
                startActivity(intent);

            }
        });

        //Button RATING
        btnRateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, RatingActivity.class);
                startActivity(intent);
            }
        });

        //button track progress
        btnStopWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "View BMI History", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(MainActivity.this, ViewHistory.class);
                startActivity(intent);
            }
        });


    }

    long bpt;

    @Override
    public void onBackPressed() {
        if(bpt + 1500>System.currentTimeMillis())
            super.onBackPressed();
        else
            Toast.makeText(this, "Are you sure you want to exit ? \n Press back once again to exit", Toast.LENGTH_SHORT).show();
        bpt=System.currentTimeMillis();

    }
}
