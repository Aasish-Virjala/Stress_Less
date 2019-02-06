package com.google.vr.sdk.samples.simplevideowidget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.QueryDocumentSnapshot;


public class AfterVR extends AppCompatActivity {
    private FirebaseAuth mAuth;
    static EditText mEmailField;
    static EditText mPasswordField;
    private Button mLoginButton;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_vr);
        mAuth = FirebaseAuth.getInstance();
        mEmailField = (EditText) findViewById(R.id.emailField);
        mAuthListener = new FirebaseAuth.AuthStateListener() {


            @Override

            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(AfterVR.this, GetMetrics.class));

                }
            }


        };
        mPasswordField = (EditText) findViewById(R.id.passwordField);
        mLoginButton = (Button) findViewById(R.id.loginBtn);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEmailField.getText().toString()) || TextUtils.isEmpty(mPasswordField.getText().toString())) {
                    Toast.makeText(AfterVR.this, "One or more fields are empty", Toast.LENGTH_SHORT).show();
                } else {

//                    Map<String, Object> user = new HashMap<>();
//                    user.put(mEmailField.getText().toString(), 0);
                    //db = FirebaseFirestore.getInstance();
                    startSignin();

                }
            }
        });
    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        mAuth.addAuthStateListener(mAuthListener);
//    }
    private void startSignin() {

        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

            Toast.makeText(AfterVR.this, "One or more fields are empty.", Toast.LENGTH_LONG).show();
        }

        else {

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {

                        Toast.makeText(AfterVR.this, "Sign In Failed", Toast.LENGTH_LONG).show();
                    }
                    else {
                        startActivity(new Intent(AfterVR.this,GetMetrics.class));
                    }

                }
            });

        }
    }
}
