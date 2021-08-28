//package com.example.memod;
//
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.FirebaseException;
//import com.google.firebase.FirebaseTooManyRequestsException;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.auth.PhoneAuthCredential;
//import com.google.firebase.auth.PhoneAuthProvider;
//
//import java.util.concurrent.Executor;
//
//public class ConfirmPhoneListener extends PhoneAuthProvider.OnVerificationStateChangedCallbacks{
//
//    private static final String TAG = "ConfirmPhoneListener";
//
//    public boolean checkPhone;
//    public boolean isCheckPhone() { return checkPhone;}
//
//    private FirebaseAuth firebaseAuth;
//
//    public ConfirmPhoneListener(FirebaseAuth firebaseAuth){
//        this.firebaseAuth = firebaseAuth;
//
//    }
//
//
//    @Override // success
//    public void onVerificationCompleted(PhoneAuthCredential credential) {
//        Log.d(TAG, "onVerificationCompleted:" + credential);
//        signInWithPhoneAuthCredential(credential);
//        checkPhone=true;
//    }
//
//    @Override
//    public void onVerificationFailed(FirebaseException e) {
//        // 이 메서드는 요청에 잘못된 전화번호 또는 인증 코드가 지정된 경우와 같이 잘못된 인증 요청에 대한 응답으로 호출됩니다.
//        Log.w(TAG, "onVerificationFailed", e);
//        if (e instanceof FirebaseAuthInvalidCredentialsException) {
//        // Invalid request
//        } else if (e instanceof FirebaseTooManyRequestsException) {
//        // The SMS quota for the project has been exceeded
//        }
//        // Show a message and update the UI
//    }
//
////            @Override
////            public void onCodeSent(@NonNull String verificationId,
////                                   @NonNull PhoneAuthProvider token) {
////                //(선택사항) 이 메서드는 제공된 전화번호로 인증 코드가 SMS를 통해 전송된 후에 호출됩니다.
////                Log.d(TAG, "onCodeSent:" + verificationId);
////
////                // Save verification ID and resending token so we can use them later
////                mVerificationId = verificationId;
////                mCallbacks = token;
////            }
//
////            @Override
////            public void onCodeAutoRetrievalTimeOut(String verificationId) {
////                //(선택사항) 이 메서드는 onVerificationCompleted가 아직 트리거되기 전에 verifyPhoneNumber에
////                // 지정된 제한 시간이 경과된 후에 호출됩니다.
////                // 기기에 SIM 카드가 없으면 SMS 자동 검색이 불가능하므로 이 메서드가 즉시 호출됩니다.
////
////            }
//
//    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
//        firebaseAuth.signInWithCredential(credential)
//                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
//
//                            FirebaseUser user = task.getResult().getUser();
//                            // Update UI
//                        } else {
//                            // Sign in failed, display a message and update the UI
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
//                                // The verification code entered was invalid
//                            }
//                        }
//                    }
//                });
//    }
//
//}
