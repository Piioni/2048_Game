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
                System.out.print("+---------"); // +--------- para cada celda
            }
            System.out.println("+");

            // Imprimir valores de la fila con separadores y 5 espacios
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j] == 0) {
                    System.out.print("|         "); // |         |
                } else {
                    System.out.printf("|%5d    ", tablero[i][j]); // |  X     |
                }
            }
            System.out.println("|");
        }

        // Imprimir la última línea horizontal
        for (int j = 0; j < columnas; j++) {
            System.out.print("+---------");
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
        boolean moved;
        do {
            moved = false;
            for (int col = 0; col < columnas; col++) {
                for (int fil = 1; fil < filas; fil++) {
                    if (tablero[fil][col] != 0 && tablero[fil - 1][col] == 0) {
                        tablero[fil - 1][col] = tablero[fil][col];
                        tablero[fil][col] = 0;
                        moved = true;
                    } else if (tablero[fil][col] != 0 && tablero[fil - 1][col] == tablero[fil][col]) {
                        tablero[fil - 1][col] *= 2;
                        tablero[fil][col] = 0;
                        moved = true;
                    }
                }
            }
        } while (moved);
        generarNumero();
        comprobarFinPartida();
    }

    public void moverAbajo() {
        boolean moved;
        do {
            moved = false;
            for (int col = 0; col < columnas; col++) {
                for (int fil = filas - 2; fil >= 0; fil--) {
                    if (tablero[fil][col] != 0 && tablero[fil + 1][col] == 0) {
                        tablero[fil + 1][col] = tablero[fil][col];
                        tablero[fil][col] = 0;
                        moved = true;
                    } else if (tablero[fil][col] != 0 && tablero[fil + 1][col] == tablero[fil][col]) {
                        tablero[fil + 1][col] *= 2;
                        tablero[fil][col] = 0;
                        moved = true;
                    }
                }
            }
        } while (moved);
        generarNumero();
        comprobarFinPartida();
    }

    public void moverIzquierda() {
        boolean moved;
        do {
            moved = false;
            for (int fil = 0; fil < filas; fil++) {
                for (int col = 1; col < columnas; col++) {
                    if (tablero[fil][col] != 0 && tablero[fil][col - 1] == 0) {
                        tablero[fil][col - 1] = tablero[fil][col];
                        tablero[fil][col] = 0;
                        moved = true;
                    } else if (tablero[fil][col] != 0 && tablero[fil][col - 1] == tablero[fil][col]) {
                        tablero[fil][col - 1] *= 2;
                        tablero[fil][col] = 0;
                        moved = true;
                    }
                }
            }
        } while (moved);
        generarNumero();
        comprobarFinPartida();
    }

    public void moverDerecha() {
        boolean moved;
        do {
            moved = false;
            for (int fil = 0; fil < filas; fil++) {
                for (int col = columnas - 2; col >= 0; col--) {
                    if (tablero[fil][col] != 0 && tablero[fil][col + 1] == 0) {
                        tablero[fil][col + 1] = tablero[fil][col];
                        tablero[fil][col] = 0;
                        moved = true;
                    } else if (tablero[fil][col] != 0 && tablero[fil][col + 1] == tablero[fil][col]) {
                        tablero[fil][col + 1] *= 2;
                        tablero[fil][col] = 0;
                        moved = true;
                    }
                }
            }
        } while (moved);
        generarNumero();
        comprobarFinPartida();
    }


    private void comprobarFinPartida() {
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
