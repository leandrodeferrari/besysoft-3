package com.besysoft.bootcamp.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class FechaUtil {

    public static LocalDate formatear(String fecha){

        DateTimeFormatter formateador = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                .toFormatter();

        return LocalDate.parse(fecha, formateador);

    }

    public static void validarRango(LocalDate desde, LocalDate hasta){

        if(desde.compareTo(hasta) > 0){
            throw new IllegalArgumentException("Rango de fecha inválido.");
        }

    }

    public static void validar(LocalDate fecha){

        if(fecha == null){
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }

        if(fecha.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha no puede ser del futuro.");
        }

    }

}
