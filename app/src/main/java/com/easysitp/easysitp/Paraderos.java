package com.easysitp.easysitp;

import java.util.ArrayList;

public class Paraderos {


    public static Parada paradaEntradaCll53;
    public static Parada paradaBiologia;
    public static Parada paradaAgrarias;
    public static Parada paradaEntradaCll44;
    public static Parada paradaMedicina;
    public static Parada paradaBiblioteca;
    public static Parada paradaMuseoArq;
    public static Parada paradaEntradaCll26;
    public static Parada paradaCyt;



    public static Ruta ruta_c24;
    public static Ruta ruta_802;
    public static Ruta ruta_87;

    public static ArrayList<Ruta> listaRutas;

    public static void ini() {
        ruta_c24 = new Ruta("C24", "Entrada peatonal calle 53-Ciencias agrarias");
        ruta_802 = new Ruta("802", "Entrada peatonal calle 44-Museo de arquitectura");
        ruta_87 = new Ruta("87", "Entrada peatonal calle 26-CyT");

        ruta_c24.setCoordenadas(new double[][]{{1,0},{1,0}});
        ruta_802.setCoordenadas(new double[][]{{1,0},{1,0}});
        ruta_87.setCoordenadas(new double[][]{{1,0},{1,0}});

        listaRutas = new ArrayList<>();
        listaRutas.add(ruta_87);
        listaRutas.add(ruta_802);
        listaRutas.add(ruta_c24);

        paradaEntradaCll53 = new Parada("Entrada peatonal calle 53");
        paradaEntradaCll53.add(ruta_c24);

        paradaCyt = new Parada("CyT");
        paradaCyt.add(ruta_87);

        paradaBiologia = new Parada("Biología");
        paradaBiologia.add(ruta_c24);

        paradaMedicina = new Parada("Medicina");
        paradaMedicina.add(ruta_c24);
        paradaMedicina.add(ruta_802);
        paradaMedicina.add(ruta_87);

        paradaAgrarias = new Parada("Ciencias agrarias");
        paradaAgrarias.add(ruta_c24);

        paradaEntradaCll44 = new Parada("Entrada peatonal calle 44");
        paradaEntradaCll44.add(ruta_802);

        paradaBiblioteca = new Parada("Biblioteca central");
        paradaBiblioteca.add(ruta_802);
        paradaBiblioteca.add(ruta_87);

        paradaMuseoArq = new Parada("Museo de arquitectura");
        paradaMuseoArq.add(ruta_802);

        paradaEntradaCll26 = new Parada("Ciencias agrarias");
        paradaEntradaCll26.add(ruta_87);


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
