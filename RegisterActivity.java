package com.example.fittestbmi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    //declare
    EditText etName,etPhone,etAge;
    Button btnRegister;
    RadioButton radioFemale,radioMale;


    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        etPhone=findViewById(  R.id.etphone);


        etAge=findViewById(  R.id.etage);
        etName=findViewById(R.id.etName);
        btnRegister=findViewById(R.id.btnRegister);
        radioFemale=findViewById(R.id.radioFemale);
        radioMale=findViewById(R.id.radioMale);



       sp=getSharedPreferences("myp1",MODE_PRIVATE);
       String n=sp.getString("name","");
       String p=sp.getString("phone","");
       String varsh=sp.getString("age","");


        if (n.length()!=0 && !p.isEmpty() && !varsh.isEmpty())
        {
            Intent a =new Intent(RegisterActivity.this,MainActivity.class);
            startActivity(a);
            finish();
        }
        else{
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name=etName.getText().toString();
                    String phone=etPhone.getText().toString();
                    String age=etAge.getText().toString();

                    if( name.isEmpty() || name.length() < 2 || !isNameValid(name))
                    {
                        etName.setError("Please enter valid name ");
                        etName.requestFocus();
                        return;

                    }
                    else if (age.isEmpty() || Integer.parseInt(age)<15 || Integer.parseInt(age)>115) {
                        etAge.setError("enter a valid age ( between 15-115)");
                        etAge.requestFocus();
                        return;

                    }
                    else if (phone.isEmpty() || !isPhoneNumberValid(phone)) {
                        etPhone.setError("enter a valid phone number along with your country code (+91) ");
                        etPhone.requestFocus();
                        return;

                    }
                    else if (!radioFemale.isChecked() && !radioMale.isChecked()) {
                        Toast.makeText(RegisterActivity.this, "Please enter your gender", Toast.LENGTH_SHORT).show();
                        return;

                    }

                    else {


                        SharedPreferences.Editor editor = sp.edit();

                        editor.putString("name", name);
                        editor.putString("phone",phone);
                        editor.putString("age",age);
                        if (radioMale.isChecked())
                        {
                            String gender="Male";
                            editor.putString("gender",gender);
                        }
                        else
                        {
                            String gender="Female";
                            editor.putString("gender",gender);
                        }

                        editor.commit();
                        Intent a = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(a);
                        finish();
                    }



                }
            });

        }
    }


    public static boolean isPhoneNumberValid(String phoneNumber) {

        boolean valid = true;
        String regex = "^(?:0091|\\+91|0)[7-9][0-9]{9}";

        if (!phoneNumber.matches(regex)) {
            valid = false;
        }
        return valid;
    }

    public static boolean isNameValid(String Name) {

        boolean valid = true;
        String regex = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}";
        if (!Name.matches(regex)) {
            valid = false;
        }
        return valid;
    }


}