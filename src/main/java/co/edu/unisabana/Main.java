package co.edu.unisabana;

import co.edu.unisabana.modelo.Estudiante;
import co.edu.unisabana.modelo.Persona;

public class Main {
    public static void main(String[] args) {
        Estudiante estudiante = new Estudiante("Juan", 21);
        estudiante.imprimir();

        // Polimorfismo: una referencia de tipo Persona invoca la versión
        // sobrescrita de presentarse() definida en Estudiante.
        Persona persona = estudiante;
        persona.presentarse();
    }
}
