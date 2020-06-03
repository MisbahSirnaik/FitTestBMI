package com.example.fittestbmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RatingBar;

public class RatingActivity extends AppCompatActivity {

    RatingBar rabRating;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        rabRating =findViewById(R.id.rabRating);
        btnSubmit =findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String rat=String.valueOf(rabRating.getRating());
                String msg="Rating for FitTest BMI : "  + rat ;
                Intent a = new Intent(Intent.ACTION_SEND);
                a.putExtra(Intent.EXTRA_TEXT,msg);
                a.setType("text/plain");
                startActivity(a);

            }
        });
    }
}
