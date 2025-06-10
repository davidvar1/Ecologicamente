package com.ecologicamente.modelo;

import java.util.List;

/**
 * Controla la lógica del juego: turnos, intentos, emparejamientos y finalización.
 */
public class Juego {
    private Tablero tablero;
    private Carta seleccionada1;
    private Carta seleccionada2;
    private int intentos;

    /**
     * Inicializa el juego con un tablero ya configurado.
     * @param tablero Tablero que contiene las cartas del juego.
     */
    public Juego(Tablero tablero) {
        this.tablero = tablero;
        this.intentos = 0;
    }

    /**
     * Selecciona una carta por su índice.
     * Si es la segunda carta, compara si forma pareja con la primera.
     * @param index posición de la carta seleccionada.
     * @return true si las cartas emparejan, false si no.
     */
    public boolean seleccionarCarta(int index) {
        Carta seleccionada = tablero.getCarta(index);

        if (seleccionada.estaDescubierta() || seleccionada.estaEmparejada()) {
            return false;
        }

        seleccionada.descubrir();

        if (seleccionada1 == null) {
            seleccionada1 = seleccionada;
        } else {
            seleccionada2 = seleccionada;
            intentos++;

            if (seleccionada1.esIgual(seleccionada2)) {
                seleccionada1.marcarEmparejada();
                seleccionada2.marcarEmparejada();
                limpiarSeleccion();
                return true;
            }
        }

        return false;
    }

    /**
     * Si las cartas seleccionadas no son iguales, las vuelve a ocultar.
     */
    public void ocultarNoEmparejadas() {
        if (seleccionada1 != null && seleccionada2 != null) {
            if (!seleccionada1.esIgual(seleccionada2)) {
                seleccionada1.ocultar();
                seleccionada2.ocultar();
            }
            limpiarSeleccion();
        }
    }

    /**
     * Limpia las cartas seleccionadas para permitir un nuevo turno.
     */
    public void limpiarSeleccion() {
        seleccionada1 = null;
        seleccionada2 = null;
    }

    /**
     * Verifica si el juego ha terminado (todas las cartas emparejadas).
     * @return true si todas las cartas están emparejadas.
     */
    public boolean juegoTerminado() {
        return tablero.getCartas().stream().allMatch(Carta::estaEmparejada);
    }

    /**
     * Obtiene el número de intentos realizados por el jugador.
     * @return Número de intentos.
     */
    public int getIntentos() {
        return intentos;
    }

    /**
     * Obtiene el tablero actual del juego.
     * @return objeto Tablero.
     */
    public Tablero getTablero() {
        return tablero;
    }
}
