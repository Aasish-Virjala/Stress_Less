package com.google.vr.sdk.samples.simplevideowidget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Slider extends AppCompatActivity {
    private TextView Feeling,ValueOfSeekBar, Slide;
    private SeekBar Slider;
    private Button Done;
    public static int FeelingSlider;
    public static String databaseID, state;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        Feeling = (TextView) findViewById(R.id.Feeling);
        Slider = (SeekBar)findViewById(R.id.Slider);
        Done = (Button) findViewById(R.id.Done);
        db = FirebaseFirestore.getInstance();
        ValueOfSeekBar = (TextView)findViewById(R.id.ValueOfSeekBar);
        ValueOfSeekBar.setText("0");
        Slide = (TextView)findViewById(R.id.Slide);
        Feeling.setText("How "+MainActivity.mood+" are you feeling? (0 means none, 10 means extremely)");
        Slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ValueOfSeekBar.setText(String.valueOf(Slider.getProgress()));
                Slide.setText("");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeelingSlider=Slider.getProgress();
                Map<String,Integer> newMap= new HashMap<>();
                if(TextUtils.isEmpty(state)) {
                    newMap.put("Before", FeelingSlider);
                }
                else {
                    newMap.put(state,FeelingSlider);
                }
                databaseID = generateRandom();
                Toast.makeText(Slider.this, databaseID, Toast.LENGTH_SHORT).show();
                db.collection("Stress Levels").document(databaseID).set(newMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    }
                });
                startActivity(new Intent(Slider.this,OptionsForVr.class));
            }
        });
    }
    private static String generateRandom() {
        String aToZ= "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random rand=new Random();
        StringBuilder res=new StringBuilder();
        for (int i = 0; i < 17; i++) {
            int randIndex=rand.nextInt(aToZ.length());
            res.append(aToZ.charAt(randIndex));
        }
        return res.toString();
    }
}
