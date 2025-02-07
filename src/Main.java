import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Tablero tablero = new Tablero();
        tablero.imprimirTablero();


        while (!tablero.isJuegoTerminado()) {
            Scanner scanner = new Scanner(System.in);
            String movimiento = scanner.next();
            switch (movimiento) {
                case "w":
                    tablero.moverArriba();
                    break;
                case "s":
                    tablero.moverAbajo();
                    break;
                case "a":
                    tablero.moverIzquierda();
                    break;
                case "d":
                    tablero.moverDerecha();
                    break;
                default:
                    System.out.println("Movimiento no válido");
            }
            tablero.imprimirTablero();
        }
        if (tablero.isWin()) {
            System.out.println("¡Felicidades, has ganado!");
        } else {
            System.out.println("¡Lo siento, has perdido!");
        }


    }
}
