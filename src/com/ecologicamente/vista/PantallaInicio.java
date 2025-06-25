
package com.ecologicamente.vista;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PantallaInicio extends Application {

    private int modoJugadores = 2;

    @Override
    public void start(Stage primaryStage) {
        Label titulo = new Label("🌿 EcoLógicamente 🌿");
        titulo.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #2e7d32;");

        Label modoLabel = new Label("Selecciona el modo de juego:");
        modoLabel.setStyle("-fx-font-size: 16px;");

        ToggleGroup grupoModo = new ToggleGroup();
        RadioButton uno = new RadioButton("1 Jugador");
        RadioButton dos = new RadioButton("2 Jugadores");
        uno.setToggleGroup(grupoModo);
        dos.setToggleGroup(grupoModo);
        dos.setSelected(true);

        uno.setOnAction(e -> modoJugadores = 1);
        dos.setOnAction(e -> modoJugadores = 2);

        HBox modoBox = new HBox(15, uno, dos);
        modoBox.setAlignment(Pos.CENTER);

        Label dificultadLabel = new Label("Elige la dificultad:");
        dificultadLabel.setStyle("-fx-font-size: 16px;");

        Button facil = new Button("🟢 Fácil (4x3)");
        Button medio = new Button("🟡 Medio (4x4)");
        Button dificil = new Button("🔴 Difícil (6x6)");

        facil.setPrefWidth(180);
        medio.setPrefWidth(180);
        dificil.setPrefWidth(180);

        facil.setOnAction(e -> lanzarJuego(primaryStage, 6));
        medio.setOnAction(e -> lanzarJuego(primaryStage, 8));
        dificil.setOnAction(e -> lanzarJuego(primaryStage, 18));

        VBox layout = new VBox(15, titulo, modoLabel, modoBox, dificultadLabel, facil, medio, dificil);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setTitle("Inicio - EcoLógicamente");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void lanzarJuego(Stage stage, int pares) {
        JuegoView.pares = pares;
        JuegoView.modoJugadores = modoJugadores;
        stage.close();
        JuegoView.iniciarDesdeOtraVista();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
