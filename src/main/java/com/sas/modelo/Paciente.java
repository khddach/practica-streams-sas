package com.sas.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

    //Atributos
    private long id;
    private String nombre;
    private int edad;
    private String sexo;
    private String municipio;
    private String especialidad;
    private LocalDate fechaConsulta;
    private int tiempoEsperaMin;
    private boolean derivado;


    public boolean getDerivado() {
        return this.derivado;
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Paciente{");
        sb.append("ID: ").append(id);
        sb.append(" | ").append(nombre);
        sb.append(" | ").append(edad).append(" años");
        sb.append(" | ").append(sexo);
        sb.append(" | ").append(municipio);
        sb.append(" | ").append(especialidad);
        sb.append(" | ").append(fechaConsulta);
        sb.append(" | Espera: ").append(tiempoEsperaMin).append(" min");
        sb.append(" | Derivado: ").append(derivado);
        return sb.toString();
    }
}
