package com.sas.util;


import com.sas.modelo.Paciente;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CsvLoader {

    public static List<Paciente> cargarPacientes(String ruta) throws IOException {

        try {
            return Files.lines(Paths.get(ruta))
                    .skip(1) // ignorar cabecera
                    .map(linea -> {
                        String[] campos = linea.split(",");

                        //1,Dotti Dye,5,MUJER,Bromma,Neumología,2026-01-06,131,false
                        return new Paciente(
                                Long.parseLong(campos[0]),
                                campos[1],
                                Integer.parseInt(campos[2]),
                                campos[3],
                                campos[4],
                                campos[5],
                                LocalDate.parse(campos[6]),
                                Integer.parseInt(campos[7]),
                                Boolean.parseBoolean(campos[8])
                        );
                    })
                    .collect(Collectors.toList());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
