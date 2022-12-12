package com.example.pja2;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pja2.Modelo.RegistroCash;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class CashAdapter extends FirebaseRecyclerAdapter<RegistroCash, CashAdapter.cashViewholder>{
    public CashAdapter(
            @NonNull FirebaseRecyclerOptions<RegistroCash> opciones){
        super(opciones);
    }

    @Override
    protected void onBindViewHolder(@NonNull cashViewholder holder, int position, @NonNull RegistroCash model) {
        holder.fechaDiaCash.setText(model.getFechaDiaCash());
        holder.cantidadAhorradaCash.setText((String.valueOf(model.getCantidadAhorradaCash())));

    }

    @NonNull
    @Override
    public CashAdapter.cashViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cash_rv, parent, false);
        return new cashViewholder(view);
    }


    class cashViewholder extends RecyclerView.ViewHolder {
        TextView fechaDiaCash;
        TextView cantidadAhorradaCash;
        public cashViewholder(@NonNull View itemView){
            super(itemView);
            fechaDiaCash = itemView.findViewById(R.id.showDateCash);
            cantidadAhorradaCash = itemView.findViewById(R.id.showCash);
        }
    }



}

