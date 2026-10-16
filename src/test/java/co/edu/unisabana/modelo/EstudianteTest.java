package co.edu.unisabana.modelo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EstudianteTest {

    @Test
    public void getNombreYEdadDevuelvenLosValoresDelConstructor() {
        Estudiante estudiante = new Estudiante("Ana", 22);

        assertEquals("Ana", estudiante.getNombre());
        assertEquals(22, estudiante.getEdad());
    }

    @Test
    public void setEdadActualizaElValor() {
        Estudiante estudiante = new Estudiante("Ana", 22);

        estudiante.setEdad(23);

        assertEquals(23, estudiante.getEdad());
    }
}
