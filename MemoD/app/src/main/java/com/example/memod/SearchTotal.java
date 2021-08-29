package com.example.memod;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

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

    }
}