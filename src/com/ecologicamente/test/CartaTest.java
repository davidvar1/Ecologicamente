package com.ecologicamente.test;

import com.ecologicamente.modelo.Carta;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartaTest {

    @Test
    public void testDescubrirYOcultar() {
        Carta carta = new Carta("arbol");

        assertFalse(carta.estaDescubierta());
        carta.descubrir();
        assertTrue(carta.estaDescubierta());

        carta.ocultar();
        assertFalse(carta.estaDescubierta());
    }

    @Test
    public void testEmparejamiento() {
        Carta carta1 = new Carta("agua");
        Carta carta2 = new Carta("agua");
        Carta carta3 = new Carta("reciclaje");

        assertTrue(carta1.esIgual(carta2));
        assertFalse(carta1.esIgual(carta3));
    }
}
