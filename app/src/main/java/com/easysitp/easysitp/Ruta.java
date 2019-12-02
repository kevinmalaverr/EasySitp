package com.easysitp.easysitp;

public class Ruta {
    public String numeroRuta;
    public String NombreRuta;
    public double[][] coordenadas;

    public Ruta(String numero, String nombre) {
        this.NombreRuta = nombre;
        this.numeroRuta = numero;
    }

    public void setCoordenadas(double[][] coordenadas) {
        this.coordenadas = coordenadas;
    }
}
