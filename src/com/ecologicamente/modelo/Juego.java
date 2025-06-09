package com.ecologicamente.modelo;

import java.util.List;

/**
 * Controla la lógica del juego: turnos, emparejamientos, estado.
 */
public class Juego {
    private Tablero tablero;
    private Carta seleccionada1;
    private Carta seleccionada2;
    private int intentos;

    public Juego(Tablero tablero) {
        this.tablero = tablero;
        this.intentos = 0;
    }

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

    public void ocultarNoEmparejadas() {
        if (seleccionada1 != null && seleccionada2 != null) {
            if (!seleccionada1.esIgual(seleccionada2)) {
                seleccionada1.ocultar();
                seleccionada2.ocultar();
            }
            limpiarSeleccion();
        }
    }

    public void limpiarSeleccion() {
        seleccionada1 = null;
        seleccionada2 = null;
    }

    public boolean juegoTerminado() {
        return tablero.getCartas().stream().allMatch(Carta::estaEmparejada);
    }

    public int getIntentos() {
        return intentos;
    }

    public Tablero getTablero() {
        return tablero;
    }
}
