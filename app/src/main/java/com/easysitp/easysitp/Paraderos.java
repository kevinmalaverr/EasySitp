package com.easysitp.easysitp;

import java.util.ArrayList;

public class Paraderos {


    public static Parada paradaCyt;
    public static Parada paradaSindu;
    public static Parada paradaCade;
    public static Parada paradaEnfermeria;
    public static Parada paradaConcha;

    public static Ruta ruta_c24;
    public static Ruta ruta_802;
    public static Ruta ruta_87;

    public static ArrayList<Ruta> listaRutas;

    public static void ini() {
        ruta_c24 = new Ruta("C24", "Concha-Sindu");
        ruta_802 = new Ruta("802", "Enfermería-Cade");
        ruta_87 = new Ruta("87", "CyT-sindu");

        listaRutas = new ArrayList<>();
        listaRutas.add(ruta_87);
        listaRutas.add(ruta_802);
        listaRutas.add(ruta_c24);

        paradaCade = new Parada("CADE");
        paradaCade.add(ruta_c24);
        paradaCade.add(ruta_802);

        paradaCyt = new Parada("CyT");
        paradaCyt.add(ruta_c24);
        paradaCyt.add(ruta_87);

        paradaConcha = new Parada("Concha");
        paradaConcha.add(ruta_c24);

        paradaSindu = new Parada("Sindu");
        paradaSindu.add(ruta_c24);
        paradaSindu.add(ruta_87);

        paradaEnfermeria = new Parada("Enfermería");
        paradaEnfermeria.add(ruta_802);
        paradaEnfermeria.add(ruta_87);
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
