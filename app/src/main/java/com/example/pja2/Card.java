package com.example.pja2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Card extends AppCompatActivity {

    public static boolean spentMs_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        findViewById(R.id.addInOutBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Card.this, Add_Income_Outcome.class);
                startActivity(intent);
                spentMs_card = true;
                finish();
            }
        });

        findViewById(R.id.seeHistoryBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Card.this, MainActivity.class);
                startActivity(intent);
                spentMs_card = true;
                finish();
            }
        });
    }
}