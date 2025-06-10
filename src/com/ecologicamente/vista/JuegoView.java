package com.ecologicamente.vista;

import com.ecologicamente.modelo.Carta;
import com.ecologicamente.modelo.Juego;
import com.ecologicamente.modelo.Tablero;
import com.ecologicamente.controlador.JuegoControlador;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

/**
 * Clase JuegoView: representa la interfaz gráfica del juego "EcoLógicamente".
 * Se encarga de mostrar el tablero de cartas, el contador de intentos y 
 * conectar la vista con el controlador.
 */
public class JuegoView extends Application {

    private Juego juego;
    private Label intentosLabel;
    private Button[] botones;

    @Override
    public void start(Stage primaryStage) {
        // Lista de nombres que representan las imágenes de las cartas (6 pares)
        List<String> imagenes = Arrays.asList("arbol", "agua", "reciclaje", "papel", "planta", "sol");

        // Crear el modelo: 6 pares de cartas (12 cartas en total)
        Tablero tablero = new Tablero(6, imagenes);
        juego = new Juego(tablero);

        // Crear la imagen principal de la interfaz
        BorderPane root = new BorderPane();

        // Crea y ubica la etiqueta para mostrar el número de intentos
        intentosLabel = new Label("Intentos: 0");
        root.setTop(intentosLabel);
        BorderPane.setMargin(intentosLabel, new Insets(10));

        // Crea el contenedor en una cuadrícula para las cartas (botones)
        GridPane grid = new GridPane();
        grid.setHgap(5); // espacio horizontal entre botones
        grid.setVgap(5); // espacio vertical entre botones
        grid.setPadding(new Insets(10));

        // Crea un arreglo de botones que representan las cartas
        botones = new Button[tablero.tamano()];

        int columnas = 4;
        int filas = 3; 

        // Inicializa cada botón y lo ubica en la cuadrícula
        for (int i = 0; i < tablero.tamano(); i++) {
            Button boton = new Button("❓"); // símbolo para carta oculta
            boton.setMinSize(80, 80);       // tamaño fijo de cada carta
            botones[i] = boton;
            grid.add(boton, i % columnas, i / columnas);
        }

        // Crear el controlador y enlazar los botones y etiqueta de intentos
        JuegoControlador controlador = new JuegoControlador(juego, botones, intentosLabel);

        // Asociar la acción de clic a cada botón de carta
        for (int i = 0; i < botones.length; i++) {
            final int index = i; 
            botones[i].setOnAction(e -> controlador.seleccionar(index));
        }

        // Ubica el tablero en el centro de la ventana
        root.setCenter(grid);

        // Crea y muestra la escena principal del juego
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("EcoLógicamente - Juego de Memoria");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Método principal para lanzar la aplicación JavaFX.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
