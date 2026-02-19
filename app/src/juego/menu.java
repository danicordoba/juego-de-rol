package juego;

import java.util.Scanner;

public class menu {

    private Scanner scanner = new Scanner(System.in);
    private juego juego = new juego();

    public void mostrar() {

        int opcion = 0;

        while (opcion != 5) {

            System.out.println("\n==== mini juego de rol ====");
            System.out.println("1 - iniciar partida (aleatorio)");
            System.out.println("2 - iniciar partida (manual)");
            System.out.println("3 - leer logs");
            System.out.println("4 - borrar logs");
            System.out.println("5 - salir");
            System.out.print("elige una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    juego.iniciaraleatorio(scanner);
                    break;

                case 2:
                    juego.iniciarmanual(scanner);
                    break;
                case 3:
                    juego.leerlogs();
                    break;
                case 4:
                    juego.borrarlogs();
                    break;
                case 5:
                    System.out.println("saliendo...");
                    break;
                default:
                    System.out.println("opcion invalida");
            }
        }

        scanner.close();
    }
}
