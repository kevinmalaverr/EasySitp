package com.easysitp.easysitp;

public class Ruta {
    private String numeroRuta;
    private String nombreRuta;
    public double[][] coordenadas;

    public Ruta(String numero, String nombre) {
        this.nombreRuta = nombre;
        this.numeroRuta = numero;
    }

    public void setCoordenadas(double[][] coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getNumeroRuta() {
        return this.numeroRuta;
    }

    public String getNombreRuta() {
        return this.nombreRuta;
    }
}
