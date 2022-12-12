package com.example.pja2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PreviewTicket extends AppCompatActivity {

    public static boolean tkt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_ticket);

        findViewById(R.id.fab2card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreviewTicket.this, PH_add_saving.class);
                startActivity(intent);
                tkt = true;
                finish();
            }
        });
    }
}