package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.Personaje;

import java.util.List;

public class PersonajeUtil {

    public static void validar(Personaje personaje){

        validarNombre(personaje.getNombre());
        validarEdad(personaje.getEdad());
        validarPeso(personaje.getPeso());
        validarHistoria(personaje.getHistoria());

    }

    public static void validarEdad(Byte edad){

        if(edad == null){
            throw new IllegalArgumentException("La edad no puede ser nula.");
        }

        if(edad < 0){
            throw new IllegalArgumentException("La edad no puede ser menor a 0.");
        }

    }

    public static void validarNombre(String nombre){

        if(nombre.isBlank()){
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }

    }

    public static void validarPeso(Float peso){

        if(peso == null){
            throw new IllegalArgumentException("El peso no puede ser nulo.");
        }

        if(peso < 0){
            throw new IllegalArgumentException("El peso no puede ser menor a 0.");
        }

    }

    public static void validarHistoria(String historia){

        if(historia.isBlank()){
            throw new IllegalArgumentException("La historia no puede ser nula o vacía.");
        }

    }

    public static boolean validarQueExistaPorId(List<Personaje> personajes, Long id){
        return personajes.stream().anyMatch(g -> g.getId().equals(id));
    }

}
