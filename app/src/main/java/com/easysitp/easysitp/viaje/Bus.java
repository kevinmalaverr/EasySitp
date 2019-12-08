package com.easysitp.easysitp.viaje;

public class Bus {
    public double latitud;
    public double longitud;
    public double velocidad;
    public String ultimaParada;

    public Bus() {
    }

    public Bus(double lat, double lon, double vel) {
        this.latitud = lat;
        this.longitud = lon;
        this.velocidad = vel;
    }
}
