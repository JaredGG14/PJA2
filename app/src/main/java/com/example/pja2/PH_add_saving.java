package com.example.pja2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class PH_add_saving extends AppCompatActivity {
    private ProgressBar progressBar;
    public static boolean save_saving_ph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ph_add_saving);

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(PH_add_saving.this, MainActivity.class);
                startActivity(intent);
                save_saving_ph = true;
                finish();
            }
        };
        Timer tiempo = new Timer();
        tiempo.schedule(tarea, 2000);

        progressBar = findViewById(R.id.progressBar);
    }
}