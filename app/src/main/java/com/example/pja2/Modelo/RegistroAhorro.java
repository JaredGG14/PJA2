package com.example.pja2.Modelo;

public class RegistroAhorro {
    private String fechaDia;
    private int cantidadAhorrada;

    public RegistroAhorro(String fechaDia, int cantidadAhorrada) {
        this.fechaDia = fechaDia;
        this.cantidadAhorrada = cantidadAhorrada;
    }

    public String getFechaDia() {
        return fechaDia;
    }

    public void setFechaDia(String fechaDia) {
        this.fechaDia = fechaDia;
    }

    public int getCantidadAhorrada() {
        return cantidadAhorrada;
    }

    public void setCantidadAhorrada(int cantidadAhorrada) {
        this.cantidadAhorrada = cantidadAhorrada;
    }

    public RegistroAhorro() {
    }
}



