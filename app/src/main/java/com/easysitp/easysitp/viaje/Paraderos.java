package com.easysitp.easysitp.viaje;

import java.util.ArrayList;

public class Paraderos {

    public static Parada paradaHumanas;
    public static Parada paradaIEI;
    public static Parada paradaVeterinaria;
    public static Parada paradaOdontologia;
    public static Parada paradaAgrarias;
    public static Parada paradaCyt;
    public static Parada paradaEconomicas;
    public static Parada paradaSindu;


    public static Ruta ruta_c24;
    public static Ruta ruta_802;
    public static Ruta ruta_87;

    public static ArrayList<Ruta> listaRutas;
    public static ArrayList<Parada> listaParadas;

    public static void ini() {
        ruta_c24 = new Ruta("C24", "Humanas-CyT");
        ruta_802 = new Ruta("802", "Agrarias-CyT");
        ruta_87 = new Ruta("87", "IEI-SINDU");

        paradaVeterinaria = new Parada("Veterinaria");
        paradaVeterinaria.setCoordenadas(4.6359799, -74.0856624);
        paradaVeterinaria.add(ruta_c24);
        paradaVeterinaria.add(ruta_802);

        paradaCyt = new Parada("CyT");
        paradaCyt.setCoordenadas(4.6384670, -74.0847311);
        paradaCyt.add(ruta_c24);
        paradaCyt.add(ruta_802);

        paradaHumanas = new Parada("Humanas");
        paradaHumanas.setCoordenadas(4.6342009, -74.0844837);
        paradaHumanas.add(ruta_c24);

        paradaAgrarias = new Parada("Ciencias Agrarias");
        paradaAgrarias.setCoordenadas(4.6357379, -74.0869557);
        paradaAgrarias.add(ruta_802);

        paradaOdontologia = new Parada("Odontología");
        paradaOdontologia.setCoordenadas(4.6348069, -74.0855220);
        paradaOdontologia.add(ruta_802);

        paradaIEI = new Parada("IEI");
        paradaIEI.setCoordenadas(4.6370473, -74.0811157);
        paradaIEI.add(ruta_87);

        paradaEconomicas = new Parada("Económicas");
        paradaEconomicas.setCoordenadas(4.6382184, -74.0822255);
        paradaEconomicas.add(ruta_87);

        paradaSindu = new Parada("SINDU");
        paradaSindu.setCoordenadas(4.6358426, -74.0809157);
        paradaSindu.add(ruta_87);

        listaRutas = new ArrayList<>();
        listaRutas.add(ruta_87);
        listaRutas.add(ruta_802);
        listaRutas.add(ruta_c24);

        listaParadas = new ArrayList<>();
        listaParadas.add(paradaAgrarias);
        listaParadas.add(paradaCyt);
        listaParadas.add(paradaEconomicas);
        listaParadas.add(paradaHumanas);
        listaParadas.add(paradaIEI);
        listaParadas.add(paradaSindu);
        listaParadas.add(paradaVeterinaria);
        listaParadas.add(paradaOdontologia);
    }

    public static String numeroRutas(Parada parada) {
        return String.valueOf(parada.numeroRutas());
    }

    public static String nombreParada(Parada parada) {
        return parada.getNombre();
    }

    public static ArrayList<Ruta> getRutas() {
        return listaRutas;
    }
}
