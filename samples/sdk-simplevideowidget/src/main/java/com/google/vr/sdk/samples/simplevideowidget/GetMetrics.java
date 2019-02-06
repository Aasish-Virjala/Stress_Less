package com.google.vr.sdk.samples.simplevideowidget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class GetMetrics extends AppCompatActivity {
    private String[] keys;
    private TextView mStoreList;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private Button mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_metrics);
        db = FirebaseFirestore.getInstance();
        //set mAuth to the instance of Firebase Authentication
        mAuth = FirebaseAuth.getInstance();
        //set the button variables to their correspoding buttons
        mBack = (Button) findViewById(R.id.Back);
        final ScrollView scrollView = (ScrollView) findViewById(R.id.ScrollView);
        final LinearLayout linearLayout = new LinearLayout(this);
        scrollView.addView(linearLayout);

        //mStoreList = (TextView) findViewById(R.id.StoreList);
        db.collection("Stress Levels")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for (final QueryDocumentSnapshot document : task.getResult()) {
                                final Button cb = new Button(getApplicationContext());
                                cb.setText(document.getId());
                                cb.setGravity(Gravity.LEFT);
                                cb.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        StressData.documentID = document.getId();
                                        StressData.map = document.getData();
                                        startActivity(new Intent(GetMetrics.this,StressData.class));
                                    }
                                });
                                linearLayout.addView(cb);
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(GetMetrics.this, "Error in finding List", Toast.LENGTH_SHORT).show();
                    }
                });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GetMetrics.this, MainActivity.class));
            }
        });
    }

}

