package com.example.memod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {

    private ImageView home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

        // common bar
        ImageView home = (ImageView) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Notification.this , HomeNew.class);
                startActivity(intent);
            }
        });

        ImageView chart = findViewById(R.id.chart);
        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Notification.this , PostActivity.class);
                startActivity(intent);
            }
        });

        ImageView plus = findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Notification.this , MakePost.class);
                startActivity(intent);
            }
        });

        ImageView alert = findViewById(R.id.alert);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Notification.this , Notification.class);
                startActivity(intent);
            }
        });

        ImageView my_page = findViewById(R.id.my_page);
        my_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Notification.this , MypageMap.class);
                startActivity(intent);
            }
        });


        // list set -->
        ArrayList<ListGeneral> dataList = new ArrayList<ListGeneral>();
        dataList.add(new ListNorification(null ,"최고운님이 유은서님의 2021년 8월 31일의 게시물에 댓글을 달았습니다 \"노래랑 완전 어울리는 장소다 ㅜㅜ\"","1"));
        dataList.add(new ListNorification(null,"최고운님이 유은서님의 2021년 5월 15일의 게시물에 댓글을 달았습니다 \"여긴 어디야?? 나도 데려가\"","2"));
        // insert data into listview
        ListView listView = (ListView) findViewById(R.id.ListView);
        final MyListAdapter myListAdapter = new MyListAdapter(this,dataList){
            @Override
            public View getView(int position, View converView, ViewGroup parent) {
                View view = mLayoutInflater.inflate(R.layout.list_row, null,false);

                ImageView image = (ImageView)view.findViewById(R.id.image);
                TextView txtName = (TextView)view.findViewById(R.id.txtName);
                ImageView image2 = (ImageView)view.findViewById(R.id.image2);

//            user_image.setImageResource(sample.get(position).getUserProfileUrl());
                image.setImageResource(R.drawable.rabbit);
                txtName.setText(((ListNorification)sample.get(position)).getTxtName());
                if(((ListNorification)sample.get(position)).getImage2()=="1") {
                    image2.setImageResource(R.drawable.seodaemungu);
                } else{
                    image2.setImageResource(R.drawable.ramen);

                }


                return view;
            }
        };
        listView.setAdapter(myListAdapter) ;

    }
}