package com.example.memod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GroupScreen extends AppCompatActivity {

    private  Intent intent;
    private long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupmain);

        intent = getIntent();
        id = (long)intent.getExtras().get("id");

        TextView group_name = findViewById(R.id.group_name);

        // for test
        if (id==0) group_name.setText("서대문 모여라~");
        else if (id==1) group_name.setText("종로 맛집 탐방단");
        else if (id==2) group_name.setText("라멘 맛있당");


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

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(GroupScreen.this, "test : "+id,Toast.LENGTH_LONG);

    }
}
