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
import android.widget.TextView;
import android.widget.Toast;

import com.example.pja2.Modelo.RegistroCash;
import com.example.pja2.Modelo.RegistroTarjeta;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Add_Income_Outcome extends AppCompatActivity {

    public static boolean Add_IO;
    private CalendarView calendarView;
    private EditText et_montoCard, et_tipoCard;
    private TextView fechaCard;
    private Button guardarCard;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income_outcome);
/*
        Spinner spinneroptions = findViewById(R.id.spinnerSpent);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.typeSaving, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinneroptions.setAdapter(adapter);

        Spinner spinneroptions2 = findViewById(R.id.spinnerIncomeOutcome);
        ArrayAdapter<CharSequence> adapt = ArrayAdapter.createFromResource(this,R.array.typeIncomeOutcome, android.R.layout.simple_spinner_item);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinneroptions2.setAdapter(adapt);

        findViewById(R.id.start_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_Income_Outcome.this, PH_add_saving.class);
                startActivity(intent);
                Add_IO = true;
                finish();
            }
        });
    }

 */
        calendarView = findViewById(R.id.calendarView_Card);
        et_montoCard = findViewById(R.id.mountCard);
        et_tipoCard = findViewById(R.id.typeCard);
        fechaCard = findViewById(R.id.savetxt2Card);
        guardarCard = findViewById(R.id.guardar_savebtnCard);

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("registro_card");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String fechaSeleccionada = dayOfMonth + "-" + month + "-" + year;
                myRef.child(auth.getCurrentUser().getUid()).child(fechaSeleccionada).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        RegistroCash registroCash = dataSnapshot.getValue(RegistroCash.class);
                        if (registroCash!=null){
                            fechaCard.setText(registroCash.getFechaDiaCash());
                        }else{
                            fechaCard.setText(fechaSeleccionada);


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

        guardarCard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                guardarRegistroIn(fechaCard.getText().toString(), Integer.parseInt(et_montoCard.getText().toString()+""), et_tipoCard.getText().toString());
                Intent intent = new Intent(Add_Income_Outcome.this, PH_add_saving.class);
                startActivity(intent);
                Add_IO = true;
                finish();
            }
        });

    }

    public void guardarRegistroIn(String fechain, int montoin, String tipocard) {
        RegistroTarjeta registroTar = new RegistroTarjeta(fechain, montoin, tipocard);
        if (auth.getCurrentUser() != null) {
            myRef.child(auth.getCurrentUser().getUid()).child(fechain).setValue(registroTar).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(Add_Income_Outcome.this, "Se ha guardado correctamente",Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Add_Income_Outcome.this, "No se ha guardado",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

}