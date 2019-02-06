package com.google.vr.sdk.samples.simplevideowidget;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;


public class MainActivity extends AppCompatActivity {
    private TextView age;
    private RadioButton zeroEighteen, eighteenThirty,thiryPlus;
    public static String ageString;
    public static String mood;
    private FirebaseFirestore db;
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        Toast.makeText(this, getClass().toString(), Toast.LENGTH_SHORT).show();
       // startActivity(new Intent(MainActivity.this,OptionsForVr.class));
        age = (TextView) findViewById(R.id.Age);
        zeroEighteen = (RadioButton) findViewById(R.id.zeroEighteen);
        eighteenThirty = (RadioButton) findViewById(R.id.eighteenThirty);
        thiryPlus = (RadioButton) findViewById(R.id.thirtyPlus);
        zeroEighteen.setChecked(false);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Slider.class));
            }
        });
    }
    public void onRadioButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.zeroEighteen:
                mood="angry";
                Toast.makeText(this, mood, Toast.LENGTH_SHORT).show();
                eighteenThirty.setChecked(false);
                thiryPlus.setChecked(false);
                zeroEighteen.setChecked(true);
                break;

            case R.id.eighteenThirty:
                mood="sad";
                Toast.makeText(this, mood, Toast.LENGTH_SHORT).show();
                eighteenThirty.setChecked(true);
                thiryPlus.setChecked(false);
                zeroEighteen.setChecked(false);
                break;
            case R.id.thirtyPlus:
                mood="happy";
                Toast.makeText(this, mood, Toast.LENGTH_SHORT).show();
                eighteenThirty.setChecked(false);
                thiryPlus.setChecked(true);
                zeroEighteen.setChecked(false);
                break;
        }
    }
    public int dpTopx(int dp) {
        Resources r = this.getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return px;
    }
}
