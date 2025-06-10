package com.ecologicamente.modelo;

/**
 * Representa una carta del juego de memoria.
 * Cada carta tiene un identificador (normalmente una imagen)
 * y puede estar descubierta o emparejada.
 */
public class Carta {
    private String idImagen;
    private boolean descubierta;
    private boolean emparejada;

    /**
     * Constructor que inicializa una carta con su ID.
     * @param idImagen Nombre o identificador de la imagen de la carta.
     */
    public Carta(String idImagen) {
        this.idImagen = idImagen;
        this.descubierta = false;
        this.emparejada = false;
    }

    public String getIdImagen() {
        return idImagen;
    }

    /**
     * Verifica si la carta está descubierta.
     * @return true si está descubierta, false si está oculta.
     */
    public boolean estaDescubierta() {
        return descubierta;
    }

    /**
     * Verifica si la carta ya fue emparejada.
     * @return true si está emparejada.
     */
    public boolean estaEmparejada() {
        return emparejada;
    }

    /**
     * Marca la carta como descubierta (volteada).
     */
    public void descubrir() {
        this.descubierta = true;
    }

    /**
     * Vuelve a ocultar la carta (no emparejada).
     */
    public void ocultar() {
        this.descubierta = false;
    }

    /**
     * Marca la carta como emparejada.
     */
    public void marcarEmparejada() {
        this.emparejada = true;
    }

    /**
     * Compara esta carta con otra para verificar si son del mismo tipo.
     * @param otra Otra carta para comparar.
     * @return true si ambas cartas tienen el mismo ID de imagen.
     */
    public boolean esIgual(Carta otra) {
        return this.idImagen.equals(otra.idImagen);
    }
}
