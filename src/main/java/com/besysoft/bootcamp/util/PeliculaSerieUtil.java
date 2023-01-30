package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.PeliculaSerie;

import java.util.List;

public class PeliculaSerieUtil {

    public static void validar(PeliculaSerie peliculaSerie){

        validarTitulo(peliculaSerie.getTitulo());
        validarCalificacion(peliculaSerie.getCalificacion());
        FechaUtil.validar(peliculaSerie.getFechaDeCreacion());

    }

    public static void validarCalificacion(Byte calificacion){

        if(calificacion == null){
            throw new IllegalArgumentException("La calificación no puede ser nula.");
        }

        if(calificacion < 1 || calificacion > 5){
            throw new IllegalArgumentException("La calificación no puede ser menor a 1 o mayor a 5.");
        }

    }

    public static void validarTitulo(String titulo){

        if(titulo.isBlank()){
            throw new IllegalArgumentException("El título no puede ser nulo o vacío.");
        }

    }

    public static boolean validarQueExistaPorId(List<PeliculaSerie> peliculasSeries, Long id){
        return peliculasSeries.stream().anyMatch(g -> g.getId().equals(id));
    }

}
