package com.example.memod;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomeNew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_new);

        // for test (logout)
        FirebaseAuth firebaseAuth =  FirebaseAuth.getInstance();

        ImageView test = findViewById(R.id.backToLogin);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(HomeNew.this, MainActivity.class));
            }
        });

//        EditText info = findViewById(R.id.information);
//        String infotext = info.getText().toString();
        ImageView search_btn =  findViewById(R.id.search_btn);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeNew.this , SearchTotal.class);
                startActivity(intent);
            }
        });

        ImageView alert = (ImageView) findViewById(R.id.alert);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeNew.this , Notification.class);
                startActivity(intent);
            }
        });
        LinearLayout mypage_map = (LinearLayout) findViewById(R.id.mypage_map);
        mypage_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeNew.this , MypageMap.class);
                startActivity(intent);
            }
        });
        ImageView chart = (ImageView) findViewById(R.id.chart);
        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeNew.this , PostActivity.class);
                startActivity(intent);
            }
        });

        ImageView plus = (ImageView) findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeNew.this , MakePost.class);
                startActivity(intent);
            }
        });


        // set List ---->
        // <test Data>
        ArrayList<ListGeneral> dataList = new ArrayList<ListGeneral>();
        dataList.add(new ListHomeGroup(null , null,null));
        dataList.add(new ListHomeGroup("서대문 모여라~",null,"url1"));
        dataList.add(new ListHomeGroup("종로 맛집 탐방단",null,"url2"));
        dataList.add(new ListHomeGroup("라멘 맛있당" , null,"url3"));

        // insert data into listview
        GridView listView = (GridView) findViewById(R.id.total_userlistview);
        final MyGridviewListAdapter myListAdapter = new MyGridviewListAdapter(this,dataList){
            @Override
            public View getView(int position, View converView, ViewGroup parent) {
                View view = null;
                if(((ListHomeGroup) sample.get(position)).getPostTitle()==null){ // 마지막에
                    view = mLayoutInflater.inflate(R.layout.activity_home_group_list_new_component, null, false);
                } else { // 그외엔 기존 그룹들 추가
                    view = mLayoutInflater.inflate(R.layout.activity_home_group_list_component, null, false);

                    // view Attribute setting
                    LinearLayout layout = view.findViewById(R.id.background);
                    ImageView photo = (ImageView) view.findViewById(R.id.photo);
                    TextView group_name_in = (TextView) view.findViewById(R.id.group_name_in);

                    // view Value

                    if(((ListHomeGroup) sample.get(position)).getPostImgUrl()=="url1") {
                        photo.setImageResource(R.drawable.seodaemungu);
                        layout.setBackgroundResource(R.drawable.group_creat_border2);
                    } else  if(((ListHomeGroup) sample.get(position)).getPostImgUrl()=="url2") {
                        photo.setImageResource(R.drawable.jongro);
                        layout.setBackgroundResource(R.drawable.group_creat_border3);
                    } else  if(((ListHomeGroup) sample.get(position)).getPostImgUrl()=="url3") {
                        photo.setImageResource(R.drawable.ramen);
                        layout.setBackgroundResource(R.drawable.group_creat_border);
                    }
                    group_name_in.setText(((ListHomeGroup) sample.get(position)).getPostTitle());
                }
                return view;
            }
        };

        listView.setAdapter(myListAdapter) ;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent=null;
                if(myListAdapter.getItem(position).getName()==null) {
                    intent = new Intent(HomeNew.this, GroupCreate.class);
                }else {
                    intent = new Intent(HomeNew.this, GroupScreen.class);
                    intent.putExtra("id",id);
                }
                startActivity(intent);
            }
        });
    }
}