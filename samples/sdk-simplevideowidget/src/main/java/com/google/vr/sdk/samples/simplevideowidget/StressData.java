package com.google.vr.sdk.samples.simplevideowidget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class StressData extends AppCompatActivity {
    private FirebaseFirestore db;
    public static String documentID;
    public static Map map;
    private Button Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stress_data);
        db = FirebaseFirestore.getInstance();
        Back = (Button)findViewById(R.id.Back);
        final ScrollView scrollView = (ScrollView) findViewById(R.id.ScrollView);
        final LinearLayout linearLayout = new LinearLayout(this);
        scrollView.addView(linearLayout);
        final TextView firstMood = new TextView(this);
        final TextView laterMood = new TextView(this);
        firstMood.setTextSize(20);
        laterMood.setTextSize(20);
        firstMood.setTextColor(getResources().getColor(R.color.Black));
        laterMood.setTextColor(getResources().getColor(R.color.Black));
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StressData.this,GetMetrics.class));
            }
        });
        db.collection("Stress Levels").document(documentID)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(final DocumentSnapshot documentSnapshot) {
                        //Toast.makeText(AccountActivity.this, "Got here too", Toast.LENGTH_SHORT).show();
                        if (documentSnapshot.exists()) {
                            int i = 1;
                            for (String key : documentSnapshot.getData().keySet()) {
                                if (key.equals("Before")) {
                                    firstMood.setText(key+" : "+ documentSnapshot.get("Before"));
                                    Toast.makeText(StressData.this, key+" : "+ documentSnapshot.get("Before"), Toast.LENGTH_SHORT).show();
                                    linearLayout.addView(firstMood);
                                }
                                else {
                                    laterMood.setText(key +documentSnapshot.get(key));
                                    linearLayout.addView(laterMood);
                                }
                            }

                        }
                    }});



    }
}
