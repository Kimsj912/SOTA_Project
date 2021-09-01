package com.example.memod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        ImageView home = (ImageView) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostActivity.this , GroupScreen.class);
                startActivity(intent);
            }
        });

        ImageView imageView = findViewById(R.id.imageView4);
        imageView.setBackground(new ShapeDrawable((new OvalShape())));
        imageView.setClipToOutline(true);
    }
}