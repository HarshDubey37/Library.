package com.jtp.library;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.jtp.library.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {
    ActivitySplashScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d("splash","show animation 1 ");

        Animation ammm= AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.movedata);
        LinearLayout ssl=findViewById(R.id.spll);
        Log.d("splash","show animation 2 ");
        Intent i=new Intent(SplashScreenActivity.this,MainActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                Log.d("splash","show animation 3");

                finish();
                Log.d("splash","show animation 5");

            }
        },850);
    }
}