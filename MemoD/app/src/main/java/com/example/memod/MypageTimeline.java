package com.example.memod;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MypageTimeline extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_timeline);

        Button change_info = (Button) findViewById(R.id.change_info);
        change_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MypageTimeline.this , EditProfileActivity.class);
                startActivity(intent);
            }
        });

        Button my_map = (Button) findViewById(R.id.my_map);
        my_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MypageTimeline.this , MypageMap.class);
                startActivity(intent);
            }
        });
        ImageView alert = (ImageView) findViewById(R.id.alert);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MypageTimeline.this , Notification.class);
                startActivity(intent);
            }
        });

}}
