package com.example.memod;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

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

        ImageView chart = (ImageView) findViewById(R.id.chart);
        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MypageTimeline.this , PostActivity.class);
                startActivity(intent);
            }
        });

        // set List ---->
        // <test Data>
        ArrayList<ListGeneral> dataList = new ArrayList<ListGeneral>();
        dataList.add(new ListTimeLineRow("2021년 8월 22일","여의도 한강공원",null));
        dataList.add(new ListTimeLineRow("2021년 8월 25일","명지대학교 인문캠퍼스",null));
        dataList.add(new ListTimeLineRow("2021년 8월 29일","AK& 홍대",null));
        dataList.add(new ListTimeLineRow("2021년 8월 31일","스타벅스 강남삼성타운점",null));
        // insert data into listview
        ListView listView = (ListView) findViewById(R.id.ListView);
        final MyListAdapter myListAdapter = new MyListAdapter(this,dataList){
            @Override
            public View getView(int position, View converView, ViewGroup parent) {
                View view = mLayoutInflater.inflate(R.layout.timeline_row, null,false);

                TextView date = (TextView)view.findViewById(R.id.date);
                TextView place = (TextView)view.findViewById(R.id.place);

                date.setText(((ListTimeLineRow)sample.get(position)).getDate());
                place.setText(((ListTimeLineRow)sample.get(position)).getPlace());

                return view;
            }
        };

        listView.setAdapter(myListAdapter) ;
    }
}
