package com.easysitp.easysitp;

import java.util.ArrayList;

public class Parada {

    private ArrayList<Ruta> rutas = new ArrayList<Ruta>();
    private String nombre;

    public Parada(String nombre) {
        this.nombre = nombre;
    }

    public void add(Ruta ruta) {
        this.rutas.add(ruta);
    }

    public ArrayList<Ruta> getRutas() {
        return this.rutas;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int numeroRutas() {
        return this.rutas.size();
    }
}
