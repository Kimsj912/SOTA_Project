package com.example.memod;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private EditText  self_intro, user_id, name, pw, ph,birth_year,sex, location1,location2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        self_intro = findViewById(R.id.self_intro);
        user_id = findViewById(R.id.user_id);
        name = findViewById(R.id.name);
        pw = findViewById(R.id.pw);
        ph = findViewById(R.id.ph);
        birth_year = findViewById(R.id.birth_year);
        sex = findViewById(R.id.sex);
        location1 = findViewById(R.id.location1);
        location2 = findViewById(R.id.location2);







        ImageView close = (ImageView) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfileActivity.this , MypageMap.class);
                startActivity(intent);
            }
        });

        ImageView alert = (ImageView) findViewById(R.id.alert);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfileActivity.this , Notification.class);
                startActivity(intent);
            }
        });

        ImageView home = (ImageView) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfileActivity.this , HomeNew.class);
                startActivity(intent);
            }
        });

        ImageView chart = (ImageView) findViewById(R.id.chart);
        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfileActivity.this , PostActivity.class);
                startActivity(intent);
            }
        });


}}

