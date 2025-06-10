package com.ecologicamente.vista;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Clase principal del proyecto "EcoLógicamente".
 * Lanza una ventana básica para verificar que JavaFX funciona correctamente.
 */
public class Main extends Application {

    /**
     * Método llamado automáticamente al iniciar la aplicación JavaFX.
     * Configura una ventana simple con un mensaje central.
     *
     * @param primaryStage La ventana principal (Stage) de la aplicación.
     */
    @Override
    public void start(Stage primaryStage) {
        // Crear un texto de bienvenida
        Label label = new Label("¡EcoLógicamente!");

        // Colocar el texto en el centro de la ventana
        StackPane root = new StackPane(label);

        // Crear la escena con ancho y alto definidos
        Scene scene = new Scene(root, 400, 200);

        // Configurar y mostrar la ventana
        primaryStage.setTitle("EcoLógicamente - Juego de Memoria");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Método main: punto de entrada del programa.
     * Llama a launch() para iniciar la aplicación JavaFX.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        launch(args);
    }
}
