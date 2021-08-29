package com.example.memod;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
    }
}