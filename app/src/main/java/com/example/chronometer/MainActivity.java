package com.example.chronometer;

import androidx.annotation.NonNull;
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
    public static final String KEY_CHRONOMETERS_STOPTIME = "chronometer base";
    public static final String KEY_CHRONOMETERS_IFRUNNING = "is chronometer running";
    private long stoppedTime;
    private boolean clicked;


    //launched --> onCreate, onStart, onResume
    //rotate --> on Pause, onStop, onDestroy, onCreate, onStart, onResume
    //hit the square button --> onPause, onStop
    //click back on the app from the square button --> onStop, onStart, onResume
    //hit the circle button ---> onPause, onStop
    //relaunch app with navigation bar -->onStop, onStart, onResume
    //hit the back button -->onPause, onStop, onDestroy

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wirewidgets();
        setListeners();
        clicked = false;
        // check to see if saved instance state ins't null,
        // pull out the value of the base that we saved from the bundle
        // set the chronometer's base to that value
        // start the chronometer

        // also store in the bundle
        // see if it was running ir stopped to decide if you should start or not in onCreate

        Log.d(TAG, "onCreate: ");
    }
    private void wirewidgets() {
        startPauseButton = findViewById(R.id.startandpause);
        resetButton = findViewById(R.id.reset);
        timerChronometer = findViewById(R.id.chronometer_main_time);
    }

    // maintain state through orientation change,
    // when running stay running, no skip time
    // time stopped, stay stopped and not skip time
    //no hardcoded strings
    // changing
    private void setListeners() {
        startPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (!clicked) {
                        timerChronometer.setBase(SystemClock.elapsedRealtime() - stoppedTime);
                        startPauseButton.setText(getString(R.string.main_stop));
                        timerChronometer.start();
                        clicked = true;
                    }
                    else{
                        timerChronometer.stop();
                        startPauseButton.setText(getString(R.string.main_start));
                        clicked = false;
                        stoppedTime = (SystemClock.elapsedRealtime() - timerChronometer.getBase());
                    }
            }

        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerChronometer.stop();
                timerChronometer.setBase(SystemClock.elapsedRealtime());
                startPauseButton.setText(getString(R.string.main_start));
                clicked = false;
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_CHRONOMETERS_STOPTIME, stoppedTime);
        outState.putBoolean(KEY_CHRONOMETERS_IFRUNNING, clicked);
    }

}

