package juego;

import java.time.LocalDate;
import java.time.Period;

public class personaje {

    private String nombre;
    private String apodo;
    private String raza;
    private int velocidad;
    private int destreza;
    private int fuerza;
    private int nivel;
    private int armadura;
    private int salud;
    private LocalDate fechaNacimiento;
    private int edad;

    public personaje(String nombre, String apodo, String raza, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();

        this.velocidad = 1 + (int)(Math.random() * 10);
        this.destreza = 1 + (int)(Math.random() * 5);
        this.fuerza = 1 + (int)(Math.random() * 10);
        this.nivel = 1;
        this.armadura = 1 + (int)(Math.random() * 10);
        this.salud = 100;
    }

    public void recibirDanio(double danio) {
        salud -= danio;
        if (salud < 0) salud = 0;
    }

    public void mejorar() {
        salud += 10;
        nivel++;
        if (salud > 100) salud = 100;
    }

    public boolean estaVivo() {
        return salud > 0;
    }

    public String getNombre() { return nombre; }
    public String getApodo() { return apodo; }
    public String getRaza() { return raza; }
    public int getVelocidad() { return velocidad; }
    public int getDestreza() { return destreza; }
    public int getFuerza() { return fuerza; }
    public int getNivel() { return nivel; }
    public int getArmadura() { return armadura; }
    public int getSalud() { return salud; }
}
