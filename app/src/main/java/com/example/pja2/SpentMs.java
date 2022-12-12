package com.example.pja2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pja2.Modelo.RegistroTarjeta;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SpentMs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpentMs extends Fragment {

    FloatingActionButton btn;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SpentMs() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SpentMs.
     */
    // TODO: Rename and change types and number of parameters
    public static SpentMs newInstance(String param1, String param2) {
        SpentMs fragment = new SpentMs();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView recyclerView;
    SpentAdapter adapter;
    DatabaseReference mbase;
    FirebaseAuth auth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spent_ms, container, false);

        btn = (FloatingActionButton) view.findViewById(R.id.fab);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Add_Income_Outcome.class);
                startActivity(intent);
            }
        });

        auth = FirebaseAuth.getInstance();
        mbase = FirebaseDatabase.getInstance().getReference("registro_card").child(auth.getCurrentUser().getUid());
        recyclerView= (RecyclerView) view.findViewById(R.id.showSpentsRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<RegistroTarjeta> opciones = new FirebaseRecyclerOptions.Builder<RegistroTarjeta>().setQuery(mbase, RegistroTarjeta.class).build();
        adapter = new SpentAdapter(opciones);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void onStart()
    {
        super.onStart();
        adapter.startListening();
    }


    @Override
    public void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}