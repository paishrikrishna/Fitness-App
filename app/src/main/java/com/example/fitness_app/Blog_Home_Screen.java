package com.example.fitness_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Blog_Home_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_home_screen);
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_left);
    }
}