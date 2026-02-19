package juego;

import java.io.*;
import java.util.*;

public class juego {

    private List<personaje> jugador1 = new ArrayList<>();
    private List<personaje> jugador2 = new ArrayList<>();
    private combate combate = new combate();
    private Random random = new Random();
    private final String archivo = "logs.txt";

    private String nombreJugador1;
    private String nombreJugador2;

    public void iniciaraleatorio(Scanner sc) {

        jugador1.clear();
        jugador2.clear();

        System.out.print("nombre jugador 1: ");
        nombreJugador1 = sc.nextLine();

        System.out.print("nombre jugador 2: ");
        nombreJugador2 = sc.nextLine();

        String[] razas = {"humano", "elfo", "orco"};

        System.out.println("\n========== personajes generados ==========\n");

        for (int i = 0; i < 6; i++) {

            personaje p = new personaje("carta" + (i+1),
                    razas[random.nextInt(3)]);

            System.out.println("-> " + p.getapodo()
                    + " (" + p.getraza() + ")");

            if (i < 3) jugador1.add(p);
            else jugador2.add(p);
        }

        jugar();
    }


    public void iniciarmanual(Scanner sc) {

        jugador1.clear();
        jugador2.clear();

        System.out.print("nombre jugador 1: ");
        nombreJugador1 = sc.nextLine();

        System.out.print("nombre jugador 2: ");
        nombreJugador2 = sc.nextLine();

        for (int i = 0; i < 3; i++) {

            System.out.println("\n" + nombreJugador1 + " - carta " + (i+1));

            String apodo;
            do {
                System.out.print("nombre carta: ");
                apodo = sc.nextLine();
            } while (apodo.trim().isEmpty());

            String raza;
            do {
                System.out.print("raza (humano/elfo/orco): ");
                raza = sc.nextLine().toLowerCase();
            } while (!raza.equals("humano") &&
                    !raza.equals("elfo") &&
                    !raza.equals("orco"));

            jugador1.add(new personaje(apodo, raza));
        }

        for (int i = 0; i < 3; i++) {

            System.out.println("\n" + nombreJugador2 + " - carta " + (i+1));

            String apodo;
            do {
                System.out.print("nombre carta: ");
                apodo = sc.nextLine();
            } while (apodo.trim().isEmpty());

            String raza;
            do {
                System.out.print("raza (humano/elfo/orco): ");
                raza = sc.nextLine().toLowerCase();
            } while (!raza.equals("humano") &&
                    !raza.equals("elfo") &&
                    !raza.equals("orco"));

            jugador2.add(new personaje(apodo, raza));
        }

        jugar();
    }

    private void jugar() {

        while (!jugador1.isEmpty() && !jugador2.isEmpty()) {

            personaje p1 = jugador1.get(0);
            personaje p2 = jugador2.get(0);

            System.out.println("\n========== batalla ==========");
            System.out.println(nombreJugador1 + " vs " + nombreJugador2);
            System.out.println("carta 1 (" + p1.getraza() + ") vs carta 2 (" + p2.getraza() + ")");
            System.out.println("================================");

            while (p1.estavivo() && p2.estavivo()) {

                combate.atacar(p1, p2);
                if (!p2.estavivo()) break;

                combate.atacar(p2, p1);
            }

            if (!p1.estavivo()) {
                jugador1.remove(p1);
                p2.aumentarsalud(10);
                p2.subirmivel();
            }

            if (!p2.estavivo()) {
                jugador2.remove(p2);
                p1.aumentarsalud(10);
                p1.subirmivel();
            }
        }

        String ganador = jugador1.isEmpty() ? nombreJugador2 : nombreJugador1;

        System.out.println("\n====================================");
        System.out.println("el ganador del trono es: " + ganador);
        System.out.println("====================================\n");

        guardarlog("ganador: " + ganador);
    }

    public void guardarlog(String texto) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(texto);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("error guardando log");
        }
    }

    public void leerlogs() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("no hay logs");
        }
    }

    public void borrarlogs() {
        try (PrintWriter pw = new PrintWriter(archivo)) {
            pw.print("");
            System.out.println("logs borrados");
        } catch (IOException e) {
            System.out.println("error al borrar logs");
        }
    }
}
