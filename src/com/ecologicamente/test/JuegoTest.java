package com.ecologicamente.test;

import com.ecologicamente.modelo.Juego;
import com.ecologicamente.modelo.Tablero;
import com.ecologicamente.modelo.Carta;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JuegoTest {

    @Test
    public void testEmparejamientoCorrecto() {
        List<String> imagenes = Arrays.asList("arbol");
        Tablero tablero = new Tablero(1, imagenes);
        Juego juego = new Juego(tablero);

        // Buscar ambas cartas con el mismo ID
        int index1 = -1;
        int index2 = -1;
        List<Carta> cartas = tablero.getCartas();
        String id = cartas.get(0).getIdImagen();

        for (int i = 0; i < cartas.size(); i++) {
            if (cartas.get(i).getIdImagen().equals(id)) {
                if (index1 == -1) {
                    index1 = i;
                } else {
                    index2 = i;
                    break;
                }
            }
        }

        assertFalse(cartas.get(index1).estaDescubierta());
        assertFalse(cartas.get(index2).estaDescubierta());

        juego.seleccionarCarta(index1);
        juego.seleccionarCarta(index2);

        assertTrue(cartas.get(index1).estaEmparejada());
        assertTrue(cartas.get(index2).estaEmparejada());
        assertEquals(1, juego.getIntentos());
    }

    @Test
    public void testJuegoTerminado() {
        List<String> imagenes = Arrays.asList("reciclaje");
        Tablero tablero = new Tablero(1, imagenes);
        Juego juego = new Juego(tablero);

        juego.seleccionarCarta(0);
        juego.seleccionarCarta(1);

        assertTrue(juego.juegoTerminado());
    }
}
