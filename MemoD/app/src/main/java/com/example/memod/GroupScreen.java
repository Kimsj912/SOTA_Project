package com.example.memod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GroupScreen extends AppCompatActivity {

    private  Intent intent;
    private long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupmain);

        intent = getIntent();
        id = (long)intent.getExtras().get("id");

        TextView group_name = findViewById(R.id.group_name);
        ImageView profile_image = findViewById(R.id.profile_image);
        ImageView map = findViewById(R.id.map);
        ImageView self_profile = findViewById(R.id.self_profile);
        TextView name = findViewById(R.id.name);
        TextView self_intro = findViewById(R.id.self_intro);

        // for test
        if (id==1) {
            group_name.setText("서대문 모여라~");
            profile_image.setImageResource(R.drawable.seodaemungu);
            profile_image.setClipToOutline(true);
            map.setImageResource(R.drawable.seodaemungumap);
            self_profile.setImageResource(R.drawable.userimage1);
            name.setText("서대문구 대장");
            self_intro.setText("서대문구 투어 같이 가실분 친구신청 바람 ~~");

        } else if (id==2) {
            group_name.setText("종로 맛집 탐방단");
            profile_image.setClipToOutline(true);
            profile_image.setImageResource(R.drawable.jongro);
            map.setImageResource(R.drawable.jongromap);
            self_profile.setImageResource(R.drawable.userimage2);
            name.setText("종로👍");
            self_intro.setText("종로를 사랑하는 사람");

        } else if (id==3) {
            group_name.setText("라멘 맛있당");
            profile_image.setImageResource(R.drawable.ramen);
            self_profile.setImageResource(R.drawable.userimage3);
            name.setText("라-- o^o ----면");
            self_intro.setText("나는 국밥보다 라면이 더 좋음. 최애는 인라면");
        }
        ImageView goback = (ImageView) findViewById(R.id.goback);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupScreen.this , HomeNew.class);
                startActivity(intent);
            }
        });



        // common bar
         ImageView home = (ImageView) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupScreen.this , HomeNew.class);
                startActivity(intent);
            }
        });
        ImageView chart = (ImageView) findViewById(R.id.chart);
        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupScreen.this , PostActivity.class);
                startActivity(intent);
            }
        });
        ImageView plus = (ImageView) findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupScreen.this , MakePost.class);
                startActivity(intent);
            }
        });
        ImageView alert = findViewById(R.id.alert);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupScreen.this , Notification.class);
                startActivity(intent);
            }
        });

        ImageView my_page = findViewById(R.id.my_page);
        my_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupScreen.this , MypageMap.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(GroupScreen.this, "test : "+id,Toast.LENGTH_LONG);

    }
}
