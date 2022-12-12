package com.example.pja2;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pja2.Modelo.RegistroAhorro;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.w3c.dom.Text;


public class SavingAdapter extends FirebaseRecyclerAdapter<RegistroAhorro, SavingAdapter.savingViewholder>{
    public SavingAdapter(
            @NonNull FirebaseRecyclerOptions<RegistroAhorro> opciones){
        super(opciones);
    }

    @Override
    protected void onBindViewHolder(@NonNull savingViewholder holder, int position, @NonNull RegistroAhorro model) {
        holder.fechaDia.setText(model.getFechaDia());
        holder.cantidadAhorrada.setText((String.valueOf(model.getCantidadAhorrada())));
    }

    @NonNull
    @Override
    public SavingAdapter.savingViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saving_rv, parent, false);
        return new savingViewholder(view);
    }

    class savingViewholder extends RecyclerView.ViewHolder {
        TextView fechaDia;
        TextView cantidadAhorrada;
        public savingViewholder(@NonNull View itemView){
            super(itemView);
            fechaDia = itemView.findViewById(R.id.showDate);
            cantidadAhorrada = itemView.findViewById(R.id.showSaving);
        }
    }

}
