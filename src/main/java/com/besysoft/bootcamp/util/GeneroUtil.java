package com.besysoft.bootcamp.util;

public class GeneroUtil {

    public static void validarNombre(String nombre){

        if(nombre.isBlank()){
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }

    }

}
