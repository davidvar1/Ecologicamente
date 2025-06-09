package com.ecologicamente.modelo;

/**
 * Representa una carta en el juego de memoria.
 */
public class Carta {
    private String idImagen;
    private boolean descubierta;
    private boolean emparejada;

    public Carta(String idImagen) {
        this.idImagen = idImagen;
        this.descubierta = false;
        this.emparejada = false;
    }

    public String getIdImagen() {
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
