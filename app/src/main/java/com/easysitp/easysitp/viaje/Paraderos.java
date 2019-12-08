package com.easysitp.easysitp.viaje;

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
    public static ArrayList<Parada> listaParadas;

    public static void ini() {
        ruta_c24 = new Ruta("C24", "Entrada 53 - C. Agrarias");
        ruta_802 = new Ruta("802", "Entrada 44 - M. Arquitectura");
        ruta_87 = new Ruta("87", "Entrada 26 - CyT");

        ruta_c24.setCoordenadas(new double[][]{
                {4.6438, -74.08307}, {4.64293, -74.08369}, {4.6419, -74.08251},
                {4.6415, -74.08241}, {4.64024, -74.08278}, {4.64, -74.08192},
                {4.63949, -74.08208}, {4.6387, -74.08131}, {4.63789, -74.0829},
                {4.63696, -74.08241}, {4.63636, -74.08275}, {4.63582, -74.08273},
                {4.63564, -74.08311}, {4.63576, -74.08386}, {4.63606, -74.08401},
                {4.63532, -74.08539}, {4.63501, -74.08519}, {4.63464, -74.08582},
                {4.6357, -74.08632}, {4.63559, -74.08685}});

        ruta_802.setCoordenadas(new double[][]{
                {4.63969, -74.08906}, {4.63888, -74.08849}, {4.63867, -74.0882},
                {4.63785, -74.0857}, {4.63721, -74.08584}, {4.63623, -74.08574},
                {4.63532, -74.08539}, {4.63606, -74.08401}, {4.63576, -74.08386},
                {4.63564, -74.08311}, {4.63508, -74.08284}, {4.63486, -74.08325},
                {4.6342, -74.08342}, {4.63412, -74.08318}});

        ruta_87.setCoordenadas(new double[][]{
                {4.63299, -74.08378}, {4.63486, -74.08325}, {4.635, -74.08353},
                {4.63606, -74.08401}, {4.63531, -74.08542}, {4.63615, -74.0857},
                {4.63696, -74.08583}, {4.63785, -74.0857}, {4.63757, -74.08469},
                {4.63767, -74.08448}, {4.63799, -74.08464}});



                paradaEntradaCll53 = new Parada("Entrada calle 53");
        paradaEntradaCll53.setCoordenadas(4.6438, -74.08307);
        paradaEntradaCll53.add(ruta_c24);


        paradaCyt = new Parada("CyT");
        paradaCyt.setCoordenadas(4.63799, -74.08464);
        paradaCyt.add(ruta_87);

        paradaBiologia = new Parada("Biología");
        paradaBiologia.setCoordenadas(4.64006, -74.08212);
        paradaBiologia.add(ruta_c24);

        paradaMedicina = new Parada("Medicina");
        paradaMedicina.setCoordenadas(4.63577, -74.08456);
        paradaMedicina.add(ruta_c24);
        paradaMedicina.add(ruta_802);
        paradaMedicina.add(ruta_87);

        paradaAgrarias = new Parada("Ciencias agrarias");
        paradaAgrarias.setCoordenadas(4.63559, -74.08685);
        paradaAgrarias.add(ruta_c24);

        paradaEntradaCll44 = new Parada("Entrada calle 44");
        paradaEntradaCll44.setCoordenadas(4.63969, -74.08906);
        paradaEntradaCll44.add(ruta_802);

        paradaBiblioteca = new Parada("Biblioteca central");
        paradaBiblioteca.setCoordenadas(4.63486, -74.08325);
        paradaBiblioteca.add(ruta_802);
        paradaBiblioteca.add(ruta_87);

        paradaMuseoArq = new Parada("Museo de arquitectura");
        paradaMuseoArq.setCoordenadas(4.63412, -74.08318);
        paradaMuseoArq.add(ruta_802);

        paradaEntradaCll26 = new Parada("Entrada calle 26");
        paradaEntradaCll26.setCoordenadas(4.63299, -74.08378);
        paradaEntradaCll26.add(ruta_87);


        listaRutas = new ArrayList<>();
        listaRutas.add(ruta_87);
        listaRutas.add(ruta_802);
        listaRutas.add(ruta_c24);

        listaParadas = new ArrayList<>();
        listaParadas.add(paradaAgrarias);
        listaParadas.add(paradaBiblioteca);
        listaParadas.add(paradaBiologia);
        listaParadas.add(paradaCyt);
        listaParadas.add(paradaEntradaCll26);
        listaParadas.add(paradaEntradaCll44);
        listaParadas.add(paradaEntradaCll53);
        listaParadas.add(paradaMedicina);
        listaParadas.add(paradaMuseoArq);
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
