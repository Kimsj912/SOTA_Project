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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class Profile extends AppCompatActivity {

    // Variable
    private Bundle mustInfoBundle;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference databaseRef;
    private FirebaseStorage mStorage;
    private Uri imageUri;
    private String pathUri;
    private UserModel userModel;


    // Attribute
    private ImageView back;
    private ImageView photo;
    private EditText informationTxt, yearTxt, monthTxt, dayTxt, location1Txt, location2Txt;
    private RadioGroup sex;
    private RadioButton selectedSex;
    private Button done;
    private String email, pw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // super & set View
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // firebase setting
        firebaseAuth =  FirebaseAuth.getInstance();
        mStorage = FirebaseStorage.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        databaseRef = mDatabase.getReference("user");

    // Set DB values (before view values)
        // get Data from before Activty
        mustInfoBundle = getIntent().getExtras();

        // create usermodel
        userModel = new UserModel();

        // essential value setting
        email = (String) mustInfoBundle.get("email");
        pw = (String) mustInfoBundle.get("pwd");
        String name = (String) mustInfoBundle.get("name");
        String phone = (String) mustInfoBundle.get("phone");
        userModel.createUser(email,pw, name,phone);

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
    private void checkAndSet() {
        // 부가적 정보를 담는 곳이기 때문에 null이 될수도 있다. --> 하지만 나중에 프로필 띄우는 걸 생각하면... 다 넣는게 좋을듯 하다.
        if (!TextUtils.isEmpty(informationTxt.getText())) {
            userModel.setInfomation(informationTxt.getText().toString());
        } else{
            userModel.setInfomation("");
        }
        if (!TextUtils.isEmpty(yearTxt.getText())&&!TextUtils.isEmpty(monthTxt.getText())&&!TextUtils.isEmpty(dayTxt.getText())){
            userModel.setYear(yearTxt.getText().toString());
            userModel.setMonth(monthTxt.getText().toString());
            userModel.setDay(dayTxt.getText().toString());
        } else if(TextUtils.isEmpty(yearTxt.getText())||TextUtils.isEmpty(monthTxt.getText())||TextUtils.isEmpty(dayTxt.getText())){
            myToast("생년월일을 모두 입력하지 않으면 입력이 거부됩니다.");
            userModel.setYear("");
            userModel.setMonth("");
            userModel.setDay("");
        }
        if(!TextUtils.isEmpty(location1Txt.getText())&&!TextUtils.isEmpty(location2Txt.getText())) {
            userModel.setLocation1(location1Txt.getText().toString());
            userModel.setLocation2(location2Txt.getText().toString());
        } else if(TextUtils.isEmpty(location1Txt.getText())&&!TextUtils.isEmpty(location2Txt.getText())
                ||!TextUtils.isEmpty(location1Txt.getText())&&TextUtils.isEmpty(location2Txt.getText())){
            myToast("주소를 모두 입력하지 않으면 입력이 거부됩니다.");
            userModel.setLocation1("");
            userModel.setLocation2("");
        }
        if(sex.isSelected()) {
            userModel.setSex(selectedSex.getText().toString());
        }else{
            userModel.setSex("");
        }
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
        if (resultCode != RESULT_OK) { // 코드가 틀릴경우 = 업로드 취소하는 경우.
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
        checkAndSet();
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, pw)
                    .addOnCompleteListener
                            (Profile.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) { // 회원가입 성공시
                                        // uid에 task, 선택된 사진을 file에 할당
                                        final String uid = task.getResult().getUser().getUid();
                                        final Uri file = Uri.fromFile(new File(pathUri)); // path
                                        // 스토리지에 방생성 후 선택한 이미지 넣음
                                        uploadProfileToStorage(file);

                                        // database에 저장
                                        databaseRef.child(uid).setValue(userModel);
                                        Intent intent = new Intent(Profile.this, MainActivity.class);
                                        startActivity(intent);

                                    } else{
                                        myToast("가입에 실패하였습니다. 다시 시도해주세요.");
                                        Log.i("실패이유 : ",""+task.getException());
                                    }

                                }
                            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void uploadProfileToStorage(Uri file) {
        StorageReference storageReference = mStorage.getReference()
                .child("user/photo").child("uid/"+file.getLastPathSegment());
        StorageTask<UploadTask.TaskSnapshot> uploadTask = storageReference.putFile(imageUri);
    uploadTask.addOnCompleteListener(
        new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                final Task<Uri> imageUrl = task.getResult().getStorage().getDownloadUrl();
                while (!imageUrl.isComplete()) ;
                myToast("사진업로드 성공");
                }
        });
    }
}