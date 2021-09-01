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

public class SearchPost extends AppCompatActivity {

    private TextView total;
    private TextView group;
    private TextView user;
    private ImageView post_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_post);


        TextView total = (TextView) findViewById(R.id.total);
        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchPost.this , SearchTotal.class);
                startActivity(intent);
            }
        });

        TextView group = (TextView) findViewById(R.id.group);
        group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchPost.this , SearchGroup.class);
                startActivity(intent);
            }
        });

        TextView user = (TextView) findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchPost.this , SearchUser.class);
                startActivity(intent);
            }
        });

        ImageView group_back = (ImageView) findViewById(R.id.post_back);
        group_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchPost.this , HomeNew.class);
                startActivity(intent);
            }
        });

        ImageView alert = (ImageView) findViewById(R.id.alert);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchPost.this , Notification.class);
                startActivity(intent);
            }
        });

        ImageView chart = (ImageView) findViewById(R.id.chart);
        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchPost.this , PostActivity.class);
                startActivity(intent);
            }
        });
        ImageView plus = (ImageView) findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchPost.this , MakePost.class);
                startActivity(intent);
            }
        });

        // set List ---->
        // <test Data>
        ArrayList<ListGeneral> dataList = new ArrayList<ListGeneral>();
        dataList.add(new ListPost("김철수","김철부지","Imageurl1"));
        dataList.add(new ListPost("김영희","영희의 자기소개입니당 :)","Imageurl2"));
        dataList.add(new ListPost("한여름","여름의 자기소개","Imageurl3"));

        // insert data into listview
        ListView listView = (ListView) findViewById(R.id.total_userlistview);
        final MyListAdapter myListAdapter = new MyListAdapter(this,dataList){
            @Override
            public View getView(int position, View converView, ViewGroup parent) {
                View view = mLayoutInflater.inflate(R.layout.activity_post_list_component, null,false);

                ImageView post_image = (ImageView)view.findViewById(R.id.post_image);
                TextView post_title = (TextView)view.findViewById(R.id.post_title);
                TextView post_context = (TextView)view.findViewById(R.id.post_context);

//            user_image.setImageResource(sample.get(position).getUserProfileUrl());
                post_image.setImageResource(R.drawable.back);
                post_title.setText(((ListPost)sample.get(position)).getPostTitle());
                post_context.setText(((ListPost)sample.get(position)).getPostContext());

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