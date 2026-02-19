package juego;

import java.util.ArrayList;

public class jugador {

    private String nombre;
    private ArrayList<personaje> personajes;

    public jugador(String nombre) {
        this.nombre = nombre;
        this.personajes = new ArrayList<>();
    }

    public void agregarpersonaje(personaje p) {
        if (personajes.size() < 3) {
            personajes.add(p);
        }
    }

    public ArrayList<personaje> getpersonajes() {
        return personajes;
    }

    public String getnombre() {
        return nombre;
    }

    public boolean tienepersonajesvivos() {
        for (personaje p : personajes) {
            if (p.estavivo()) {
                return true;
            }
        }
        return false;
    }
}
