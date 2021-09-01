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


        ImageView home = (ImageView) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Notification.this , HomeNew.class);
                startActivity(intent);
            }
        });

        ArrayList<ListGeneral> dataList = new ArrayList<ListGeneral>();
        dataList.add(new ListNorification(null ,"서대문구 주민분들을 환영합니다.",null));
        dataList.add(new ListNorification(null,"종로 근처 맛집을 함께 공유하는 곳!",null));
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
                image2.setImageResource(R.drawable.bear);

                return view;
            }
        };
        listView.setAdapter(myListAdapter) ;

    }
}