package com.example.pja2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText correo;
    private EditText password;
    private EditText passwordConfirmacion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.correo);
        password = findViewById(R.id.pass);
        passwordConfirmacion = findViewById(R.id.passver);

    }
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
    public void registrarUsuario(View view){

        if (password.getText().toString().equals(passwordConfirmacion.getText().toString())){

            mAuth.createUserWithEmailAndPassword(correo.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Usuario creado",Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                //updateUI(user);
                                Intent i = new Intent(getApplicationContext(), LoginActiivty.class);
                                startActivity(i);
                            }else{
                                Toast.makeText(getApplicationContext(), "Authenticaton failed",Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });
        }else{
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }



    }
}