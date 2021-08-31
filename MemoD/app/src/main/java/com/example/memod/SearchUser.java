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

public class SearchUser extends AppCompatActivity {

    private TextView total;
    private TextView post;
    private TextView user;
    private ImageView user_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);

        TextView total = (TextView) findViewById(R.id.total);
        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchUser.this , SearchTotal.class);
                startActivity(intent);
            }
        });

        TextView group = (TextView) findViewById(R.id.group);
        group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchUser.this , SearchGroup.class);
                startActivity(intent);
            }
        });

        TextView post = (TextView) findViewById(R.id.post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchUser.this , SearchPost.class);
                startActivity(intent);
            }
        });

        ImageView user_back = (ImageView) findViewById(R.id.user_back);
        user_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchUser.this , HomeNew.class);
                startActivity(intent);
            }
        });
        ImageView alert = (ImageView) findViewById(R.id.alert);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchUser.this , Notification.class);
                startActivity(intent);
            }
        });


        // set List ---->
        // <test Data>
        ArrayList<ListGeneral> dataList = new ArrayList<ListGeneral>();
        dataList.add(new ListUser("미션임파서블","15세 이상관람가","url1"));
        dataList.add(new ListUser("아저씨","19세 이상관람가","url2"));
        dataList.add(new ListUser("어벤져스","12세 이상관람가","url3"));
        dataList.add(new ListUser("미션임파서블","15세 이상관람가","url1"));
        dataList.add(new ListUser("아저씨","19세 이상관람가","url2"));
        dataList.add(new ListUser("어벤져스","12세 이상관람가","url3"));
        dataList.add(new ListUser("미션임파서블","15세 이상관람가","url1"));
        dataList.add(new ListUser("아저씨","19세 이상관람가","url2"));
        dataList.add(new ListUser("어벤져스","12세 이상관람가","url3"));

        // insert data into listview
        ListView listView = (ListView) findViewById(R.id.total_userlistview);
        final MyListAdapter myListAdapter = new MyListAdapter(this,dataList){
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