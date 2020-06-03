package com.example.fittestbmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Bmi1Activity extends AppCompatActivity {
    EditText etWeight;
    Button btnCalculateBMI;
    TextView tvfeet,tvinch,tvRes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi1);

        Toolbar toolbar=findViewById( R.id.toolbar);
        setSupportActionBar(toolbar);

        etWeight = findViewById(R.id.etWeight);
        btnCalculateBMI = findViewById(R.id.btnCalculate);
        tvfeet=findViewById(  R.id.tvfeet);
        tvinch=findViewById(  R.id.tvinch);
        tvRes=findViewById(  R.id.tvRes);

        Spinner spinner1 = findViewById(R.id.spinner1);
        Spinner spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.feet, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.inches, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);



        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String item1 = parent.getItemAtPosition(pos).toString();
                tvfeet.setText(item1, TextView.BufferType.EDITABLE);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String item2 = parent.getItemAtPosition(pos).toString();
                tvinch.setText(item2, TextView.BufferType.EDITABLE);

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnCalculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weight = etWeight.getText().toString();


                if (weight.isEmpty()) {
                    etWeight.setError("Please enter your weight  ");
                    etWeight.requestFocus();
                    return;

                }
                else
                {
                    String feet = tvfeet.getText().toString();
                    String inches = tvinch.getText().toString();
                    String wght=etWeight.getText().toString();
                    float feet1=Float.parseFloat(inches)/12;
                     float result=feet1+Float.parseFloat(feet);
                     double metres= result/3.281;



                     double bmi=(Integer.parseInt(wght))/(metres*metres);
                   // DecimalFormat df1 = new DecimalFormat( "#.##" );



                    //String msg="Calc Res= "+ bmi ;
                   // tvRes.setText(msg,TextView.BufferType.EDITABLE);
                   // String ppr=tvRes.getText().toString();

                   // Toast.makeText(Bmi1Activity.this,ppr, Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(Bmi1Activity.this,Bmi2Activity.class);
                    intent.putExtra("bmi",bmi);
                    startActivityForResult(intent,1);
                }


            }
        });

    }
 public boolean onCreateOptionsMenu(Menu menu)
 {
     MenuInflater inflater =getMenuInflater();
     inflater.inflate(R.menu.example_menu,menu);
     return  true;
 }

 public boolean onOptionsItemSelected(MenuItem item)
 {
     if (R.id.item1==item.getItemId())
     {
         Toast.makeText(this, "App developed with love ", Toast.LENGTH_SHORT).show();
     }
     if (R.id.item2==item.getItemId())
     {
         Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
         startActivity(browserIntent);
     }
     if (R.id.subitem1==item.getItemId())
     {
         Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.eatright.org"));
         startActivity(browserIntent);
     }
     if (R.id.subitem2==item.getItemId())
     {
         Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.quackwatch.org"));
         startActivity(browserIntent);
     }
     if (R.id.subitem3==item.getItemId())
     {
         Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.medindia.net"));
         startActivity(browserIntent);
     }
     return super.onOptionsItemSelected(item);
 }

}


