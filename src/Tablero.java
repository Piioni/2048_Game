import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class Tablero {
    private final int filas = 4;
    private final int columnas = 4;
    private boolean juegoTerminado = false;
    private boolean win = false;
    private final int[][] tablero = new int[filas][columnas];
    private List<Score> scores;
    private int score = 0;
    private final Stage stage;
    GridPane tableroLayout;

    // Constructor de la clase Tablero que inicializa el tablero con dos números aleatorios en la stage dada
    public Tablero(Stage stage, List<Score> scores) {
        this.stage = stage;
        this.scores = scores;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = 0;
            }
        }
        generarNumero();
        generarNumero();
    }


    public void generarNumero() {
        // Comprobar si hay espacio en el tablero
        boolean hayEspacio = false;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j] == 0) {
                    hayEspacio = true;
                    break;
                }
            }
            if (hayEspacio) break;
        }
        if (!hayEspacio) return;

        // Generar un número aleatorio en una celda vacía
        boolean celdaEncotrada = false;
        while (!celdaEncotrada) {
            Random r = new Random();
            int i = r.nextInt(filas);
            int j = r.nextInt(columnas);
            if (tablero[i][j] == 0) {
                tablero[i][j] = (r.nextInt(10) < 6) ? 2 : 4;
                celdaEncotrada = true;
            }
        }
    }

    public void moverArriba() {
        boolean mooved = false;
        // Se recorre el tablero de arriba a abajo y de izquierda a derecha
        for (int col = 0; col < columnas; col++) {
            int[] nuevaColumna = new int[filas];
            // Se inicializa el indice en 0
            int indice = 0;
            for (int fil = 0; fil < filas; fil++) {
                // Si la celda del tablero no es 0
                if (tablero[fil][col] != 0) {
                    // Si el indice es mayor a 0 y la celda anterior es igual a la celda actual
                    if (indice > 0 && nuevaColumna[indice - 1] == tablero[fil][col]) {
                        // Se multiplica por 2 la celda anterior
                        nuevaColumna[indice - 1] *= 2;
                        score += nuevaColumna[indice - 1];
                        mooved = true;
                    } else {
                        // Si el índice es diferente a la fila actual
                        if (indice != fil) {
                            mooved = true;
                        }
                        // Se agrega la celda al nuevo array y se incrementa el índice
                        nuevaColumna[indice++] = tablero[fil][col];
                    }
                }
            }
            // Se agregan los valores al tablero
            for (int fil = 0; fil < filas; fil++) {
                tablero[fil][col] = nuevaColumna[fil];
            }
        }
        if (mooved) generarNumero();
        comprobarFinPartida();
    }

    public void moverAbajo() {
        boolean mooved = false;
        // Se recorre el tablero de abajo a arriba y de izquierda a derecha
        for (int col = 0; col < columnas; col++) {
            int[] nuevaColumna = new int[filas];
            // Se inicializa el índice en la última fila
            int indice = filas - 1;
            for (int fil = filas - 1; fil >= 0; fil--) {
                // Si la celda del tablero no es 0
                if (tablero[fil][col] != 0) {
                    // Si el índice es menor a la última fila y la celda de abajo es igual a la celda actual
                    if (indice < filas - 1 && nuevaColumna[indice + 1] == tablero[fil][col]) {
                        // Se multiplica por 2 la celda de abajo
                        nuevaColumna[indice + 1] *= 2;
                        score += nuevaColumna[indice + 1];
                        mooved = true;
                    } else {
                        // Si el índice es diferente a la fila actual
                        if (indice != fil) {
                            mooved = true;
                        }
                        // Se agrega la el valor a la celda de abajo y se decrementa el índice
                        nuevaColumna[indice--] = tablero[fil][col];
                    }
                }
            }
            for (int fil = filas - 1; fil >= 0; fil--) {
                // Se agregan los valores al tablero
                tablero[fil][col] = nuevaColumna[fil];
            }

        }
        if (mooved) generarNumero();
        comprobarFinPartida();
    }

    public void moverIzquierda() {
        boolean mooved = false;
        // Se recorre el tablero de izquierda a derecha y de arriba a abajo
        for (int fil = 0; fil < filas; fil++) {
            int[] nuevaFila = new int[columnas];
            // Se inicializa el índice en la primera columna
            int indice = 0;
            for (int col = 0; col < columnas; col++) {
                // Si la celda del tablero no es 0
                if (tablero[fil][col] != 0) {
                    // Si el índice es mayor a 0 y la celda del lado izquierdo es igual a la celda actual
                    if (indice > 0 && nuevaFila[indice - 1] == tablero[fil][col]) {
                        nuevaFila[indice - 1] *= 2;
                        score += nuevaFila[indice - 1];
                        mooved = true;
                    } else {
                        // Si el índice es diferente a la columna actual
                        if (indice != col) {
                            mooved = true;
                        }
                        // Se agrega la celda al nuevo array y se incrementa el índice
                        nuevaFila[indice++] = tablero[fil][col];
                    }
                }
            }
            // Se agregan los valores al tablero
            System.arraycopy(nuevaFila, 0, tablero[fil], 0, columnas);
        }
        if (mooved) generarNumero();
        comprobarFinPartida();
    }

    public void moverDerecha() {
        boolean mooved = false;
        // Se recorre el tablero de derecha a izquierda y de arriba a abajo
        for (int fil = 0; fil < filas; fil++) {
            int[] nuevaFila = new int[columnas];
            // Se inicializa el índice en la última columna
            int indice = columnas - 1;
            for (int col = columnas - 1; col >= 0; col--) {
                // Si la celda del tablero no es 0
                if (tablero[fil][col] != 0) {
                    // Si el índice es menor a la última columna y la celda del lado derecho es igual a la celda actual
                    if (indice < columnas - 1 && nuevaFila[indice + 1] == tablero[fil][col]) {
                        nuevaFila[indice + 1] *= 2;
                        score += nuevaFila[indice + 1];
                        mooved = true;
                    } else {
                        // Si el índice es diferente a la columna actual
                        if (indice != col) {
                            mooved = true;
                        }
                        // Se agrega la celda al nuevo array y se decrementa el índice
                        nuevaFila[indice--] = tablero[fil][col];
                    }
                }
            }
            // Se agregan los valores al tablero
            System.arraycopy(nuevaFila, 0, tablero[fil], 0, columnas );
        }
        if (mooved) generarNumero();
        comprobarFinPartida();
    }

    private void comprobarFinPartida() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j] == 2048) {
                    juegoTerminado = true; // Se ha alcanzado el valor 2048
                    win = true;
                    return;
                }
                if (tablero[i][j] == 0) {
                    return; // Hay una celda vacía, el juego no ha terminado
                }
                // Comprobar celdas adyacentes
                if (i > 0 && tablero[i][j] == tablero[i - 1][j]) {
                    return; // Hay un movimiento válido hacia arriba
                }
                if (i < filas - 1 && tablero[i][j] == tablero[i + 1][j]) {
                    return; // Hay un movimiento válido hacia abajo
                }
                if (j > 0 && tablero[i][j] == tablero[i][j - 1]) {
                    return; // Hay un movimiento válido hacia la izquierda
                }
                if (j < columnas - 1 && tablero[i][j] == tablero[i][j + 1]) {
                    return; // Hay un movimiento válido hacia la derecha
                }
            }
        }
        juegoTerminado = true;
    }

    public Scene getScene() {
        // Creación del tablero
        tableroLayout = new GridPane();
        tableroLayout.setHgap(5);
        tableroLayout.setVgap(5);
        tableroLayout.setAlignment(javafx.geometry.Pos.CENTER);

        actualizarTablero();

        VBox tableroBox = new VBox(10);
        tableroBox.getChildren().add(tableroLayout);
        tableroBox.setAlignment(javafx.geometry.Pos.CENTER);

        Button btnReinicar = new Button("Reiniciar");
        btnReinicar.setOnAction(e -> reiniciarJuego());

        Button btnSalir = new Button("Salir");
        btnSalir.setOnAction(e -> {
            boolean salir = AlertaConfirmacion("Salir", "¿Estás seguro de que quieres salir?");
            if (salir) {
                MainApp.mostrarMenuPrincipal();
            } else {
                mostrarAlerta("No saliste", "Sos bobo");
                tableroLayout.requestFocus();

            }
        });

        HBox botonesBox = new HBox(40);
        botonesBox.getChildren().addAll(btnReinicar, btnSalir);
        botonesBox.setAlignment(javafx.geometry.Pos.CENTER);

        VBox tableroCompleto = new VBox(30);
        tableroCompleto.getChildren().addAll(tableroBox, botonesBox);
        tableroCompleto.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(tableroCompleto, 440, 500);
        scene.getStylesheets().add("/Styles/StylesTablero.css");

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP, W -> moverArriba();
                case DOWN, S -> moverAbajo();
                case LEFT, A -> moverIzquierda();
                case RIGHT, D -> moverDerecha();
            }
            actualizarTablero();
            tableroLayout.requestFocus();
        });

        return scene;
    }

    private void reiniciarJuego() {
        boolean reiniciar = AlertaConfirmacion("Reiniciar", "¿Estás seguro de que quieres reiniciar el juego?");
        if (reiniciar) {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    tablero[i][j] = 0;
                }
            }
            generarNumero();
            generarNumero();
            juegoTerminado = false;
            win = false;
            score = 0;
            actualizarTablero();

        } else {
            mostrarAlerta("No reiniciaste", "Sos bobo");
            tableroLayout.requestFocus();
        }
    }

    private void actualizarTablero() {
        tableroLayout.getChildren().clear(); // Eliminar celdas anteriores
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                StackPane celda = new StackPane();
                celda.getStyleClass().add("celda");

                Rectangle fondo = new Rectangle(96, 96);
                fondo.setFill(obtenerColor(tablero[i][j]));
                fondo.setArcWidth(20);
                fondo.setArcHeight(20);

                Text texto = getText(i, j);

                celda.getChildren().addAll(fondo, texto);
                tableroLayout.add(celda, j, i);
            }
        }

        if (juegoTerminado) {
            addScore(score, win);
            saveScores();
            if (win) {
                mostrarAlerta("¡Felicidades!", "¡Has ganado!");
            } else {
                mostrarAlerta("¡Lo siento!", "¡Has perdido!");
            }
        }


    }

    private Text getText(int i, int j) {
        // Se crea un texto con el valor de la celda y se le da un estilo dependiendo del valor
        Text texto = new Text(tablero[i][j] == 0 ? "" : String.valueOf(tablero[i][j]));
        if (tablero[i][j] == 8 || tablero[i][j] == 16 || tablero[i][j] == 32 ||
                tablero[i][j] == 64 || tablero[i][j] == 128 || tablero[i][j] == 256 ||
                tablero[i][j] == 512 || tablero[i][j] == 1024 || tablero[i][j] == 2048) {
            texto.setStyle(" -fx-font-family: Rubik ;-fx-fill: white; -fx-font-size: 30px; -fx-font-weight: bold;");
        } else if (tablero[i][j] == 2 || tablero[i][j] == 4) {
            texto.setStyle("-fx-font-family: Rubik; -fx-fill: #756452; -fx-font-size: 30px; -fx-font-weight: bold;");
        }
        return texto;
    }

    private Color obtenerColor(int valor) {
        return switch (valor) {
            case 2 -> Color.web("#eee4da");
            case 4 -> Color.web("#ebd8b6");
            case 8 -> Color.web("#f2b177");
            case 16 -> Color.web("#f79562");
            case 32 -> Color.web("#f78063");
            case 64 -> Color.web("#f76442");
            case 128 -> Color.web("#f0d26b");
            case 256 -> Color.web("#f3d35f");
            case 512 -> Color.web("#f6c838");
            case 1024 -> Color.web("#f9c52a");
            case 2048 -> Color.web("#fdbe00"); // gradient ffd746 , fbbe0f
            default -> Color.web("#bdac97");
        };
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private boolean AlertaConfirmacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        ButtonType buttonTypeSi = new ButtonType("Sí");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeSi, buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();

        // Si el usuario presiona el botón "Sí" se devuelve true, de lo contrario false
        return result.isPresent() && result.get() == buttonTypeSi;
    }

    private void saveScores(){
        MenuPrincipal.saveScores(scores);
    }

    private void addScore(int score, boolean win){
        scores.add(new Score(score, win));
    }

}
