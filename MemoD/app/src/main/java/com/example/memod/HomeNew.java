package com.example.memod;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
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


        EditText information = (EditText) findViewById(R.id.information);
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeNew.this , SearchTotal.class);
                startActivity(intent);
            }
        });


        // set List ---->
        // <test Data>
        ArrayList<ListGeneral> dataList = new ArrayList<ListGeneral>();
        dataList.add(new ListHomeGroup("서대문 모여라~",null,"url1"));
        dataList.add(new ListHomeGroup("종로 맛집 탐방단",null,"url2"));
        dataList.add(new ListHomeGroup("라멘 맛있당" , null,"url3"));

        // insert data into listview
        GridView listView = (GridView) findViewById(R.id.total_userlistview);
        final MyListAdapter myListAdapter = new MyListAdapter(this,dataList){
            @Override
            public View getView(int position, View converView, ViewGroup parent) {
                View view = mLayoutInflater.inflate(R.layout.activity_home_group_list_component, null,false);

                // view Attribute setting
                ImageView photo = (ImageView)view.findViewById(R.id.photo);
                TextView group_name_in = (TextView)view.findViewById(R.id.group_name_in);

                // view Value
//            user_image.setImageResource(sample.get(position).getUserProfileUrl());
                photo.setImageResource(R.drawable.back);
                group_name_in.setText(((ListHomeGroup)sample.get(position)).getPostTitle());
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