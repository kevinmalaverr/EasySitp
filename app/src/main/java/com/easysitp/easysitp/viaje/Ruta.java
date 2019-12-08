package com.easysitp.easysitp.viaje;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

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

    public LatLng[] getCoordenas() {
        ArrayList<LatLng> lis = new ArrayList<>();

        for (int i = 0; i < coordenadas.length; i++) {
            double[] lalo = coordenadas[i];
            LatLng latLng = new LatLng(lalo[0], lalo[1]);
            lis.add(latLng);
        }
        LatLng[] array = lis.toArray(new LatLng[lis.size()]);

        return array;
    }

    public String getNumeroRuta() {
        return this.numeroRuta;
    }

    public String getNombreRuta() {
        return this.nombreRuta;
    }
}
