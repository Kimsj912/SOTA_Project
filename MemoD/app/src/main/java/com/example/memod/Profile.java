package com.example.memod;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class Profile extends AppCompatActivity {

    // Variable
    private Bundle mustInfoBundle;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mDatabase;
    private FirebaseStorage mStorage;
    private Uri imageUri;
    private String pathUri;

    // Attribute
    private ImageView back;
    private ImageView photo;
    private EditText informationTxt, yearTxt, monthTxt, dayTxt, location1Txt, location2Txt;
    private RadioGroup sex;
    private RadioButton selectedSex;
    private Button done;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // super & set View
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // firebase setting
        firebaseAuth =  FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mStorage = FirebaseStorage.getInstance();

        // get Data from before Activty
        mustInfoBundle = getIntent().getExtras();

        // common
        back = findViewById(R.id.backToLogin);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // attribute
        photo = findViewById(R.id.photo);
        informationTxt = findViewById(R.id.information);

        yearTxt = findViewById(R.id.year);
        monthTxt = findViewById(R.id.month);
        dayTxt = findViewById(R.id.day);
        sex = findViewById(R.id.sex);
        selectedSex = (RadioButton) findViewById(sex.getCheckedRadioButtonId());

        location1Txt = findViewById(R.id.location1);
        location2Txt = findViewById(R.id.location2);

        done = findViewById(R.id.done);

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoAlbum();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { signup();}
        });
    }

    // Base Method
    private void myToast(String text){
        Toast.makeText(Profile.this, text, Toast.LENGTH_SHORT).show();
    }

    // special Method
    private boolean successCheck() {
        // signup Success Checker main
        return  noEmpty();
    }

    private boolean noEmpty() {
        if (imageUri==null) {
            myToast("이미지를 선택해주세요.");
            return false;
        } else
            if (TextUtils.isEmpty(informationTxt.getText())) {
            myToast("자기소개를 입력해주세요.");
            return false;
        } else if (TextUtils.isEmpty(yearTxt.getText())||TextUtils.isEmpty(monthTxt.getText())||TextUtils.isEmpty(dayTxt.getText())) {
            myToast("생년월일을 확인해주세요.");
            return false;
        } else if(TextUtils.isEmpty(location1Txt.getText())&&TextUtils.isEmpty(location2Txt.getText())) {
            myToast("주소를 입력해주세요.");
            return false;
        } else if(!sex.isSelected()) {
            myToast("성별을 선택해주세요.");
            return false;
        }
        return true;
    }


    // Method - Album
    public static final int PICK_FROM_ALBUM = 1;
    private void gotoAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    private File tempFile;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) { // 코드가 틀릴경우
            myToast("취소 되었습니다");
            if (tempFile != null) {
                if (tempFile.exists()) {
                    if (tempFile.delete()) {
                        Log.e("Profile : ", tempFile.getAbsolutePath() + " 삭제 성공");
                        tempFile = null;
                    }
                }
            }
            return;
        }

        switch (requestCode) {
            case PICK_FROM_ALBUM: { // 코드 일치
                // Uri
                imageUri = data.getData();
                pathUri = getPath(data.getData());
                Log.d("Profile : ", "PICK_FROM_ALBUM photoUri : " + imageUri);
                photo.setImageURI(imageUri); // 이미지 띄움
                break;
            }
        }
    }

    // uri 절대경로 가져오기
    public String getPath(Uri uri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(this, uri, proj, null, null, null);

        Cursor cursor = cursorLoader.loadInBackground();
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();
        return cursor.getString(index);
    }

    // 회원가입 로직
    private void signup() {
        // Data
        String email = (String) mustInfoBundle.get("email");
        String pw = (String) mustInfoBundle.get("pwd");
        String name = (String) mustInfoBundle.get("name");
        String phone = (String) mustInfoBundle.get("phone");

        String infomation = informationTxt.getText().toString();
        String year = yearTxt.getText().toString();
        String month = monthTxt.getText().toString();
        String day = dayTxt.getText().toString();
        String sex = selectedSex.toString();
        String location1 = location1Txt.getText().toString();
        String location2 = location2Txt.getText().toString();


        // 프로필사진,이름,이메일,비밀번호 중 하나라도 비었으면 return
        // success check
        if(!successCheck()) {
            // if success -> save -> to SignUpSuccess Activity
            myToast("정보를 바르게 입력해 주세요");
            return;
        }
        try {
            // Authentication에 email,pw 생성
            firebaseAuth.createUserWithEmailAndPassword(email, pw)
                    .addOnCompleteListener
                            (Profile.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d("Profile-adduser : ","유저 만들기는 성공했다");
                                    if (task.isSuccessful()) { // 회원가입 성공시

                                        // uid에 task, 선택된 사진을 file에 할당
                                        final String uid = task.getResult().getUser().getUid();
                                        final Uri file = Uri.fromFile(new File(pathUri)); // path

//                                        // 스토리지에 방생성 후 선택한 이미지 넣음
//                                        StorageReference storageReference = mStorage.getReference()
//                                                .child("usersprofileImages").child("uid/"+file.getLastPathSegment());
//                                        storageReference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                                            @Override
//                                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                                                // 이미지 넣기 성공하면,
//                                                final Task<Uri> imageUrl = task.getResult().getStorage().getDownloadUrl();
//                                                while (!imageUrl.isComplete()) ;
//                                                UserModel userModel = new UserModel();
//                                                userModel.createUser(uid,email,pw, name,phone);
//                                                userModel.setAddition(imageUrl,infomation,year,month,day,sex,location1,location2);
//
//                                                // database에 저장
//                                                mDatabase.getReference().child("user").child(uid)
//                                                        .setValue(userModel);
//                                            }
//
//                                        });

                                        myToast("회원가입에 성공하였습니다");
                                        // TODO: user정보 저장?
                                        Intent intent = new Intent(Profile.this, MainActivity.class);

                                    } else {
                                        if (task.getException() != null) { // 회원가입 실패시
                                            myToast("가입에 실패 하였습니다");
                                        }
                                    }
                                }
                            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}