package juego;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class juego {

    private List<personaje> jugador1 = new ArrayList<>();
    private List<personaje> jugador2 = new ArrayList<>();
    private combate combate = new combate();
    private Random random = new Random();
    private StringBuilder log = new StringBuilder();
    private final String archivo = "logs.txt";

    private String nombreJugador1;
    private String nombreJugador2;


    public void iniciarAleatorio(Scanner sc) {

        jugador1.clear();
        jugador2.clear();
        log.setLength(0);

        System.out.print("Nombre Jugador 1: ");
        nombreJugador1 = sc.nextLine();

        System.out.print("Nombre Jugador 2: ");
        nombreJugador2 = sc.nextLine();

        jugador1.add(new personaje("Ragnar", "", "humano", LocalDate.now()));
        jugador1.add(new personaje("Grom", "", "orco", LocalDate.now()));
        jugador1.add(new personaje("Elandor", "", "elfo", LocalDate.now()));

        jugador2.add(new personaje("Aquiles", "", "humano", LocalDate.now()));
        jugador2.add(new personaje("Thrall", "", "orco", LocalDate.now()));
        jugador2.add(new personaje("Legarion", "", "elfo", LocalDate.now()));

        mostrarEquipos();
        jugar();
    }


    public void iniciarManual(Scanner sc) {

        jugador1.clear();
        jugador2.clear();
        log.setLength(0);

        System.out.print("Nombre Jugador 1: ");
        nombreJugador1 = sc.nextLine();

        System.out.print("Nombre Jugador 2: ");
        nombreJugador2 = sc.nextLine();

        for (int i = 0; i < 3; i++) {

            System.out.println("\n--- Personaje para " + nombreJugador1 + " ---");

            String nombre;
            do {
                System.out.print("Nombre: ");
                nombre = sc.nextLine().trim();
                if (nombre.isEmpty()) {
                    System.out.println("⚠ El nombre no puede estar vacío.");
                }
            } while (nombre.isEmpty());

            String raza;
            do {
                System.out.print("Raza (humano/elfo/orco): ");
                raza = sc.nextLine().trim().toLowerCase();

                if (!raza.equals("humano") &&
                        !raza.equals("elfo") &&
                        !raza.equals("orco")) {

                    System.out.println("⚠ Raza inválida. Debe ser humano, elfo u orco.");
                    raza = "";
                }

            } while (raza.isEmpty());

            jugador1.add(new personaje(nombre, "", raza, LocalDate.now()));
        }

        for (int i = 0; i < 3; i++) {

            System.out.println("\n--- Personaje para " + nombreJugador2 + " ---");

            String nombre;
            do {
                System.out.print("Nombre: ");
                nombre = sc.nextLine().trim();
                if (nombre.isEmpty()) {
                    System.out.println("⚠ El nombre no puede estar vacío.");
                }
            } while (nombre.isEmpty());

            String raza;
            do {
                System.out.print("Raza (humano/elfo/orco): ");
                raza = sc.nextLine().trim().toLowerCase();

                if (!raza.equals("humano") &&
                        !raza.equals("elfo") &&
                        !raza.equals("orco")) {

                    System.out.println("⚠ Raza inválida. Debe ser humano, elfo u orco.");
                    raza = "";
                }

            } while (raza.isEmpty());

            jugador2.add(new personaje(nombre, "", raza, LocalDate.now()));
        }

        mostrarEquipos();
        jugar();
    }

    private void mostrarEquipos() {

        log.append("\n====================================\n");
        log.append("           EJÉRCITOS INICIALES\n");
        log.append("====================================\n");

        log.append("🛡 El equipo de ").append(nombreJugador1)
                .append(" es: ").append(resumenEquipo(jugador1)).append("\n");

        log.append("🛡 El equipo de ").append(nombreJugador2)
                .append(" es: ").append(resumenEquipo(jugador2)).append("\n");

        log.append("\n--- Detalle de personajes ---\n");

        for (personaje p : jugador1) {
            log.append(p.getNombre())
                    .append(" es un ")
                    .append(p.getRaza().toUpperCase())
                    .append(" del equipo de ")
                    .append(nombreJugador1)
                    .append("\n");
        }

        for (personaje p : jugador2) {
            log.append(p.getNombre())
                    .append(" es un ")
                    .append(p.getRaza().toUpperCase())
                    .append(" del equipo de ")
                    .append(nombreJugador2)
                    .append("\n");
        }
    }

    private String resumenEquipo(List<personaje> lista) {

        int humanos = 0;
        int elfos = 0;
        int orcos = 0;

        for (personaje p : lista) {
            switch (p.getRaza().toLowerCase()) {
                case "humano": humanos++; break;
                case "elfo": elfos++; break;
                case "orco": orcos++; break;
            }
        }

        return humanos + " HUMANOS, " +
                elfos + " ELFOS, " +
                orcos + " ORCOS";
    }


    private void jugar() {

        int ronda = 1;
        boolean turnoJugador1 = random.nextBoolean();

        while (!jugador1.isEmpty() && !jugador2.isEmpty()) {

            personaje p1 = jugador1.get(random.nextInt(jugador1.size()));
            personaje p2 = jugador2.get(random.nextInt(jugador2.size()));

            log.append("\n====================================\n");
            log.append("             RONDA ").append(ronda).append("\n");
            log.append("====================================\n");

            int ataques1 = 0;
            int ataques2 = 0;

            while (ataques1 < 7 && ataques2 < 7 && p1.estaVivo() && p2.estaVivo()) {

                if (turnoJugador1) {

                    double danio = combate.atacar(p1, p2);

                    log.append("⚔ ")
                            .append(p1.getNombre())
                            .append(" (").append(p1.getRaza().toUpperCase()).append(") de ")
                            .append(nombreJugador1)
                            .append(" ataca a ")
                            .append(p2.getNombre())
                            .append(" (").append(p2.getRaza().toUpperCase()).append(") de ")
                            .append(nombreJugador2)
                            .append(" y quita ")
                            .append((int) danio)
                            .append(" de vida.\n");

                    ataques1++;

                } else {

                    double danio = combate.atacar(p2, p1);

                    log.append("⚔ ")
                            .append(p2.getNombre())
                            .append(" (").append(p2.getRaza().toUpperCase()).append(") de ")
                            .append(nombreJugador2)
                            .append(" ataca a ")
                            .append(p1.getNombre())
                            .append(" (").append(p1.getRaza().toUpperCase()).append(") de ")
                            .append(nombreJugador1)
                            .append(" y quita ")
                            .append((int) danio)
                            .append(" de vida.\n");

                    ataques2++;
                }

                turnoJugador1 = !turnoJugador1;
            }

            if (!p1.estaVivo()) {
                log.append("☠ ").append(p1.getNombre()).append(" murió.\n");
                jugador1.remove(p1);
                p2.mejorar();
                turnoJugador1 = true;

            } else if (!p2.estaVivo()) {
                log.append("☠ ").append(p2.getNombre()).append(" murió.\n");
                jugador2.remove(p2);
                p1.mejorar();
                turnoJugador1 = false;

            } else {
                log.append("⚖ Nadie murió en esta ronda.\n");
            }

            ronda++;
        }

        String ganador = jugador1.isEmpty() ? nombreJugador2 : nombreJugador1;

        log.append("\n====================================\n");
        log.append("🏆 GANADOR DEL TRONO: ").append(ganador).append("\n");
        log.append("====================================\n");

        System.out.println(log);
        guardarLog();
    }

    private void guardarLog() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(log.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error guardando log.");
        }
    }

    public void leerLogs() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null)
                System.out.println(linea);
        } catch (IOException e) {
            System.out.println("No hay logs.");
        }
    }

    public void borrarLogs() {
        try (PrintWriter pw = new PrintWriter(archivo)) {
            pw.print("");
            System.out.println("Logs borrados.");
        } catch (IOException e) {
            System.out.println("Error.");
        }
    }
}
