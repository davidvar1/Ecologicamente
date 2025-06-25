
package com.ecologicamente.vista;

import com.ecologicamente.controlador.JuegoControlador;
import com.ecologicamente.modelo.Juego;
import com.ecologicamente.modelo.Tablero;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class JuegoView extends Application {

    public static int pares = 6;
    public static int modoJugadores = 2;

    public static final Map<String, String> iconos = Map.ofEntries(
        Map.entry("arbol", "🌳"), Map.entry("agua", "💧"), Map.entry("reciclaje", "♻️"),
        Map.entry("papel", "📄"), Map.entry("planta", "🪴"), Map.entry("sol", "☀️"),
        Map.entry("hoja", "🍃"), Map.entry("fuego", "🔥"), Map.entry("aire", "🌬️"),
        Map.entry("nube", "☁️"), Map.entry("tierra", "🌍"), Map.entry("semilla", "🌱"),
        Map.entry("eco", "🌿"), Map.entry("basura", "🗑️"), Map.entry("botella", "🥤"),
        Map.entry("viento", "💨"), Map.entry("luz", "💡"), Map.entry("flor", "🌸")
    );

    @Override
    public void start(Stage primaryStage) {
        List<String> imagenes = new ArrayList<>(iconos.keySet());
        Collections.shuffle(imagenes);
        List<String> usadas = imagenes.subList(0, pares);

        Tablero tablero = new Tablero(pares, usadas);
        Juego juego = new Juego(tablero);

        BorderPane root = new BorderPane();
        VBox topBox = new VBox(5);
        topBox.setPadding(new Insets(10));
        topBox.setAlignment(Pos.TOP_CENTER);

        Label titulo = new Label("🌿 EcoLógicamente 🌿");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2e7d32;");
        Label intentosLabel = new Label("Intentos: 0");
        Label turnoLabel = new Label("Turno: Jugador 1");
        Label puntajeLabel = new Label("Puntaje - J1: 0 | J2: 0");

        topBox.getChildren().addAll(titulo, intentosLabel, turnoLabel, puntajeLabel);
        root.setTop(topBox);

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));

        Button[] botones = new Button[tablero.tamano()];
        int columnas = (int) Math.ceil(Math.sqrt(tablero.tamano()));
        int filas = (int) Math.ceil((double) tablero.tamano() / columnas);

        for (int i = 0; i < tablero.tamano(); i++) {
            Button boton = new Button("❓");
            boton.setPrefSize(70, 70);
            botones[i] = boton;
            grid.add(boton, i % columnas, i / columnas);
        }

        JuegoControlador controlador = new JuegoControlador(
            juego, botones, intentosLabel, turnoLabel, puntajeLabel, modoJugadores
        );

        for (int i = 0; i < botones.length; i++) {
            final int index = i;
            botones[i].setOnAction(e -> controlador.seleccionar(index));
        }

        root.setCenter(grid);
        Scene scene = new Scene(root, 100 + columnas * 80, 150 + filas * 90);
        primaryStage.setTitle("EcoLógicamente - Juego de Memoria");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void iniciarDesdeOtraVista() {
        Platform.runLater(() -> {
            try {
                new JuegoView().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}

