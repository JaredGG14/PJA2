package com.example.pja2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pja2.Modelo.RegistroCash;
import com.example.pja2.Modelo.RegistroGasto;
import com.example.pja2.Modelo.RegistroTarjeta;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class SpentAdapter extends FirebaseRecyclerAdapter<RegistroTarjeta, SpentAdapter.spentViewholder> {
    public SpentAdapter(
            @NonNull FirebaseRecyclerOptions<RegistroTarjeta> opciones){
        super(opciones);
    }

    @Override
    protected void onBindViewHolder(@NonNull spentViewholder holder, int position, @NonNull RegistroTarjeta model) {
        holder.fechaDiaCard.setText(model.getFechaDiaCard());
        holder.cantidadAhorradaCard.setText((String.valueOf(model.getCantidadAhorradaCard())));
        holder.tipoCard.setText(model.getTipoCard());


    }

    @NonNull
    @Override
    public SpentAdapter.spentViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spents_rv, parent, false);
        return new spentViewholder(view);
    }


    class spentViewholder extends RecyclerView.ViewHolder {
        TextView fechaDiaCard;
        TextView cantidadAhorradaCard;
        TextView tipoCard;
        public spentViewholder(@NonNull View itemView){
            super(itemView);
            fechaDiaCard = itemView.findViewById(R.id.showDateCard);
            cantidadAhorradaCard = itemView.findViewById(R.id.showCard);
            tipoCard = itemView.findViewById(R.id.showType);

        }
    }



}


