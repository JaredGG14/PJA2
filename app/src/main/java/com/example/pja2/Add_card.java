package com.example.pja2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pja2.Modelo.RegistroCard;
import com.example.pja2.Modelo.RegistroCash;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_card extends AppCompatActivity {
    public static boolean save_card;
    private EditText et_NameCard;
    private Spinner spinnerOptions, cutoffday, paymentday;
    private FloatingActionButton fab2card;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        et_NameCard = findViewById(R.id.etNameCard);

        spinnerOptions=findViewById(R.id.spinnerCard);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.typeSaving, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerOptions.setAdapter(adapter);


        cutoffday=findViewById(R.id.spinner2Card);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this, R.array.seleccionar_dia, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        cutoffday.setAdapter(adapter1);

        paymentday=findViewById(R.id.spinner3Card);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this, R.array.seleccionar_dia, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        paymentday.setAdapter(adapter1);

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("registro_cardTarjeta");

        findViewById(R.id.fab2card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarRegistroCard(et_NameCard.getText().toString(), spinnerOptions.getSelectedItem().toString(), cutoffday.getSelectedItem().toString(), paymentday.getSelectedItem().toString());
                Intent intent = new Intent(Add_card.this, PH_add_saving.class);
                startActivity(intent);
                save_card = true;
                finish();
            }
        });
    }
    public void guardarRegistroCard(String namec, String spinnerc, String cuttc, String payc) {
        RegistroCard registroCard = new RegistroCard(namec, spinnerc, cuttc, payc);
        if (auth.getCurrentUser() != null) {
            myRef.child(auth.getCurrentUser().getUid()).child(namec).setValue(registroCard).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(Add_card.this, "Se ha guardado correctamente",Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Add_card.this, "No se ha guardado",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

}