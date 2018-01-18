package com.examle.jaime.reversecrono;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ReverseChronometer mChronometer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChronometer = findViewById(R.id.chronometer);
        mChronometer.setOverallDuration(120);
        mChronometer.setText("Valor inicial " + mChronometer.getOverallDuration());
    }


    @Override
    protected void onResume() {
        super.onResume();
        mChronometer.run();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mChronometer.stop();
    }
}
