package com.example.memod;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
    }
}