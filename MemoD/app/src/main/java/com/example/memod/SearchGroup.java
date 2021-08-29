package com.example.memod;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchGroup extends AppCompatActivity {

    private TextView total;
    private TextView post;
    private TextView user;
    private ImageView group_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_group);

        TextView total = (TextView) findViewById(R.id.total);
        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchGroup.this , SearchTotal.class);
                startActivity(intent);
            }
        });

        TextView post = (TextView) findViewById(R.id.post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchGroup.this , SearchPost.class);
                startActivity(intent);
            }
        });

        TextView user = (TextView) findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchGroup.this , SearchUser.class);
                startActivity(intent);
            }
        });

        ImageView group_back = (ImageView) findViewById(R.id.group_back);
        group_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchGroup.this , HomeNew.class);
                startActivity(intent);
            }
        });


        // set List ---->
        // <test Data>
        ArrayList<ListGeneral> dataList = new ArrayList<ListGeneral>();
        dataList.add(new ListGroup("서대문 모여라~","서대문구 주민분들을 환영합니다.","url1"));
        dataList.add(new ListGroup("종로 맛집 탐방단","종로 근처 맛집을 함께 공유하는 곳!","url2"));

        // insert data into listview
        ListView listView = (ListView) findViewById(R.id.total_userlistview);
        final MyListAdapter myListAdapter = new MyListAdapter(this,dataList){
            @Override
            public View getView(int position, View converView, ViewGroup parent) {
                View view = mLayoutInflater.inflate(R.layout.activity_group_list_component, null,false);

                ImageView group_image = (ImageView)view.findViewById(R.id.group_image);
                TextView group_name = (TextView)view.findViewById(R.id.group_name);
                TextView group_intro = (TextView)view.findViewById(R.id.group_intro);

//            user_image.setImageResource(sample.get(position).getUserProfileUrl());
                group_image.setImageResource(R.drawable.back);
                group_name.setText(((ListGroup)sample.get(position)).getGroupname());
                group_intro.setText(((ListGroup)sample.get(position)).getGroupIntro());

                return view;
            }
        };

        listView.setAdapter(myListAdapter) ;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        myListAdapter.getItem(position).getName(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}