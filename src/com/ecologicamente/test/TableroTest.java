package com.ecologicamente.test;

import com.ecologicamente.modelo.Tablero;
import com.ecologicamente.modelo.Carta;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TableroTest {

    @Test
    public void testNumeroDeCartas() {
        List<String> imagenes = Arrays.asList("arbol", "agua", "reciclaje");
        Tablero tablero = new Tablero(3, imagenes);

        // Debería haber 6 cartas (3 pares)
        assertEquals(6, tablero.tamano());
    }

    @Test
    public void testCartasMezcladas() {
        List<String> imagenes = Arrays.asList("arbol", "agua", "reciclaje");
        Tablero tablero = new Tablero(3, imagenes);

        Set<String> ids = new HashSet<>();
        for (Carta carta : tablero.getCartas()) {
            ids.add(carta.getIdImagen());
        }

        // Debería haber solo 3 tipos de imágenes
        assertEquals(3, ids.size());
    }

    @Test
    public void testParesDuplicados() {
        List<String> imagenes = Arrays.asList("arbol");
        Tablero tablero = new Tablero(1, imagenes);

        // Hay 2 cartas con la misma imagen
        Carta carta1 = tablero.getCarta(0);
        Carta carta2 = tablero.getCarta(1);

        assertTrue(carta1.esIgual(carta2));
    }
}
