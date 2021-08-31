package com.example.memod;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class GroupCreateSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creat_success);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable()  {
            public void run() {
                // 시간 지난 후 GroupScreen으로 감. (해당 그룹의 정보를 가지고)
                Intent intent = new Intent(GroupCreateSuccess.this,GroupScreen.class);
                startActivity(intent);
            }
        }, 2000); // 5초후
    }
}