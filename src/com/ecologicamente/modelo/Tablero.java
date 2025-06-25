package com.ecologicamente.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa el tablero de juego con todas las cartas.
 * Se encarga de generar los pares de cartas y mezclarlos aleatoriamente.
 */
public class Tablero {
    private List<Carta> cartas;

    /**
     * Constructor del tablero.
     * @param pares Número de pares de cartas.
     * @param imagenes Lista de identificadores de imágenes para generar los pares.
     */
    public Tablero(int pares, List<String> imagenes) {
        cartas = new ArrayList<>();

        for (int i = 0; i < pares; i++) {
            String idImagen = imagenes.get(i);
            cartas.add(new Carta(idImagen));
            cartas.add(new Carta(idImagen)); 
        }

        Collections.shuffle(cartas); // mezclar aleatoriamente
    }

    /**
     * Obtiene la lista de todas las cartas.
     * @return Lista de cartas del tablero.
     */
    public List<Carta> getCartas() {
        return cartas;
    }

    /**
     * Retorna el tamaño total del tablero (cantidad de cartas).
     * @return número de cartas en el tablero.
     */
    public int tamano() {
        return cartas.size();
    }

    /**
     * Devuelve una carta según su índice.
     * @param index posición de la carta.
     * @return Objeto Carta correspondiente al índice.
     */
    public Carta getCarta(int index) {
        return cartas.get(index);
    }
    public int totalPares() {
        return cartas.size() / 2;
    }

}
