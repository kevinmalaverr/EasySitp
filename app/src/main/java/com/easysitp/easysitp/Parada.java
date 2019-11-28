package com.easysitp.easysitp;

import java.util.ArrayList;

public class Parada {

    private ArrayList<String> lista;
    private String nombre;

    public Parada(String nombre) {
        this.nombre = nombre;
    }

    public void add(String string) {
        this.lista.add(string);
    }

    public ArrayList<String> getLista() {
        return this.lista;
    }

    public int numeroRutas() {
        return this.lista.size();
    }
}
