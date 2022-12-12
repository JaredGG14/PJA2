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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddSaving extends AppCompatActivity {

    public static boolean save_savings;
    private CalendarView calendarView;
    private EditText et_montoAhorrado;
    private TextView fechaAhorro;
    private Button guardar;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_saving);

        calendarView = findViewById(R.id.calendarView_Card);
        et_montoAhorrado = findViewById(R.id.mountCard);
        fechaAhorro = findViewById(R.id.savetxt2Card);
        guardar = findViewById(R.id.guardar_savebtnCard);

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("registro_ahorro");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String fechaSeleccionada = dayOfMonth + "-" + month + "-" + year;
                myRef.child(auth.getCurrentUser().getUid()).child(fechaSeleccionada).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        RegistroAhorro registroAhorro = dataSnapshot.getValue(RegistroAhorro.class);
                        if (registroAhorro!=null){
                            fechaAhorro.setText(registroAhorro.getFechaDia());
                        }else{
                            fechaAhorro.setText(fechaSeleccionada);


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
                guardarRegistroAhorro(fechaAhorro.getText().toString(), Integer.parseInt(et_montoAhorrado.getText().toString()+""));
                Intent intent = new Intent(AddSaving.this, PH_add_saving.class);
                startActivity(intent);
                save_savings = true;
                finish();
            }
        });
        //findViewById(R.id.guardar_savebtn).setOnClickListener(new View.OnClickListener() {
        //@Override
        //public void onClick(View v) {
        //  Intent intent = new Intent(AddSaving.this, ph_save_savings_xd.class);
        //startActivity(intent);
        //}
        //});
    }

    public void guardarRegistroAhorro(String fecha, int monto) {
        RegistroAhorro registroAhorro = new RegistroAhorro(fecha, monto);
        if (auth.getCurrentUser() != null) {
            myRef.child(auth.getCurrentUser().getUid()).child(fecha).setValue(registroAhorro).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(AddSaving.this, "Se ha guardado correctamente",Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddSaving.this, "No se ha guardado",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
    //public void addSaving(View view) {
    //  Intent intent = new Intent(AddSaving.this, MainActivity.class);
    //startActivity(intent);
    //}
}