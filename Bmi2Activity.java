package com.example.fittestbmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Bmi2Activity extends AppCompatActivity {
   TextView tvdate,tvbmires,tvbmicom,tvset1,tvset2,tvset3,tvset4;
   Button btnShare,mButtonSpeak,btnSave,btnViewHistory;
   SharedPreferences sp;
   private TextToSpeech mTTS;
   private SeekBar mSeekBarPitch,mSeekBarSpeed;
//Firebase

    DatabaseReference databaseUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi2);

        databaseUsers= FirebaseDatabase.getInstance().getReference("userinfo");

        tvbmires=findViewById(R.id.tvbmires);
        tvbmicom=findViewById(R.id.tvbmicom);
        tvset1=findViewById(R.id.tvset1);
        tvset2=findViewById(R.id.tvset2);
        tvset3=findViewById(R.id.tvset3);
        tvset4=findViewById(R.id.tvset4);
        tvdate=findViewById(R.id.tvdate);
        btnShare=findViewById(R.id.btnShare);
        mButtonSpeak=findViewById(R.id.button_speak);
        btnSave=findViewById(R.id.btnSave);
        btnViewHistory=findViewById(R.id.btnViewHistory);







        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
           if (status==TextToSpeech.SUCCESS){

               int result=mTTS.setLanguage(Locale.ENGLISH);

               if(result==TextToSpeech.LANG_MISSING_DATA

               || result==TextToSpeech.LANG_NOT_SUPPORTED

               )


               {
                   Log.e("TTS","Language not supported");

               }

               else
               {
                   mButtonSpeak.setEnabled(true);
               }
           }

           else
           {
               Log.e("TTS","Initialization Failed");

           }
            }
        });

        mSeekBarPitch=findViewById(R.id.seek_bar_pitch);
        mSeekBarSpeed=findViewById(R.id.seek_bar_speed);




        Thread t = new Thread(){
            @Override
            public void run() {
                try{
                    while(!isInterrupted()){
                        Thread.sleep(100);
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run(){

                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy hh:mm a");
                                String dateString = sdf.format(date);
                                tvdate.setText(dateString);
                            }


                        });
                    }
                } catch(InterruptedException e){
                }
            }
        };
        t.start();
        Intent intent=getIntent();
        double bmi=intent.getDoubleExtra("bmi",0);
        DecimalFormat df1 = new DecimalFormat( "#.##" );


        tvbmires.setText("Your BMI is "+ df1.format(bmi));

        if( bmi<=18.5)
        {
            tvbmicom.setText("You are Underweight");
            tvset1.setTextColor(Color.parseColor("#FF0000"));
        }

        else if( bmi>18.5 && bmi<=25)
        {
            tvbmicom.setText("You are Normal");
            tvset2.setTextColor(Color.parseColor("#FF0000"));

        }

        else if( bmi>25 && bmi<=30)
        {
            tvbmicom.setText("You are Overweight");
            tvset3.setTextColor(Color.parseColor("#FF0000"));
        }
        else
        {
            tvbmicom.setText("You are Obese");
            tvset4.setTextColor(Color.parseColor("#FF0000"));
        }
          //final  String  msg=tvbmicom.getText().toString() + tvbmires.getText().toString();
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sp=getSharedPreferences("myp1",MODE_PRIVATE);
                String  name=sp.getString("name","");
                String phone=sp.getString("phone","");
                String age=sp.getString("age","");
                String gender=sp.getString("gender","");
                String  bomi=tvbmires.getText().toString();
                String cat=tvbmicom.getText().toString();
                String date=tvdate.getText().toString();
                Intent sharingIntent=new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody="Today-"+date+"\n"+"Name: "+name+"\n"+"Age: "+age +"\n"+
                        "Phone No: "+phone+"\n"+"Gender: "+gender+"\n"+bomi+"\n"+cat;
                String shareSubject="My Demographic Data ";

                sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);

                startActivity(Intent.createChooser(sharingIntent,"Share Using "));


            }
        });

        mButtonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            addUser();
            }
        });

        btnViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Bmi2Activity.this,ViewHistory.class);
                startActivity(intent);
            }
        });


    }//On Create

    private void addUser()
    {
        String date=tvdate.getText().toString();
        String bmi_value=tvbmires.getText().toString();
        String category=tvbmicom.getText().toString();

        //unique node key (id)

        String id =databaseUsers.push().getKey();

        User user=new User(id,date,bmi_value,category);

        databaseUsers.child(id).setValue(user);

        Toast.makeText(this, " Saved Successfully ", Toast.LENGTH_SHORT).show();

    }


    private void speak()
    {

        String  mi=tvbmires.getText().toString();
        String cat=tvbmicom.getText().toString();
        String text=mi + "and" + cat;

        float pitch= (float)mSeekBarPitch.getProgress()/50;
        if(pitch<0.1)
            pitch=0.1f;
        float speed= (float)mSeekBarSpeed.getProgress()/50;
        if(speed<0.1)
            speed=0.1f;

        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);
        mTTS.speak(text,TextToSpeech.QUEUE_FLUSH,null);

    }

}
