package com.ecologicamente.modelo;

import java.util.Arrays;
import java.util.List;

public class PruebaConsola {
    public static void main(String[] args) {
        // Simulamos 3 imágenes diferentes (3 pares)
        List<String> imagenes = Arrays.asList("arbol", "reciclaje", "agua");

        // Creamos un tablero con 3 pares
        Tablero tablero = new Tablero(3, imagenes);

        // Mostramos las cartas en consola
        System.out.println("Cartas mezcladas:");
        int posicion = 0;
        for (Carta carta : tablero.getCartas()) {
            System.out.println("Posición " + posicion + " → ID: " + carta.getIdImagen());
            posicion++;
        }
    }
}
