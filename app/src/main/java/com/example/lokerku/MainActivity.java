package com.example.lokerku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // Declare Items
    private final Handler handler = new Handler();
    private TextView lockerNumber;
    private TextView currentDate;
    private TextView statusText;
    private Button button;
    private Chronometer stopwatch;

    // Declare SharedPreferences
    private SharedPreferences lastClick;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set Theme to Light Mode
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Last Click With SharedPreferences
        lastClick = getSharedPreferences("status", Context.MODE_PRIVATE);
        editor = lastClick.edit();

        final boolean prevStatus = lastClick.getBoolean("buttonStatus", false);
        final long prevTime = lastClick.getLong("time", 0);

        // FindViewById
        lockerNumber = findViewById(R.id.lockerNumber);
        currentDate = findViewById(R.id.CurrentDate);
        statusText = findViewById(R.id.statusText);
        button = findViewById(R.id.button);
        stopwatch = findViewById(R.id.stopwatch);

        // Randomize Locker Number
        Random rand = new Random();
        int randomNum = rand.nextInt((10 - 1) + 1) + 1;
        lockerNumber.setText(String.valueOf(randomNum));

        // Set Current Date
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, DD-MM-YYYY");
        String date = dateFormat.format(currentTime);
        currentDate.setText(date);

        // Set Last Click
        if (prevStatus == true) {
            statusText.setText("CLOSED");
            statusText.setTextColor(Color.parseColor("#FFE91E63"));
            button.setText("UNLOCK");

            // Keep Updating The Time
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    stopwatch.setBase(prevTime);
                    stopwatch.start();
                    handler.postDelayed(this, 1000);
                }
            }, 1000);
        }
        else {
            statusText.setText("OPEN");
            statusText.setTextColor(Color.parseColor("#43A047"));
            button.setText("LOCK");
        }

        // Lock/Unlock Button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Status Changes
                String statusChanges = statusText.getText().toString();

                if (statusChanges.equals("OPEN")) {
                    statusText.setText("CLOSED");
                    statusText.setTextColor(Color.parseColor("#FFE91E63"));
                    button.setText("UNLOCK");

                    stopwatch.setBase(SystemClock.elapsedRealtime());
                    stopwatch.start();

                    editor.putBoolean("buttonStatus", true);
                    editor.putLong("time", SystemClock.elapsedRealtime());
                    editor.apply();
                }
                else {
                    statusText.setText("OPEN");
                    statusText.setTextColor(Color.parseColor("#43A047"));
                    button.setText("LOCK");

                    stopwatch.stop();
                    handler.removeCallbacksAndMessages(null);

                    editor.putBoolean("buttonStatus", false);
                    editor.apply();
                }
            }
        });
    }
}
