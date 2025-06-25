package com.ecologicamente.modelo;

import java.util.List;

/**
 * Clase que representa el estado del juego y gestiona la lógica de emparejamiento.
 */
public class Juego {
    private Tablero tablero;
    private int parejasEncontradas;

    public Juego(Tablero tablero) {
        this.tablero = tablero;
        this.parejasEncontradas = 0;
    }

    /**
     * Intenta emparejar dos cartas.
     *
     * @param i Índice de la primera carta.
     * @param j Índice de la segunda carta.
     * @return true si forman un par, false si no.
     */
    public boolean seleccionar(int i, int j) {
        Carta c1 = tablero.getCarta(i);
        Carta c2 = tablero.getCarta(j);

        if (c1.esIgual(c2)) {
            c1.marcarEmparejada();
            c2.marcarEmparejada();
            parejasEncontradas++;
            return true;
        }
        return false;
    }

    /**
     * Verifica si todas las parejas han sido encontradas.
     *
     * @return true si el juego ha terminado.
     */
    public boolean terminado() {
        return parejasEncontradas == tablero.totalPares();
    }

    /**
     * Devuelve el nombre de la imagen de una carta según el índice.
     *
     * @param index Índice de la carta.
     * @return Nombre de la imagen.
     */
    public String getImagen(int index) {
        return tablero.getCarta(index).getImagen();
    }

    /**
     * Verifica si una carta ya fue emparejada.
     *
     * @param index Índice de la carta.
     * @return true si ya está emparejada.
     */
    public boolean estaEmparejada(int index) {
        return tablero.getCarta(index).estaEmparejada();
    }
}
