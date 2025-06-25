
package com.ecologicamente.controlador;

import com.ecologicamente.modelo.Juego;
import com.ecologicamente.vista.JuegoView;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class JuegoControlador {

    private final Juego juego;
    private final Button[] botones;
    private final Label intentosLabel;
    private final Label turnoLabel;
    private final Label puntajeLabel;
    private final int modoJugadores;

    private int intentos = 0;
    private int turnoJugador = 1;
    private int puntajeJugador1 = 0;
    private int puntajeJugador2 = 0;

    private int primeraSeleccion = -1;
    private int segundaSeleccion = -1;
    private boolean bloqueo = false;

    public JuegoControlador(Juego juego, Button[] botones, Label intentosLabel, Label turnoLabel, Label puntajeLabel, int modoJugadores) {
        this.juego = juego;
        this.botones = botones;
        this.intentosLabel = intentosLabel;
        this.turnoLabel = turnoLabel;
        this.puntajeLabel = puntajeLabel;
        this.modoJugadores = modoJugadores;
        actualizarEtiquetas();
    }

    private void actualizarEtiquetas() {
        intentosLabel.setText("Intentos: " + intentos);
        if (modoJugadores == 2) {
            turnoLabel.setText("Turno: Jugador " + turnoJugador);
            puntajeLabel.setText("Puntaje - J1: " + puntajeJugador1 + " | J2: " + puntajeJugador2);
        } else {
            turnoLabel.setText("");
            puntajeLabel.setText("Pares encontrados: " + puntajeJugador1);
        }
    }

    public void seleccionar(int index) {
        if (bloqueo || juego.estaEmparejada(index)) return;

        String nombreImagen = juego.getImagen(index);
        botones[index].setText(JuegoView.iconos.get(nombreImagen) + "\n" + nombreImagen);
        botones[index].setStyle("-fx-background-color: #c8e6c9; -fx-font-size: 16px;");

        if (primeraSeleccion == -1) {
            primeraSeleccion = index;
        } else {
            segundaSeleccion = index;
            bloqueo = true;
            intentos++;
            actualizarEtiquetas();

            PauseTransition pausa = new PauseTransition(Duration.seconds(1));
            pausa.setOnFinished(e -> {
                if (juego.seleccionar(primeraSeleccion, segundaSeleccion)) {
                    botones[primeraSeleccion].setDisable(true);
                    botones[segundaSeleccion].setDisable(true);
                    if (modoJugadores == 2) {
                        if (turnoJugador == 1) puntajeJugador1++;
                        else puntajeJugador2++;
                    } else {
                        puntajeJugador1++;
                    }
                } else {
                    botones[primeraSeleccion].setText("❓");
                    botones[primeraSeleccion].setStyle("");

                    botones[segundaSeleccion].setText("❓");
                    botones[segundaSeleccion].setStyle("");

                    if (modoJugadores == 2) {
                        turnoJugador = (turnoJugador == 1) ? 2 : 1;
                    }
                }

                actualizarEtiquetas();
                primeraSeleccion = -1;
                segundaSeleccion = -1;
                bloqueo = false;

                if (juego.terminado()) {
                    String resultado;
                    if (modoJugadores == 2) {
                        if (puntajeJugador1 > puntajeJugador2) resultado = "Jugador 1 gana 🎉";
                        else if (puntajeJugador2 > puntajeJugador1) resultado = "Jugador 2 gana 🎉";
                        else resultado = "¡Empate!";
                    } else {
                        resultado = "¡Has encontrado todos los pares!";
                    }
                    intentosLabel.setText("Juego finalizado en " + intentos + " intentos. " + resultado);
                }
            });
            pausa.play();
        }
    }
}
