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

    /**
     * Devuelve el identificador de la imagen asociada a esta carta.
     * @return ID de imagen.
     */
    public String getImagen() {
        return idImagen;
    }

    public boolean estaDescubierta() {
        return descubierta;
    }

    public boolean estaEmparejada() {
        return emparejada;
    }

    public void descubrir() {
        this.descubierta = true;
    }

    public void ocultar() {
        this.descubierta = false;
    }

    public void marcarEmparejada() {
        this.emparejada = true;
    }

    public boolean esIgual(Carta otra) {
        return this.idImagen.equals(otra.idImagen);
    }
}
