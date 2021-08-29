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

    // TODO:제거
//    private EditText[] pocket;
//    private boolean isTextFilled;

    // Attribute
    private ImageView backToLoginBtn;
    private EditText emailTxt, passwordTxt, passwordCheckTxt, nameTxt, phoneTxt, confirmNumTxt;
    private Button confirm_btn, ok_btn, next_btn;

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
        confirm_btn = findViewById(R.id.confirm_btn);

        confirmNumTxt = findViewById(R.id.confirmNum);
        ok_btn = findViewById(R.id.ok);
        ok_btn.setClickable(false);

        next_btn = findViewById(R.id.next);

        //
//        pocket = new EditText[6];
//        pocket[0]=emailTxt;
//        pocket[1]=passwordTxt;
//        pocket[2]=passwordCheckTxt;
//        pocket[3]=nameTxt;
//        pocket[4]=phoneTxt;
//        pocket[5]=confirmNumTxt;


        // add Listener
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(confirm_btn.getText().toString().equals("인증하기")) {
                    if (!TextUtils.isEmpty(phoneTxt.getText())) {
                        ok_btn.setClickable(true);
                        confirm_btn.setText("재전송하기");

                    } else
                        Toast.makeText(SignUp.this, "전화번호 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                } else if(confirm_btn.getText().toString().equals("재전송하기")){
                    resendVerificationCode(phoneTxt.getText().toString(),mResendToken);

                }
            }
        });

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyPhoneNumberWithCode(mVerificationId,confirmNumTxt.getText().toString());
            }
        });
        
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
                if (noEmpty() && checkPWD(pwd, pwdcheck)
//                        && checkPhone
                ) {
                    Intent intent = new Intent(SignUp.this, Profile.class);
                    intent.putExtra("email",email);
                    intent.putExtra("pwd",pwd);
                    intent.putExtra("name",name);
                    intent.putExtra("phone",phone);
                    startActivity(intent);
                }
            }
        });
        next_btn.addTextChangedListener(new myTextWatcher());

        // initialize phone auth callback
        // PhoneAuthCredential인 mcallbacks 객체를 이용하여 사용자를 로그인할 수 있다.

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override // success
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // 즉각 확인
                Log.d(TAG, "onVerificationCompleted:" + credential);
                next_btn.setClickable(true);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // 이 메서드는 요청에 잘못된 전화번호 또는 인증 코드가 지정된 경우와 같이 잘못된 인증 요청에 대한 응답으로 호출됩니다.
                Log.w(TAG, "onVerificationFailed", e);
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    Toast.makeText(SignUp.this, "옳지 않은 요청입니다. ", Toast.LENGTH_SHORT).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    Toast.makeText(SignUp.this, "문자 인증 횟수를 초과하였습니다. ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);
                Toast toast= Toast.makeText(SignUp.this, "입력시간이 초과되었습니다. 다시 시도해주세요.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        };
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
        } else if(TextUtils.isEmpty(confirmNumTxt.getText())) {
            Toast.makeText(SignUp.this, "인증번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    // TODO: 사용X 제거하기
    private class myTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
//            isTextFilled = true;
        }

        @Override
        public void afterTextChanged(Editable editable) {
//            for(EditText view : pocket ){
//                if(TextUtils.isEmpty(view.getText())) {
//                    isTextFilled = false;
//                }
//            }
//            if(isTextFilled) next_btn.setBackgroundColor(R.drawable.findborderline);
        }
    }

    // Method - Phone
    private void startPhoneNumberVerification(String phoneNumber) {
        // [START start_phone_auth]
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(5L, TimeUnit.MINUTES) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        // [END start_phone_auth]
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        try{
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
            this.checkPhone=true;
        }catch (Exception e){
            Toast toast = Toast.makeText(this, "코드가 옳지 않습니다.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
    }

    // [START resend_verification]
    private void resendVerificationCode(String phoneNumber, PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(5L, TimeUnit.MINUTES) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .setForceResendingToken(token)     // ForceResendingToken from callbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }


}