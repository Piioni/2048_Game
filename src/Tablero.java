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
        generarNumero();
        comprobarFinPartida();
    }

    public void moverAbajo() {
        // Se recorre el tablero de abajo a arriba y de izquierda a derecha
        for (int col = 0; col < columnas; col ++){
            int[] nuevaColumna = new int[filas];
            // Se inicializa el índice en la última fila
            int indice = filas - 1;
            for (int fil = filas - 1; fil >= 0; fil--){
                // Si la celda del tablero no es 0
                if (tablero[fil][col] != 0){
                    // Si el índice es menor a la última fila y la celda de abajo es igual a la celda actual
                    if (indice < filas - 1 && nuevaColumna[indice + 1] == tablero[fil][col]){
                        // Se multiplica por 2 la celda de abajo
                        nuevaColumna[indice + 1] *= 2;
                    } else {
                        // Se agrega la el valor a la celda de abajo y se decrementa el índice
                        nuevaColumna[indice--] = tablero[fil][col];
                    }
                }
            }
            for (int fil = filas - 1; fil >= 0; fil--){
                // Se agregan los valores al tablero
                tablero[fil][col] = nuevaColumna[fil];
            }

        }
        generarNumero();
        comprobarFinPartida();
    }

    public void moverIzquierda() {
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
                    } else {
                        // Se agrega la celda al nuevo array y se incrementa el índice
                        nuevaFila[indice++] = tablero[fil][col];
                    }
                }
            }
            // Se agregan los valores al tablero
            for (int col = 0; col < columnas; col++) {
                tablero[fil][col] = nuevaFila[col];
            }
        }
        generarNumero();
        comprobarFinPartida();
    }

    public void moverDerecha() {
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
                    } else {
                        // Se agrega la celda al nuevo array y se decrementa el índice
                        nuevaFila[indice--] = tablero[fil][col];
                    }
                }
            }
            // Se agregan los valores al tablero
            for (int col = columnas - 1; col >= 0; col--) {
                tablero[fil][col] = nuevaFila[col];
            }
        }
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
