package com.ecologicamente.controlador;

import com.ecologicamente.modelo.Carta;
import com.ecologicamente.modelo.Juego;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Clase JuegoControlador: se encarga de manejar los eventos del usuario (clics en cartas)
 * y conectar la lógica del modelo con la vista en el juego.
 */
public class JuegoControlador {

    private Juego juego;
    private Button[] botones;
    private Label intentosLabel;
    private boolean bloqueado = false;

    private int primeraCarta = -1;
    private int segundaCarta = -1;

    /**
     * Constructor del controlador del juego.
     * @param juego Objeto que contiene la lógica del juego.
     * @param botones Arreglo de botones que representan las cartas.
     * @param intentosLabel Etiqueta para mostrar el número de intentos.
     */
    public JuegoControlador(Juego juego, Button[] botones, Label intentosLabel) {
        this.juego = juego;
        this.botones = botones;
        this.intentosLabel = intentosLabel;
    }

    /**
     * Método llamado cuando el usuario selecciona una carta.
     * Controla el flujo de selección de cartas y maneja emparejamientos.
     * @param index Índice del botón presionado (carta seleccionada).
     */
    public void seleccionar(int index) {
        if (bloqueado) return;
        if (index == primeraCarta || index == segundaCarta) return;

        Carta carta = juego.getTablero().getCarta(index);
        if (carta.estaDescubierta() || carta.estaEmparejada()) return;

        carta.descubrir();
        botones[index].setText(carta.getIdImagen());
        botones[index].setDisable(true);

        if (primeraCarta == -1) {
            primeraCarta = index;
        } else {
            segundaCarta = index;
            bloqueado = true;
            juego.seleccionarCarta(primeraCarta);
            juego.seleccionarCarta(segundaCarta);

            intentosLabel.setText("Intentos: " + juego.getIntentos());

            // Temporizador para pausar antes de verificar emparejamiento
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> verificarEmparejamiento());
                }
            }, 1000); // Espera de 1 segundo
        }
    }

    /**
     * Verifica si las dos cartas seleccionadas forman una pareja.
     * Si no coinciden, las vuelve a ocultar. Si coinciden, las marca visualmente.
     */
    private void verificarEmparejamiento() {
        Carta c1 = juego.getTablero().getCarta(primeraCarta);
        Carta c2 = juego.getTablero().getCarta(segundaCarta);

        if (!c1.esIgual(c2)) {
            c1.ocultar();
            c2.ocultar();
            botones[primeraCarta].setText("❓");
            botones[segundaCarta].setText("❓");
            botones[primeraCarta].setDisable(false);
            botones[segundaCarta].setDisable(false);
        } else {
            botones[primeraCarta].setStyle("-fx-background-color: lightgreen");
            botones[segundaCarta].setStyle("-fx-background-color: lightgreen");
        }

        primeraCarta = -1;
        segundaCarta = -1;
        bloqueado = false;

        if (juego.juegoTerminado()) {
            intentosLabel.setText("¡Ganaste en " + juego.getIntentos() + " intentos!");
        }
    }
}

