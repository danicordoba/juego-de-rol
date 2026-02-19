package juego;

import java.util.Scanner;

public class menu {

    public void mostrar() {

        Scanner sc = new Scanner(System.in);
        juego juego = new juego();
        int opcion;

        do {
            System.out.println("\n====================================");
            System.out.println("        MINI JUEGO DE ROL");
            System.out.println("====================================");
            System.out.println("1 - Iniciar partida (aleatorio)");
            System.out.println("2 - Iniciar partida (manual)");
            System.out.println("3 - Leer logs");
            System.out.println("4 - Borrar logs");
            System.out.println("5 - Salir");
            System.out.println("====================================");
            System.out.print("Elegí una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> juego.iniciarAleatorio(sc);
                case 2 -> juego.iniciarManual(sc);
                case 3 -> juego.leerLogs();
                case 4 -> juego.borrarLogs();
            }

        } while (opcion != 5);
    }
}
