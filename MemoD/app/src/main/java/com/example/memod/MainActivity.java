package com.example.memod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText mEmailText, mPasswordText;
    TextView mResigertxt, mFindtxt;
    Button mLoginBtn;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //파이어베이스 접근 설정
        // user = firebaseAuth.getCurrentUser();
        firebaseAuth =  FirebaseAuth.getInstance();
        //firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        mEmailText = findViewById(R.id.login_email);
        mPasswordText = findViewById(R.id.login_pw);
        mFindtxt = findViewById(R.id.login_findTV);
        mLoginBtn = findViewById(R.id.login_btn);
        mResigertxt = findViewById(R.id.login_signupBtn);

        //find 버튼이 눌리면
        mFindtxt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //intent함수를 통해 find Activity 함수를 호출한다.
                Toast.makeText(MainActivity.this,"find id/pw",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, FindID.class);
                startActivity(intent);
            }
        });

        //가입 버튼이 눌리면
        mResigertxt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //intent함수를 통해 Signup액티비티 함수를 호출한다.
                Toast.makeText(MainActivity.this,"가입 시도",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);

            }
        });

        //로그인 버튼이 눌리면
        mLoginBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"로그인 시도",Toast.LENGTH_LONG).show();
                mLoginBtn.setBackgroundColor(Color.BLACK);
                String email = mEmailText.getText().toString().trim();
                String pwd = mPasswordText.getText().toString().trim();
                firebaseAuth.signInWithEmailAndPassword(email,pwd)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Login Success : ", "signInWithEmail:success");
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("Login Fail : ", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        });

            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser != null){
            updateUI(currentUser);
        }
    }

    private void updateUI(FirebaseUser user) {
        if(user!=null){
            Toast.makeText(MainActivity.this, "login success and will change view to homeview", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, HomeNew.class);
            intent.putExtra("user",user);
            startActivity(intent);
        }
    }

}
