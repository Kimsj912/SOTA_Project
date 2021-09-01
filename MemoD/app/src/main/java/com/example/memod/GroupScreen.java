package com.example.memod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GroupScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupmain);


        ImageView goback = (ImageView) findViewById(R.id.goback);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupScreen.this , HomeNew.class);
                startActivity(intent);
            }
        });

        ImageView plus = (ImageView) findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupScreen.this , MakePost.class);
                startActivity(intent);
            }
        });
        ImageView home = (ImageView) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupScreen.this , HomeNew.class);
                startActivity(intent);
            }
        });
        ImageView chart = (ImageView) findViewById(R.id.chart);
        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupScreen.this , PostActivity.class);
                startActivity(intent);
            }
        });

    }
}
