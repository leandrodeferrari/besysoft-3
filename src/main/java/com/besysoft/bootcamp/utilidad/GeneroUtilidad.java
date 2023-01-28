package com.besysoft.bootcamp.utilidad;

import com.besysoft.bootcamp.dominio.Genero;

import java.util.List;

public class GeneroUtilidad {

    public static void validarNombre(String nombre){

        if(nombre.isBlank()){
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }

    }

    public static boolean validarQueExistaPorId(List<Genero> generos, Long id){
        return generos.stream().anyMatch(g -> g.getId().equals(id));
    }

}
