import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MenuPrincipal {
    private final Stage stage;

    public MenuPrincipal(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {

        // Creation de la estructura del menu principal
        VBox menuLayout = new VBox(20);
        menuLayout.setPadding(new Insets(20));
        menuLayout.setAlignment(Pos.CENTER);

        // Creation del titulo
        Label titulo = new Label("2048");
        titulo.getStyleClass().add("label-title");

        VBox tituloBox = new VBox(titulo);
        tituloBox.setPadding(new Insets(0, 0, 25, 0));
        tituloBox.setAlignment(Pos.CENTER);

        // Creation de los botones
        Button btnJugar = new Button("Jugar");
        btnJugar.setOnAction(e -> {
            List<Score> scores = loadScores();
            Tablero tablero = new Tablero(stage, scores);
            stage.setScene(tablero.getScene());
        });
        btnJugar.setPrefWidth(150);

        Button btnCargar = new Button("Cargar");
        btnCargar.setOnAction(e -> {


        });
        btnCargar.setPrefWidth(150);


        Button btnPuntuaciones = new Button("Puntuaciones");
        btnPuntuaciones.setOnAction(e -> {
            List<Score> scores = loadScores();
            ScoreList puntuaciones = new ScoreList(stage, scores);
            stage.setScene(puntuaciones.getScene());
        });
        btnPuntuaciones.setPrefWidth(150);

        Button btnSalir = new Button("Salir");
        btnSalir.setOnAction(e -> System.exit(0));
        btnSalir.setPrefWidth(150);

        menuLayout.getChildren().addAll(tituloBox, btnJugar, btnCargar, btnPuntuaciones, btnSalir);

        Scene scene = new Scene(menuLayout, 550, 500);
        scene.getStylesheets().add("./Styles/StylesMenu.css");

        return scene;
    }

    public static void saveScores(List<Score> scores) {
        try (FileWriter out = new FileWriter("scores.json")) {
            out.write("[");
            for (int i = 0; i < scores.size(); i++) {
                Score score = scores.get(i);
                out.write("{\"score\":" + score.getScore() + ",\"win\":" + score.isWin() + ",\"date\":\"" + score.getDate() + "\"}");
                if (i < scores.size() - 1) {
                    out.write(",");
                }
            }
            out.write("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Score> loadScores() {
        List<Score> scores = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("scores.json"))) {
            StringBuilder json = new StringBuilder();
            while (scanner.hasNextLine()) {
                json.append(scanner.nextLine());
            }
            // Devolver lista vacia si el archivo esta vacio
            String jsonString = json.toString();
            if (jsonString.isEmpty()) {
                return scores; // Return empty list if file is empty
            }
            // Parse JSON string
            jsonString = jsonString.substring(1, jsonString.length() - 1); // Remove brackets
            String[] scoreStrings = jsonString.split("},\\{");
            for (String scoreString : scoreStrings) {
                scoreString = scoreString.replace("{", "").replace("}", "");
                String[] fields = scoreString.split(",");
                int scoreValue = Integer.parseInt(fields[0].split(":")[1]);
                boolean win = Boolean.parseBoolean(fields[1].split(":")[1]);
                LocalDate date = LocalDate.parse(fields[2].split(":")[1].replace("\"", ""));
                scores.add(new Score(scoreValue, win, date));
            }
        } catch (FileNotFoundException e) {
            // Default to an empty list if file not found
        }
        return scores;
    }
}

