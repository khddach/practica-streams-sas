package com.sas;

import com.sas.modelo.Paciente;
import com.sas.servicios.ConsultasPaciente;
import com.sas.util.CsvLoader;

import java.io.IOException;
import java.util.IntSummaryStatistics;
import java.util.List;

public class Main {
    static void main() throws IOException {

        String ruta = "src/main/resources/pacientes.csv";

        List<Paciente> ccv = CsvLoader.cargarPacientes(ruta);

        ConsultasPaciente consultas = new ConsultasPaciente(ccv);

        //Consulta 1 — Pacientes mayores de una edad dada
        //Mostrar los pacientes con edad superior a 60 años, ordenados de mayor a menor edad.
        IO.println("**************************************");
        IO.println("Consulta 1:");
        consultas.getPacienteEdadMayores().forEach(IO::println);



         //Consulta 2 — Pacientes derivados a otro especialista
        //Obtener la lista de pacientes que han sido derivados, ordenados alfabéticamente por nombre.
        IO.println("**************************************");
        IO.println("Consulta 2:");
        consultas.getPacienteDerivados().forEach(IO::println);



        //Consulta 3 — Nombres de pacientes de una especialidad concreta
        //Obtener los nombres de los pacientes atendidos en Cardiología , sin repeticiones.
        IO.println("**************************************");
        IO.println("Consulta 3:");
        consultas.getPacienteNombresCardiologia().forEach(IO::println);


        //Consulta 4 — Primera consulta encontrada de un municipio
        //Buscar el primer paciente registrado del municipio Sevilla .
        IO.println("**************************************");
        IO.println("Consulta 4:");
        consultas.getPacientePrimerSevilla().ifPresent(IO::println);


        //Consulta 5 — Pacientes con mayor tiempo de espera
        //Mostrar los 10 pacientes con mayor tiempo de espera, de mayor a menor.
        IO.println("**************************************");
        IO.println("Consulta 5:");
        consultas.getPaciente10MayorTiempo().forEach(IO::println);



        //Consulta 6 — Tiempo de espera medio general
        //Calcular el tiempo de espera medio de todos los pacientes.
        IO.println("**************************************");
        IO.println("Consulta 6:");
        consultas.getPacienteMedioTiempo()
                .ifPresent(medio -> IO.println("Tiempo de espera medio general es : " + medio));



        //Consulta 7 — Estadísticas de edad
        //Obtener las siguientes estadísticas sobre la edad de los pacientes:
        //Media
        //Edad mínima
        //Edad máxima
        //Total de registros
        IO.println("**************************************");
        IO.println("Consulta 7:");
        IntSummaryStatistics getPacienteEstadisticasTodo = consultas.getPacienteEstadisticasTodo();
        IO.println(" Media " + getPacienteEstadisticasTodo.getAverage());
        IO.println(" Edad mínima " + getPacienteEstadisticasTodo.getMin());
        IO.println(" Edad máxima " + getPacienteEstadisticasTodo.getMax());
        IO.println(" Total de registros " + getPacienteEstadisticasTodo.getCount());




        //Consulta 8 — Número de consultas por especialidad
        //Crear un mapa donde la clave sea la especialidad y el valor sea el número de consultas
        //realizadas de esa especialidad.
        IO.println("**************************************");
        IO.println("Consulta 8:");
        consultas.getPacienteNumeroConsultasEspecialidad()
                .forEach((especialidad,numeros) -> IO.println(especialidad + " : " + numeros));



        //Consulta 9 — Número de pacientes por municipio
        //Crear un mapa donde la clave sea el municipio y el valor sea el número de pacientes atendidos
        //en ese municipio.
        IO.println("**************************************");
        IO.println("Consulta 9:");
        consultas.getPacienteNumeroMunicipio()
                .forEach((municipio,numeros) -> IO.println(municipio + " : " + numeros));



        //Consulta 10 — Tiempo de espera medio por especialidad
        //Obtener el tiempo de espera medio de cada especialidad.
        IO.println("**************************************");
        IO.println("Consulta 10:");
        consultas.getPacienteMediaEsperaEspecialidad()
                .forEach((especialidad,medio) -> IO.println(especialidad + " : " + medio.getAverage()));



        //Consulta 11 — Número de pacientes derivados por especialidad
        //Crear un mapa con el número de pacientes derivados agrupados por especialidad.
        IO.println("**************************************");
        IO.println("Consulta 11:");
        consultas.getPacienteNumeroDerivadosEspecialidad()
                .forEach((especialidad,numeros) -> IO.println(especialidad + " : " + numeros));




        //Consulta 12 — Consultas agrupadas por mes
        //Crear un mapa donde la clave sea el mes de consulta (valor numérico del 1 al 12) y el valor sea
        //el número de consultas de ese mes.
        IO.println("**************************************");
        IO.println("Consulta 12:");
        consultas.getPacienteNumeroMesConsulta()
                .forEach((mes,numeros) -> IO.println(mes + " : " + numeros));



        //Consulta 13 — Especialidad con más pacientes derivados
        //Obtener la especialidad que más pacientes ha derivado.
        IO.println("**************************************");
        IO.println("Consulta 13:");
        consultas.getPacienteNumeroDerivados()
                .ifPresent(entry -> IO.println(entry.getKey()));




        //Consulta 15 — Porcentaje de pacientes derivados
        //Calcular qué porcentaje del total de pacientes han sido derivados a otro especialista.
        IO.println("**************************************");
        IO.println("Consulta 15: " + consultas.getPacientePorcentajeDerivados());

    }
}
