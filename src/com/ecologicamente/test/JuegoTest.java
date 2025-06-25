package com.ecologicamente.test;

import com.ecologicamente.modelo.Carta;
import com.ecologicamente.modelo.Tablero;
import com.ecologicamente.modelo.Juego;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

/**
 * Pruebas unitarias para la clase Juego.
 */
public class JuegoTest {

    @Test
    public void testEmparejamientoCorrecto() {
        // Crear cartas iguales para forzar un par
        List<String> imagenes = Arrays.asList("sol", "sol", "agua", "agua");
        Tablero tablero = new Tablero(2, imagenes);
        Juego juego = new Juego(tablero);

        // Emparejar cartas que sí coinciden
        boolean resultado1 = juego.seleccionar(0, 1);
        assertTrue(resultado1, "Las cartas deben emparejarse correctamente");

        boolean resultado2 = juego.seleccionar(2, 3);
        assertTrue(resultado2, "Las segundas cartas también deben emparejarse");

        assertTrue(juego.terminado(), "El juego debe estar terminado al emparejar todos los pares");
    }

    @Test
    public void testEmparejamientoIncorrecto() {
        // Crear cartas diferentes
        List<String> imagenes = Arrays.asList("hoja", "agua", "fuego", "sol");
        Tablero tablero = new Tablero(2, imagenes);
        Juego juego = new Juego(tablero);

        // Intentar emparejar cartas que no coinciden
        boolean resultado = juego.seleccionar(0, 1);
        assertFalse(resultado, "Las cartas no deberían emparejarse");

        assertFalse(juego.terminado(), "El juego no debe estar terminado si no se han emparejado todos los pares");
    }
}
