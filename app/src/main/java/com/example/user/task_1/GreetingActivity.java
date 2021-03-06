package com.example.user.task_1;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GreetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_greeting);

        getSupportActionBar().hide();
        if (savedInstanceState == null) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(GreetingActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);
        }
    }
}
