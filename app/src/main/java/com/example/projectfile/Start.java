package com.example.projectfile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Start extends AppCompatActivity {

    ImageView img_top, img_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        img_top = findViewById(R.id.img_top);
        img_bottom = findViewById(R.id.img_bottom);

        Animation animT = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.logo1);
        Animation animB = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.logo2);
        img_top.startAnimation(animT);
        img_bottom.startAnimation(animB);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Start.this, Login.class);
                startActivity(intent);
                finish();
            }
        }, 4700);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        },2000);
    }
}