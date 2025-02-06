import java.util.Random;

public class Tablero {
    private final int filas = 4;
    private final int columnas = 4;

    private final int[][] tablero = new int[filas][columnas];

    public Tablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = 0;
            }
        }
        generarNumero();
    }

    // Metodo para imprimir el tablero con celdas y valores dentro de ellas
    public void imprimirTablero() {
        for (int i = 0; i < filas; i++) {
            // Imprimir línea horizontal
            for (int j = 0; j < columnas; j++) {
                System.out.print("+-----"); // +----- para cada celda
            }
            System.out.println("+");

            // Imprimir valores de la fila con separadores y 2 espacios
            for (int j = 0; j < columnas; j++) {
                System.out.print("|  " + tablero[i][j] + "  "); // |  X  |
            }
            System.out.println("|");
        }

        // Imprimir la última línea horizontal
        for (int j = 0; j < columnas; j++) {
            System.out.print("+-----");
        }
        System.out.println("+");
    }

    // Metodo para generar un 2 o un 4 en solo una celda vacía
    public void generarNumero() {
        Random r = new Random();
        // Contar cuántas celdas vacías hay
        int celdasVacias = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j] == 0) {
                    celdasVacias++;
                }
            }
        }

        // Si no hay celdas vacías, no se puede colocar un número
        if (celdasVacias == 0) {
            System.out.println("No hay celdas vacías para colocar un número.");
            finPartida();
        }

        // Elegir una celda vacía al azar
        int celdaAleatoria = r.nextInt(celdasVacias) + 1;
        int contador = 0;

        for (int i = 0; i < filas ; i++){
            for (int j = 0; j < columnas; j++){
                if (tablero[i][j] == 0){
                    contador++;
                    if (contador == celdaAleatoria){
                        tablero[i][j] = (r.nextInt(10) < 8) ? 2 : 4;
                        return;
                    }
                }
            }
        }


    }

    private void finPartida() {
    }

}
