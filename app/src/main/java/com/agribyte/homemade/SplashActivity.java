package com.agribyte.homemade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Time;

public class SplashActivity extends AppCompatActivity {
TextView hi;
private static final int SPLASH_DELAY = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        hi = findViewById(R.id.hi);
new Handler().postDelayed(new Runnable() {
    FirebaseAuth mautho = FirebaseAuth.getInstance();
    FirebaseUser user = mautho.getCurrentUser();

    @Override
    public void run() {
        if(user!=null)
        {
            Intent i = new Intent(SplashActivity.this,Bottom_barActivity.class);
            startActivity(i);
        }
        else {
            Intent i1 = new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(i1);
        }

    }
},SPLASH_DELAY);
    }
}