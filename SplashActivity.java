package com.example.fittestbmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    MediaPlayer ourSound; //global var
private static int SPLASH_SCREEN=5000;
Animation bottomAnim;

TextView title,tagline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        ourSound=MediaPlayer.create(this,R.raw.splash);
        ourSound.start();

        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        title=findViewById(R.id.title);
        tagline=findViewById(R.id.tagline);

        title.setAnimation(bottomAnim);
        tagline.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

    }
  // so that sound stops as splash activity ends
    protected void onPause()
    {
        super.onPause();
        ourSound.release();
        finish();
    }

}
