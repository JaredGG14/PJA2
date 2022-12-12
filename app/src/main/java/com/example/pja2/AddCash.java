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

import com.example.pja2.Modelo.RegistroAhorro;
import com.example.pja2.Modelo.RegistroCash;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddCash extends AppCompatActivity {
    public static boolean Add_Cash;
    private CalendarView calendarView;
    private EditText et_montoCash;
    private TextView fechaCash;
    private Button guardar;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cash);

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
                Intent intent = new Intent(AddCash.this, PH_add_saving.class);
                startActivity(intent);
                Add_Cash = true;
                finish();
            }
        });


    }

         */


        calendarView = findViewById(R.id.calendarView_Card);
        et_montoCash = findViewById(R.id.mountCard);
        fechaCash = findViewById(R.id.savetxt2Card);
        guardar = findViewById(R.id.guardar_savebtnCard);

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("registro_cash");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String fechaSeleccionada = dayOfMonth + "-" + month + "-" + year;
                myRef.child(auth.getCurrentUser().getUid()).child(fechaSeleccionada).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        RegistroAhorro registroAhorro = dataSnapshot.getValue(RegistroAhorro.class);
                        if (registroAhorro!=null){
                            fechaCash.setText(registroAhorro.getFechaDia());
                        }else{
                            fechaCash.setText(fechaSeleccionada);


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

        guardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                guardarRegistroCash(fechaCash.getText().toString(), Integer.parseInt(et_montoCash.getText().toString()+""));
                Intent intent = new Intent(AddCash.this, PH_add_saving.class);
                startActivity(intent);
                Add_Cash = true;
                finish();
            }
        });

    }
    public void guardarRegistroCash(String fechac, int montoc) {
        RegistroCash registroCash = new RegistroCash(fechac, montoc);
        if (auth.getCurrentUser() != null) {
            myRef.child(auth.getCurrentUser().getUid()).child(fechac).setValue(registroCash).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(AddCash.this, "Se ha guardado correctamente",Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddCash.this, "No se ha guardado",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

}