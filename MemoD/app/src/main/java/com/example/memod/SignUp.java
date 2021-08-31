package com.example.memod;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUp extends AppCompatActivity {

    // Variable
    private static final String TAG = "SignUp-Activity";
    private boolean checkPhone;

    private FirebaseAuth firebaseAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    // Attribute
    private ImageView backToLoginBtn;
    private EditText emailTxt, passwordTxt, passwordCheckTxt, nameTxt, phoneTxt;
    private Button next_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //파이어베이스 접근 설정 -> phone 인증을 위함.
        firebaseAuth =  FirebaseAuth.getInstance();

        // Common btn
        backToLoginBtn = findViewById(R.id.backToLogin);
        backToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // Attribute
        emailTxt = findViewById(R.id.email);
        passwordTxt = findViewById(R.id.pw);
        passwordCheckTxt = findViewById(R.id.pwCheck);
        nameTxt = findViewById(R.id.name);

        phoneTxt = findViewById(R.id.phone);
        next_btn = findViewById(R.id.next);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //가입 정보 가져오기
                String email = emailTxt.getText().toString().trim();
                String pwd = passwordTxt.getText().toString().trim();
                String pwdcheck = passwordCheckTxt.getText().toString().trim();
                String name = nameTxt.getText().toString().trim();
                String phone = phoneTxt.getText().toString().trim();

                // 빈칸이 없고, pwd도 서로 일치하고, phone인증도 완료되었을 떄는 프로필 설정으로 넘어감.
                if (noEmpty() && checkPWD(pwd, pwdcheck)&&isFormRight()) {
                    Intent intent = new Intent(SignUp.this, Profile.class);
                    intent.putExtra("email",email);
                    intent.putExtra("pwd",pwd);
                    intent.putExtra("name",name);
                    intent.putExtra("phone",phone);
                    startActivity(intent);
                }
            }
        });

    }

    private boolean isFormRight() {
        if (!emailTxt.getText().toString().contains("@")||!emailTxt.getText().toString().contains(".")) {
            Toast.makeText(SignUp.this, "이메일의 형식이 옳지 않습니다.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (passwordTxt.getText().toString().length()<6) {
            Toast.makeText(SignUp.this, "비밀번호의 형식이 옳지 않습니다.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // Method - basic
    private boolean checkPWD(String pwd, String pwdcheck) {
        if (!pwd.equals(pwdcheck)) {
            Toast.makeText(SignUp.this, "비밀번호와 비밀번호 확인이 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean noEmpty() {
        if (TextUtils.isEmpty(emailTxt.getText())) {
            Toast.makeText(SignUp.this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(passwordTxt.getText())) {
            Toast.makeText(SignUp.this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(passwordCheckTxt.getText())) {
            Toast.makeText(SignUp.this, "확인비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.isEmpty(nameTxt.getText())){
            Toast.makeText(SignUp.this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.isEmpty(phoneTxt.getText())){
            Toast.makeText(SignUp.this, "전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}