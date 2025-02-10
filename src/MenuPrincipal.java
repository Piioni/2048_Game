import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


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
            Tablero tablero = new Tablero(stage);
            stage.setScene(tablero.getScene());
        });

        Button btnSalir = new Button("Salir");
        btnSalir.setOnAction(e -> System.exit(0));

        menuLayout.getChildren().addAll(tituloBox, btnJugar, btnSalir);

        Scene scene = new Scene(menuLayout, 450, 400);
        scene.getStylesheets().add("./Styles/StylesMenu.css");

        return scene;
    }
}

