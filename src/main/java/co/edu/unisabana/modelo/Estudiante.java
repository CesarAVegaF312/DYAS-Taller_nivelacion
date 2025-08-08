package co.edu.unisabana.modelo;

public class Estudiante {
    private String nombre;
    private int edad;

    public Estudiante(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public void imprimir() {
        System.out.println("Estudiante: " + nombre + " - Edad: " + edad);
    }
}
