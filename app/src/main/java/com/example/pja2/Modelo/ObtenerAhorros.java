package com.example.pja2.Modelo;

public class ObtenerAhorros {
    String cantidadAhorrada, fechaDia;
    public ObtenerAhorros(){}

    public ObtenerAhorros(String cantidadAhorrada, String fechaDia) {
        this.cantidadAhorrada = cantidadAhorrada;
        this.fechaDia = fechaDia;
    }

    public  String getCantidadAhorrada() {
        return cantidadAhorrada;
    }

    public void setCantidadAhorrada(String cantidadAhorrada) {
        this.cantidadAhorrada = cantidadAhorrada;
    }

    public  String getFechaDia() {
        return fechaDia;
    }

    public void setFechaDia(String fechaDia) {
        this.fechaDia = fechaDia;
    }
}
