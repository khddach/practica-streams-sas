package com.sas.servicios;

import com.sas.modelo.Paciente;
import lombok.Data;

import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class ConsultasPaciente {

    private List<Paciente> pacientes;


    public ConsultasPaciente(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }


    /**
     *  Pacientes mayores de una edad dada
     * Mostrar los pacientes con edad superior a 60 años,
     * ordenados de mayor a menor edad.
     * @return List<Paciente>
     */
    public List<Paciente> getPacienteEdadMayores() {
        return this.pacientes.stream()
                .filter(paciente -> paciente.getEdad() > 60)
                .sorted(Comparator.comparing(Paciente::getEdad).reversed())
                .toList();
    }


    /**
     *  Pacientes derivados a otro especialista
     * Obtener la lista de pacientes que han sido derivados, ordenados alfabéticamente por nombre.
     * @return List<Paciente>
     */
    public List<Paciente> getPacienteDerivados() {
        return this.pacientes.stream()
                .filter(Paciente::getDerivado)
                .sorted(Comparator.comparing(Paciente::getNombre).reversed())
                .toList();
    }



    /**
     *  Nombres de pacientes de una especialidad concreta
     * Obtener los nombres de los pacientes atendidos en Cardiología , sin repeticiones.
     * @return List<Paciente>
     */
    public List<String> getPacienteNombresCardiologia() {
        return this.pacientes.stream()
                .filter(paciente -> paciente.getEspecialidad().equals("Cardiología"))
                .map(Paciente::getNombre)
                .distinct()
                .collect(Collectors.toList());
    }



    /**
     *  Primera consulta encontrada de un municipio
     * Buscar el primer paciente registrado del municipio Sevilla .
     * @return Optional<Paciente>
     */
    public Optional<Paciente> getPacientePrimerSevilla() {
        return this.pacientes.stream()
                .filter(paciente -> paciente.getMunicipio().equals("Sevilla"))
                .findFirst();
    }




    /**
     * Pacientes con mayor tiempo de espera
     * Mostrar los 10 pacientes con mayor tiempo de espera, de mayor a menor.
     * @return List<Paciente>
     */
    public List<Paciente> getPaciente10MayorTiempo() {
        return this.pacientes.stream()
                .sorted(Comparator.comparing(Paciente::getTiempoEsperaMin).reversed())
                .limit(10)
                .toList();
    }



    /**
     * Tiempo de espera medio general
     * Calcular el tiempo de espera medio de todos los pacientes.
     * @return OptionalDouble
     */
    public OptionalDouble getPacienteMedioTiempo() {
        return this.pacientes.stream()
                .mapToInt(Paciente::getTiempoEsperaMin)
                .average();
    }



    /**
     * Estadísticas de edad
     * Obtener las siguientes estadísticas sobre la edad de los pacientes:
     * Media
     * Edad mínima
     * Edad máxima
     * Total de registros
     * @return IntSummaryStatistics
     */
    public IntSummaryStatistics getPacienteEstadisticasTodo() {
        return this.pacientes.stream()
                .mapToInt(Paciente::getEdad)
                .summaryStatistics();
    }



    /**
     * Número de consultas por especialidad
     * Crear un mapa donde la clave sea la especialidad y el valor sea el número de consultas
     * realizadas de esa especialidad.
     * @return Map<String, Long>
     */
    public Map<String, Long> getPacienteNumeroConsultasEspecialidad() {

        return this.pacientes.stream()
                .collect(Collectors.groupingBy(Paciente::getEspecialidad,Collectors.counting()));
    }



    /**
     * Número de pacientes por municipio
     * Crear un mapa donde la clave sea el municipio y el valor sea el número de pacientes atendidos
     * en ese municipio.
     * @return Map<String, Long>
     */
    public Map<String, Long> getPacienteNumeroMunicipio() {

        return this.pacientes.stream()
                .collect(Collectors.groupingBy(Paciente::getMunicipio,Collectors.counting()));
    }


    /**
     *  Tiempo de espera medio por especialidad
     * Obtener el tiempo de espera medio de cada especialidad.
     * @return Map<String, IntSummaryStatistics>
     */
    public Map<String, IntSummaryStatistics> getPacienteMediaEsperaEspecialidad() {

        return this.pacientes.stream()
                .collect(Collectors.groupingBy(Paciente::getEspecialidad,Collectors.summarizingInt(Paciente::getTiempoEsperaMin)));
    }



    /**
     * Número de pacientes derivados por especialidad
     * Crear un mapa con el número de pacientes derivados agrupados por especialidad.
     * @return Map<String, Long>
     */
    public Map<String, Long> getPacienteNumeroDerivadosEspecialidad() {

        return this.pacientes.stream()
                .filter(Paciente::getDerivado)
                .collect(Collectors.groupingBy(Paciente::getEspecialidad,Collectors.counting()));
    }



    /**
     * Consultas agrupadas por mes
     * Crear un mapa donde la clave sea el mes de consulta (valor numérico del 1 al 12) y el valor sea
     * el número de consultas de ese mes.
     * @return Map<Integer, Long>
     */
    public Map<Integer, Long> getPacienteNumeroMesConsulta() {

        return this.pacientes.stream()
                .collect(Collectors.groupingBy(paciente -> paciente.getFechaConsulta().getMonthValue(),Collectors.counting()));
    }



    /**
     * Especialidad con más pacientes derivados
     * Obtener la especialidad que más pacientes ha derivado.
     * @return Optional<Map.Entry<String, Long>>
     */
    public Optional<Map.Entry<String, Long>> getPacienteNumeroDerivados() {

        return this.pacientes.stream()
                .filter(Paciente::getDerivado)
                .collect(Collectors.groupingBy(Paciente::getEspecialidad,Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByKey());
    }




    /**
     * Comprobar si todos los pacientes de Pediatría son menores
     * de 15 años
     * Verificar si todos los pacientes atendidos en Pediatría tienen menos de 15 años.
     * @return boolean
     */
    public boolean getPacienteComprobarMenores() {

        return this.pacientes.stream()
                .filter(paciente -> paciente.getEspecialidad().equals("Pediatría"))
                .allMatch(paciente ->  paciente.getEdad() < 15);
    }



    /**
     * Porcentaje de pacientes derivados
     * Calcular qué porcentaje del total de pacientes han sido derivados a otro especialista.
     * @return boolean
     */
    public double getPacientePorcentajeDerivados() {

        long pacientesCount = this.pacientes.stream()
                .filter(Paciente::getDerivado)
                .count();

        return (double) (pacientesCount / this.pacientes.size()) * 100.0;
    }
}
