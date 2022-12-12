package com.example.pja2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pja2.Modelo.RegistroCard;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import io.reactivex.rxjava3.annotations.NonNull;

public class CardAdapter extends FirebaseRecyclerAdapter<RegistroCard, CardAdapter.cardViewHolder> {
    public CardAdapter(
            @NonNull FirebaseRecyclerOptions<RegistroCard>options){
        super(options);
    }


    @Override
    protected void onBindViewHolder(@androidx.annotation.NonNull CardAdapter.cardViewHolder holder, int position, @androidx.annotation.NonNull RegistroCard model) {
        holder.nameCard.setText(model.getNameCard());
        holder.spinnerCard.setText((String.valueOf(model.getSpinnerCard())));
        holder.cuttCard.setText(model.getCuttCard());
        holder.payCard.setText(model.getPayCard());
    }
    @Override
    public CardAdapter.cardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_rv, parent, false);
        return new CardAdapter.cardViewHolder(view);
    }
    class cardViewHolder extends RecyclerView.ViewHolder {
        TextView nameCard;
        TextView spinnerCard;
        TextView cuttCard;
        TextView payCard;
        public cardViewHolder(@androidx.annotation.NonNull View itemView){
            super(itemView);
            nameCard = itemView.findViewById(R.id.showCardName);
            spinnerCard = itemView.findViewById(R.id.showCardType);
            cuttCard = itemView.findViewById(R.id.showCutOffDateC);
            payCard = itemView.findViewById(R.id.showCutOffDateC2);
        }
    }
}
