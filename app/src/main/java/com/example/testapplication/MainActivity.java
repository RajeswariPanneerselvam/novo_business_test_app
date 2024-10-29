package com.example.testapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView
                = findViewById(R.id.bottomNavigationView);

        bottomNavigationView
                .setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setItemIconTintList(null);
    }
     HomeFragment homeFragment = new HomeFragment();
     BankFragment bankFragment = new BankFragment();
     HistoryFragment historyFragment = new HistoryFragment();
     ProfileFragment profileFragment = new ProfileFragment();
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if(menuItem.getItemId()==R.id.home){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, homeFragment)
                    .commit();
            return true;
        }
        else if(menuItem.getItemId()==R.id.bank){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, bankFragment)
                    .commit();
            return true;
        }
        else if(menuItem.getItemId()==R.id.history){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, historyFragment)
                    .commit();
            return true;
        }
        else{
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, profileFragment)
                    .commit();
            return true;
        }
    }
}