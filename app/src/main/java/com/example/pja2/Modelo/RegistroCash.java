package com.example.pja2.Modelo;

public class RegistroCash {
    private String fechaDiaCash;
    private int cantidadAhorradaCash;

    public RegistroCash(String fechaDiaCash, int cantidadAhorradaCash) {
        this.fechaDiaCash = fechaDiaCash;
        this.cantidadAhorradaCash = cantidadAhorradaCash;
    }

    public String getFechaDiaCash() {
        return fechaDiaCash;
    }

    public void setFechaDiaCash(String fechaDiaCash) {
        this.fechaDiaCash = fechaDiaCash;
    }

    public int getCantidadAhorradaCash() {
        return cantidadAhorradaCash;
    }

    public void setCantidadAhorradaCash(int cantidadAhorradaCash) {
        this.cantidadAhorradaCash = cantidadAhorradaCash;
    }

    public RegistroCash() {
    }
}
