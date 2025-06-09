package com.ecologicamente.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa el tablero de juego con las cartas.
 */
public class Tablero {
    private List<Carta> cartas;

    public Tablero(int pares, List<String> imagenes) {
        cartas = new ArrayList<>();

        for (int i = 0; i < pares; i++) {
            String idImagen = imagenes.get(i);
            cartas.add(new Carta(idImagen));
            cartas.add(new Carta(idImagen));  // agregar la pareja
        }

        Collections.shuffle(cartas);  // mezcla aleatoria
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public int tamano() {
        return cartas.size();
    }

    public Carta getCarta(int index) {
        return cartas.get(index);
    }
}
