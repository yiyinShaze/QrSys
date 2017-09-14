package com.example.mheljohn.qrsys;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageSplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);


        /*getSupportActionBar().hide();
        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
*/
        imageSplash = (ImageView) findViewById(R.id.imageSplash);

        AnimationSet animation = new AnimationSet(true);
        animation.addAnimation(new AlphaAnimation(0.0F, 1.0F));
        animation.addAnimation(new ScaleAnimation(0.0f, 1, 0.0f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f));
        animation.setDuration(1000);

        imageSplash.startAnimation(animation);

        new CountDownTimer(3000, 500){

            public void onTick(long millisUntilFinished){

            }

            public void onFinish(){

                Intent startActivityIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(startActivityIntent);
                SplashActivity.this.finish();
            }
        }.start();
    }
}
