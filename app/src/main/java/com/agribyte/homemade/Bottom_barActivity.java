package com.agribyte.homemade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Bottom_barActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
BottomNavigationView navigation;
TextView logoutbtn;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_bar);
        navigation = findViewById(R.id.navigation);
        logoutbtn =findViewById(R.id.txtlogout);
        imageView = findViewById(R.id.imageView);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth autho = FirebaseAuth.getInstance();
                autho.signOut();
                Intent i6 = new Intent(Bottom_barActivity.this, SplashActivity.class);
                startActivity(i6);
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.home)
        {
replaceFragment(new HomeFragment());
return true;
        } else if (itemId==R.id.product) {
            replaceFragment(new ProductFragment());
            return true;

        }

        return false;
    }



    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(androidx.fragment.R.id.fragment_container_view_tag, fragment);
        transaction.commit();
    }
}