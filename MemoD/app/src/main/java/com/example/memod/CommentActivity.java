package com.example.memod;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        // set List ---->
        // <test Data>
        ArrayList<ListGeneral> dataList = new ArrayList<ListGeneral>();
        dataList.add(new ListGroup("유은서","서대문구 주민분들을 환영합니다.","url1"));
        dataList.add(new ListGroup("김수정","종로 근처 맛집을 함께 공유하는 곳!","url2"));
        // insert data into listview
        ListView listView = (ListView) findViewById(R.id.comment_list);
        final MyListAdapter myListAdapter = new MyListAdapter(this,dataList){
            @Override
            public View getView(int position, View converView, ViewGroup parent) {
                View view = mLayoutInflater.inflate(R.layout.activity_user_list_component, null,false);

                ImageView user_image = (ImageView)view.findViewById(R.id.user_image);
                TextView user_name = (TextView)view.findViewById(R.id.user_name);
                TextView user_inform = (TextView)view.findViewById(R.id.user_inform);

//            user_image.setImageResource(sample.get(position).getUserProfileUrl());
                user_image.setImageResource(R.drawable.comment);
                user_name.setText(((ListGroup)sample.get(position)).getGroupname());
                user_inform.setText(((ListGroup)sample.get(position)).getGroupIntro());

                return view;
            }
        };
    }
}
