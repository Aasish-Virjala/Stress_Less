package com.google.vr.sdk.samples.simplevideowidget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class OptionsForVr extends AppCompatActivity {

    private ImageButton mUnderwater, mBeach;
    public static String choice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_for_vr);
        mUnderwater = (ImageButton) findViewById(R.id.underwater);
        mBeach = (ImageButton) findViewById(R.id.beach);
        mUnderwater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice ="underwater";
                startActivity(new Intent(OptionsForVr.this, SimpleVrVideoActivity.class));
            }
        });
        mBeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice = "beach";
                startActivity(new Intent(OptionsForVr.this,SimpleVrVideoActivity.class));
            }
        });

    }
}
