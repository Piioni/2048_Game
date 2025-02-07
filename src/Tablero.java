import java.util.Random;

public class Tablero {
    private final int filas = 4;
    private final int columnas = 4;
    private boolean juegoTerminado = false;

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
                if (tablero[i][j] == 0) {
                    System.out.print("|     "); // |     |
                } else {
                    System.out.print("|  " + tablero[i][j] + "  "); // |  X  |
                }
            }
            System.out.println("|");
        }

        // Imprimir la última línea horizontal
        for (int j = 0; j < columnas; j++) {
            System.out.print("+-----");
        }
        System.out.println("+");
    }

    public void generarNumero() {
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

    // Metodo para mover el tablero
    public void moverArriba() {
        for (int fil = 0; fil < filas; fil++) {
            for (int col = 0; col < columnas; col++) {
                // Si la celda no está vacía
                if (tablero[fil][col] != 0) {
                    //Guardar la fila de la celda
                    int filActual = fil;
                    while (filActual > 0 && tablero[filActual - 1][col] == 0) {
                        tablero[filActual - 1][col] = tablero[filActual][col];
                        tablero[filActual][col] = 0;
                        filActual--;
                    }
                    if (filActual > 0 && tablero[filActual - 1][col] == tablero[filActual][col]) {
                        tablero[filActual - 1][col] = tablero[filActual - 1][col] * 2;
                        tablero[filActual][col] = 0;
                    }
                }
            }
        }
        generarNumero();
    }

    public void moverAbajo() {
        for (int fil = 0; fil < filas; fil++) {
            for (int col = 0; col < columnas; col++) {
                // Si la celda no está vacía
                if (tablero[fil][col] != 0) {
                    //Guardar la fila de la celda
                    int filActual = fil;
                    // Mientras la fila no sea la última y la siguiente celda esté vacía
                    while (filActual < 4 && tablero[filActual + 1][col] == 0) {
                        // Mover la celda hacia abajo y vaciar la celda actual
                        tablero[filActual + 1][col] = tablero[filActual][col];
                        tablero[filActual][col] = 0;
                        filActual++;
                    }
                    // Si la fila no es la última y la siguiente celda tiene el mismo valor
                    if (filActual < 4 && tablero[filActual + 1][col] == tablero[filActual][col]) {
                        // Duplicar el valor de la siguiente celda y vaciar la celda actual
                        tablero[filActual + 1][col] = tablero[filActual + 1][col] * 2;
                        tablero[filActual][col] = 0;
                    }
                }
            }
        }
    }

    public void moverIzquierda() {
    }

    public void moverDerecha() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {

                if (tablero[i][j] != 0) {
                    int k = j;
                    while (k < 4 && tablero[i][k + 1] == 0) {
                        tablero[i][k + 1] = tablero[i][k];
                        tablero[i][k] = 0;
                        k++;
                    }
                    if (k < 4 && tablero[i][k + 1] == tablero[i][k]) {
                        tablero[i][k + 1] = tablero[i][k + 1] * 2;
                        tablero[i][k] = 0;
                    }
                }
            }
        }
        generarNumero();
    }


    private void ComprobarFinPartida() {
        int celdasVacias = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j] == 0) {
                    celdasVacias++;
                }
            }
        }
        if (celdasVacias == 0) {
            juegoTerminado = true;
        }
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }


}
