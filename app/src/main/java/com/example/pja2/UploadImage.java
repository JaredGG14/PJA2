package com.example.pja2;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class UploadImage extends AppCompatActivity {
    ImageView imageView;
    Button btn, btnCamera;
    FirebaseStorage storage;
    Uri imageUri;
    FirebaseAuth auth;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static boolean Add_new_tkt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        auth = FirebaseAuth.getInstance();

        btn = findViewById(R.id.btnUpload);
        imageView = findViewById(R.id.photoUpload);
        btnCamera = findViewById(R.id.btnPhoto);
        storage = FirebaseStorage.getInstance();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/*");
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

    }
    private void uploadImage(){

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.uploading));
        dialog.show();

        if(imageUri != null){
            StorageReference reference = storage.getReference().child("images/" + auth.getCurrentUser().getUid() + "/" + UUID.randomUUID().toString());
            reference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if(task.isSuccessful()){
                        dialog.dismiss();
                        Toast.makeText(UploadImage.this, R.string.upload_success, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UploadImage.this, MainActivity.class);
                        startActivity(intent);
                        Add_new_tkt=true;
                        finish();
                    } else {
                        dialog.dismiss();
                        Toast.makeText(UploadImage.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            dialog.dismiss();
            Toast.makeText(UploadImage.this, R.string.upl_fail, Toast.LENGTH_SHORT).show();
        }
    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            if(result != null){
                imageView.setImageURI(result);
                imageUri = result;
            }
        }
    });

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
            imageView.setImageURI(getImageUri(imageBitmap));
            imageUri = getImageUri((imageBitmap));
        }
    }

    public Uri getImageUri(Bitmap inImage){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(this.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}