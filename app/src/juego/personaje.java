package juego;

public class personaje {

    private String apodo;
    private String raza;
    private int salud;
    private int fuerza;
    private int destreza;
    private int velocidad;
    private int armadura;
    private int nivel;

    public personaje(String apodo, String raza) {
        this.apodo = apodo;
        this.raza = raza;
        this.salud = 100;
        this.nivel = 1;

        this.fuerza = 5 + (int)(Math.random() * 6);
        this.destreza = 5 + (int)(Math.random() * 6);
        this.velocidad = 5 + (int)(Math.random() * 6);
        this.armadura = 5 + (int)(Math.random() * 6);
    }

    public void recibirdanio(int danio) {
        salud -= danio;
        if (salud < 0) salud = 0;
    }

    public void aumentarsalud(int cantidad) {
        salud += cantidad;
        if (salud > 100) salud = 100;
    }

    public void subirmivel() {
        nivel++;
        fuerza++;
        destreza++;
        velocidad++;
        armadura++;
    }

    public boolean estavivo() {
        return salud > 0;
    }

    public String getapodo() { return apodo; }
    public String getraza() { return raza; }
    public int getsalud() { return salud; }
    public int getfuerza() { return fuerza; }
    public int getdestreza() { return destreza; }
    public int getvelocidad() { return velocidad; }
    public int getarmadura() { return armadura; }
    public int getnivel() { return nivel; }
}
