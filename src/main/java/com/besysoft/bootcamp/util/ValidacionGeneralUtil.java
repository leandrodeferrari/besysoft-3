package com.besysoft.bootcamp.util;

public class ValidacionGeneralUtil {

    public static void validarRangoDeNumeros(Byte desde, Byte hasta){

        if(desde > hasta){
            throw new IllegalArgumentException("Rango inválido.");
        }

    }

    public static void validarId(Long id){

        if(id == null){
            throw new IllegalArgumentException("El ID no puede ser nulo.");
        }

        if(id < 1){
            throw new IllegalArgumentException("El ID no puede ser menor a 1.");
        }

    }

}
