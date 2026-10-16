package co.edu.unisabana.modelo;

public class Estudiante extends Persona {
    private int edad;

    public Estudiante(String nombre, int edad) {
        super(nombre);
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public void presentarse() {
        System.out.println("Hola, soy " + getNombre() + " y tengo " + edad + " anios");
    }

    public void imprimir() {
        System.out.println("Estudiante: " + getNombre() + " - Edad: " + edad);
    }
}
