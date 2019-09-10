package com.example.chronometer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {
    private Button startPauseButton;
    private Button resetButton;
    private Chronometer timerChronometer;

    // Look up the Log class for Android.
    // list all the levels of logging and when they're used
    // order them from lowest priority to highest priority
    // verbose Log.v
    // ....

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wirewidgets();
        setListeners();
        Log.d(TAG, "onCreate: ");
    }
    public void changeBase(){
        timerChronometer.stop();
    }
    private void wirewidgets() {
        startPauseButton = findViewById(R.id.startandpause);
        resetButton = findViewById(R.id.reset);
        timerChronometer = findViewById(R.id.chronometer_main_time);
    }
    int countButton = 0;
    private void setListeners() {
        startPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (countButton % 2 == 0) {
                        timerChronometer.start();
                        countButton++;
                    }
                    else{
                        timerChronometer.stop();
                        changeBase();
                    }
            }

        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerChronometer.stop();

                countButton++;
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart: ");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume: ");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause: ");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "onStop: ");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
    //launched --> onCreate, onStart, onResume
    //rotate --> onStop, onDestroy, onCreate, onStart, onResume
    //hit the square button --> onPause, onStop
    //click back on the app from the square button --> onStop, onStart, onResume
    //hit the circle button ---> onPause, onStop
    //relaunch app with navigation bar -->onStop, onStart, onResume
    //hit the back button -->onPause, onStop, onDestroy
}

