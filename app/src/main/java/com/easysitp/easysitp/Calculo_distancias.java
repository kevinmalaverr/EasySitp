package com.easysitp.easysitp;

public class Calculo_distancias {

    public int FormulaHaversine(double long1, double lat1, double long2, double lat2){

        double radioTierra = 6371; //km
        long1 = Math.toRadians(long1);
        lat1 = Math.toRadians(lat1);
        long2 = Math.toRadians(long2);
        lat2 = Math.toRadians(lat2);

        double distancia_long = (long2-long1);
        double distancia_lat = (lat2-lat1);

        double sinlat = Math.sin(distancia_lat/ 2);
        double sinlong = Math.sin(distancia_long / 2);

        double a = (sinlat * sinlat) + Math.cos(lat1)*Math.cos(lat2)*(sinlong*sinlong);
        double c = 2 * Math.asin (Math.min(1.0, Math.sqrt(a)));

        double distanciaEnMetros = radioTierra * c * 1000;

        return(int) distanciaEnMetros;

    }
}
