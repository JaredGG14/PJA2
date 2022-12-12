package com.example.pja2.Modelo;

public class RegistroTarjeta {
    private String fechaDiaCard;
    private int cantidadAhorradaCard;
    private String tipoCard;

    public RegistroTarjeta(String fechaDiaCard, int cantidadAhorradaCard, String tipoCard) {
        this.fechaDiaCard = fechaDiaCard;
        this.cantidadAhorradaCard = cantidadAhorradaCard;
        this.tipoCard = tipoCard;
    }

    public RegistroTarjeta() {
    }

    public String getFechaDiaCard() {
        return fechaDiaCard;
    }

    public void setFechaDiaCard(String fechaDiaCard) {
        this.fechaDiaCard = fechaDiaCard;
    }

    public int getCantidadAhorradaCard() {
        return cantidadAhorradaCard;
    }

    public void setCantidadAhorradaCard(int cantidadAhorradaCard) {
        this.cantidadAhorradaCard = cantidadAhorradaCard;
    }

    public String getTipoCard() {
        return tipoCard;
    }

    public void setTipoCard(String tipoCard) {
        this.tipoCard = tipoCard;
    }
}
