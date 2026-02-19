package juego;

import java.util.Random;

public class combate {

    private Random random = new Random();

    public double atacar(personaje atacante, personaje defensor) {

        Random random = new Random();

        // daño base entre 20 y 40
        int danioBase = 20 + random.nextInt(21);

        // ventaja simple por raza
        if (atacante.getRaza().equalsIgnoreCase("orco") &&
                defensor.getRaza().equalsIgnoreCase("humano")) {
            danioBase += 10;
        }

        if (atacante.getRaza().equalsIgnoreCase("humano") &&
                defensor.getRaza().equalsIgnoreCase("elfo")) {
            danioBase += 10;
        }

        if (atacante.getRaza().equalsIgnoreCase("elfo") &&
                defensor.getRaza().equalsIgnoreCase("orco")) {
            danioBase += 10;
        }

        defensor.recibirDanio(danioBase);

        return danioBase;
    }

}
