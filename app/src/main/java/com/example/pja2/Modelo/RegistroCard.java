package com.example.pja2.Modelo;

public class RegistroCard {
    private String nameCard, spinnerCard, cuttCard,payCard;

    public RegistroCard(String nameCard, String spinnerCard, String cuttCard, String payCard) {
        this.nameCard = nameCard;
        this.spinnerCard = spinnerCard;
        this.cuttCard = cuttCard;
        this.payCard = payCard;
    }

    public String getNameCard() {
        return nameCard;
    }

    public void setNameCard(String nameCard) {
        this.nameCard = nameCard;
    }

    public String getSpinnerCard() {
        return spinnerCard;
    }

    public void setSpinnerCard(String spinnerCard) {
        this.spinnerCard = spinnerCard;
    }

    public String getCuttCard() {
        return cuttCard;
    }

    public void setCuttCard(String cuttCard) {
        this.cuttCard = cuttCard;
    }

    public String getPayCard() {
        return payCard;
    }

    public void setPayCard(String payCard) {
        this.payCard = payCard;
    }

    public RegistroCard() {
    }
}
