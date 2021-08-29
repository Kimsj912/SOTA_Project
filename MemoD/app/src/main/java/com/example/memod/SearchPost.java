package com.example.memod;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
    }
}