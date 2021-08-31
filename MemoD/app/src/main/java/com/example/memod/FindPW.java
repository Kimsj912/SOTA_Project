package com.example.memod;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class FindPW extends AppCompatActivity {

    private TextView findID_btn,findPW_btn;
    private EditText editTextUserEmail
            , phone_num, confirm_num;
    private Button email_btn, confirm_btn, ok_btn, find_btn;
    private ImageView backToLoginBtn;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpw);

        firebaseAuth =  FirebaseAuth.getInstance();

        // attribute
        backToLoginBtn = findViewById(R.id.backToLogin);
        editTextUserEmail = findViewById(R.id.findPW_email);
        find_btn = findViewById(R.id.change_btn);

        // common
        backToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindPW.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // special
        find_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //비밀번호 재설정 이메일 보내기
                String emailAddress = editTextUserEmail.getText().toString().trim();
                Toast.makeText(FindPW.this, "Test Email : "+emailAddress, Toast.LENGTH_SHORT).show();
                firebaseAuth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<java.lang.Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(FindPW.this, "이메일을 보냈습니다.", Toast.LENGTH_LONG).show();
                                    finish();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                } else {
                                    Toast.makeText(FindPW.this, "해당 이메일을 찾을 수 없습니다. 다시 입력해주세요.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                }
            });
    }
}