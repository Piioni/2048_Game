import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoreList {
    private Stage stage;
    private List<Score> scores ;

    public ScoreList(Stage stage, List<Score> scores) {
        this.stage = stage;
        this.scores = scores;
    }

    public Scene getScene() {
        // Creacion de la estructura de la pantalla de puntuaciones
        VBox puntuacionesLayout = new VBox(20);
        puntuacionesLayout.setPadding(new Insets(20));
        puntuacionesLayout.setAlignment(Pos.CENTER);

        // Creacion del titulo
        Label titulo = new Label("Puntuaciones");
        titulo.getStyleClass().add("label-title");

        VBox tituloBox = new VBox(titulo);
        tituloBox.setPadding(new Insets(0, 0, 25, 0));
        tituloBox.setAlignment(Pos.CENTER);

        // Creacion de la tabla de puntuaciones
        VBox tablaPuntuaciones = new VBox(10);
        for (Score score : scores) {
            Label scoreLabel = new Label("Score: " + score.getScore() + ", Win: " + score.isWin() + ", Date: " + score.getDate());
            scoreLabel.getStyleClass().add("score-label");
            tablaPuntuaciones.getChildren().add(scoreLabel);
        }

        // Create a button to go back to the main menu
        Button btnVolver = new Button("Volver");
        btnVolver.setOnAction(e -> {
            MenuPrincipal menuPrincipal = new MenuPrincipal(stage);
            stage.setScene(menuPrincipal.getScene());
        });
        btnVolver.setPrefWidth(150);

        puntuacionesLayout.getChildren().addAll(tituloBox, tablaPuntuaciones, btnVolver);

        Scene scene = new Scene(puntuacionesLayout, 450, 400);
        scene.getStylesheets().add("./Styles/StylesPuntuaciones.css");

        return scene;
    }

}
