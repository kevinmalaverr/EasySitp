package com.easysitp.easysitp.viaje;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;

public class Parada implements Serializable {

    private ArrayList<Ruta> rutas = new ArrayList<Ruta>();
    private String nombre;
    private LatLng coordenadas;

    public Parada(String nombre) {
        this.nombre = nombre;
    }

    public void add(Ruta ruta) {
        this.rutas.add(ruta);
    }

    public void setCoordenadas(double lat, double lon) {
        this.coordenadas = new LatLng(lat, lon);
    }

    public LatLng getCoordenadas() {
        return this.coordenadas;
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
