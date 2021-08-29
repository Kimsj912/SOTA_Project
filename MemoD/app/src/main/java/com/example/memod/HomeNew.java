package com.example.memod;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

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




    }
}