package juego;

import java.util.Random;

public class combate {

    private Random random = new Random();

    public void atacar(personaje atacante, personaje defensor) {

        int pd = atacante.getdestreza() * atacante.getfuerza() * atacante.getnivel();
        int ed = random.nextInt(100) + 1;
        int va = pd * ed;

        int pdef = defensor.getarmadura() * defensor.getvelocidad();

        int danio = (va - pdef) / 1000;

        if (danio <= 0) danio = 1;

        if (atacante.getraza().equals("orco")) danio *= 1.1;
        if (atacante.getraza().equals("elfo")) danio *= 1.05;

        if (atacante.getraza().equals("humano") && random.nextInt(100) < 10) {
            System.out.println("golpe critico!");
            danio *= 2;
        }

        defensor.recibirdanio(danio);

        System.out.println(atacante.getraza()
                + " ataca a "
                + defensor.getraza()
                + " y le quita "
                + danio + " de vida.");

        System.out.println("vida restante de "
                + defensor.getraza()
                + ": "
                + defensor.getsalud() + "\n");
    }
}
