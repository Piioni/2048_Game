import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Puntuaciones {
    private Stage stage;

    public Puntuaciones(Stage stage) {
        this.stage = stage;
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

        // Creacion de las filas de la tabla de puntuaciones

        Scene scene = new Scene(puntuacionesLayout, 450, 400);
        return scene;
    }
}
