package com.example.memod;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SearchTotal extends AppCompatActivity {

    private TextView group;
    private TextView post;
    private TextView user;
    private ImageView total_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_total);

        TextView information = (TextView) findViewById(R.id.group);
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchTotal.this , SearchGroup.class);
                startActivity(intent);
            }
        });

        TextView post = (TextView) findViewById(R.id.post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchTotal.this , SearchPost.class);
                startActivity(intent);
            }
        });

        TextView user = (TextView) findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchTotal.this , SearchUser.class);
                startActivity(intent);
            }
        });

        ImageView total_back = (ImageView) findViewById(R.id.total_back);
        total_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchTotal.this , HomeNew.class);
                startActivity(intent);
            }
        });

        ImageView alert = (ImageView) findViewById(R.id.alert);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchTotal.this , Notification.class);
                startActivity(intent);
            }
        });
        ImageView chart = (ImageView) findViewById(R.id.chart);
        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchTotal.this , PostActivity.class);
                startActivity(intent);
            }
        });

        // Group - set List ---->
        // <test Data>
        ArrayList<ListGeneral> dataList = new ArrayList<ListGeneral>();
        dataList.add(new ListGroup("서대문 모여라~","서대문구 주민분들을 환영합니다.","url1"));
        dataList.add(new ListGroup("종로 맛집 탐방단","종로 근처 맛집을 함께 공유하는 곳!","url2"));

        // insert data into listview
        ListView listView = (ListView) findViewById(R.id.total_groupListView);
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

        // Post - set List ---->
        // <test Data>
        ArrayList<ListGeneral> postDataList = new ArrayList<ListGeneral>();
        postDataList.add(new ListPost("김철수","김철부지","Imageurl1"));
        postDataList.add(new ListPost("김영희","영희의 자기소개입니당 :)","Imageurl2"));
        postDataList.add(new ListPost("한여름","여름의 자기소개","Imageurl3"));

        // insert data into listview
        ListView postListView = (ListView) findViewById(R.id.total_postListView);
        MyListAdapter myPostListAdapter = new MyListAdapter(this,postDataList){
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

        postListView.setAdapter(myPostListAdapter) ;
        postListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        myPostListAdapter.getItem(position).getName(),
                        Toast.LENGTH_LONG).show();
            }
        });


        // User - set List ---->
        // <test Data>
        ArrayList<ListGeneral> UserDataList = new ArrayList<ListGeneral>();
        UserDataList.add(new ListUser("미션임파서블","15세 이상관람가","url1"));
        UserDataList.add(new ListUser("아저씨","19세 이상관람가","url2"));
        UserDataList.add(new ListUser("어벤져스","12세 이상관람가","url3"));

        // insert data into listview
        ListView userListView = (ListView) findViewById(R.id.total_userlistview);
        final MyListAdapter myUserListAdapter = new MyListAdapter(this,UserDataList){
            @Override
            public View getView(int position, View converView, ViewGroup parent) {
                View view = mLayoutInflater.inflate(R.layout.activity_user_list_component, null,false);

                ImageView user_image = (ImageView)view.findViewById(R.id.user_image);
                TextView user_name = (TextView)view.findViewById(R.id.user_name);
                TextView user_inform = (TextView)view.findViewById(R.id.user_inform);

//            user_image.setImageResource(sample.get(position).getUserProfileUrl());
                user_image.setImageResource(R.drawable.back);
                user_name.setText(((ListUser)sample.get(position)).getUsername());
                user_inform.setText(((ListUser)sample.get(position)).getUserInfo());

                return view;
            }
        };
        userListView.setAdapter(myUserListAdapter) ;

        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        myUserListAdapter.getItem(position).getName(),
                        Toast.LENGTH_LONG).show();
            }
        });



    }
}