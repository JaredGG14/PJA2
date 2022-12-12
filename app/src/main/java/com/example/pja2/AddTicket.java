package com.example.pja2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import com.example.pja2.adaptador.galleryPhotoAdapter;

public class AddTicket extends AppCompatActivity {

    ImageView imageView;
    galleryPhotoAdapter galleryPhotoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);

        imageView = (ImageView) findViewById(R.id.iv_photo);
        ActionBar actionBar = getSupportActionBar();

        Intent intent = getIntent();
        int i = intent.getExtras().getInt("misImagenes");
        galleryPhotoAdapter = new galleryPhotoAdapter(this);
        imageView.setImageResource(galleryPhotoAdapter.imgArray[i]);
    }
}