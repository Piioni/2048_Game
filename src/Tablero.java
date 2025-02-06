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

    public void generarNumero(){
        boolean celdaEncotrada = false;
        while (!celdaEncotrada){
            Random r = new Random();
            int i = r.nextInt(filas);
            int j = r.nextInt(columnas);
            if (tablero[i][j] == 0){
                tablero[i][j] = (r.nextInt(10) < 6) ? 2 : 4;
                celdaEncotrada = true;
            }
        }
    }

    private void finPartida() {
    }

    // Metodo para mover las celdas hacia arriba
    public void moverArriba() {
        for (int i = 0 ;i < filas; i++){
            for (int j = 0; j < columnas; j++){

            }
        }
    }

}
