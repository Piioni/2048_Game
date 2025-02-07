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

    // Metodo para mover el tablero hacia arriba
    public void moverArriba() {
        // Se recorre el tablero de arriba a abajo y de izquierda a derecha
        for (int col = 0; col < columnas; col++) {
            // Se crea un nuevo array para almacenar la columna
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
                    } else {
                        // Se agrega la celda al nuevo array y se incrementa el indice
                        nuevaColumna[indice++] = tablero[fil][col];
                    }
                }
            }
            // Se agregan los valores al tablero
            for (int fil = 0; fil < filas; fil++) {
                tablero[fil][col] = nuevaColumna[fil];
            }
        }
        generarNumero();
        comprobarFinPartida();
    }

    public void moverAbajo() {
        for (int col = 0; col < columnas; col ++){
            int[] nuevaColumna = new int[filas];
            int indice = filas - 1;
            for (int fil = filas - 1; fil >= 0; fil--){
                // Si la celda del tablero no es 0
                if (tablero[fil][col] != 0){
                    // Si el indice es menor al tamaño del tablero y la celda siguiente es igual a la celda actual
                    if (indice < filas - 1 && nuevaColumna[indice + 1] == tablero[fil][col]){
                        nuevaColumna[indice + 1] *= 2;
                    } else {
                        nuevaColumna[indice--] = tablero[fil][col];
                    }
                }
            }
            for (int fil = filas - 1; fil >= 0; fil--){
                tablero[fil][col] = nuevaColumna[fil];
            }

        }
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
