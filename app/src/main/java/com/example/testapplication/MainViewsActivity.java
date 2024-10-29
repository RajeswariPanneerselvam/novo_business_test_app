package com.example.testapplication;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainViewsActivity extends AppCompatActivity {

   Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_views);

        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainViewsActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }


}