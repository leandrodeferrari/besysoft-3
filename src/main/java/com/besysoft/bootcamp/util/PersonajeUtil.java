package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.Personaje;

import java.util.List;
import java.util.stream.Collectors;

public class PersonajeUtil {

    public static List<Personaje> buscarPorFiltros(List<Personaje> personajes,
                                                   String nombre, Byte edad){

        if(nombre == null && edad == null){
            return personajes;
        }

        if (nombre != null && edad != null){
            return personajes.stream()
                    .filter(p -> p.getEdad().equals(edad) && p.getNombre().equalsIgnoreCase(nombre))
                    .collect(Collectors.toList());
        }

        if(nombre != null){
            return personajes.stream()
                    .filter(p -> p.getNombre().equalsIgnoreCase(nombre)).collect(Collectors.toList());
        } else {
            return personajes.stream()
                    .filter(p -> p.getEdad().equals(edad)).collect(Collectors.toList());
        }

    }

    public static List<Personaje> buscarPorEdades(List<Personaje> personajes,
                                                   Byte desde, Byte hasta){

        validarEdad(desde);
        validarEdad(hasta);
        ValidacionGeneralUtil.validarRangoDeNumeros(desde, hasta);

        return personajes.stream()
                .filter(p -> p.getEdad() >= desde && p.getEdad() <= hasta)
                .collect(Collectors.toList());

    }

    public static void validar(Personaje personaje){

        validarNombre(personaje.getNombre());
        validarEdad(personaje.getEdad());
        validarPeso(personaje.getPeso());
        validarHistoria(personaje.getHistoria());

    }

    private static void validarEdad(Byte edad){

        if(edad == null){
            throw new IllegalArgumentException("La edad no puede ser nula.");
        }

        if(edad < 0){
            throw new IllegalArgumentException("La edad no puede ser menor a 0.");
        }

    }

    private static void validarNombre(String nombre){

        if(nombre.isBlank()){
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }

    }

    private static void validarPeso(Float peso){

        if(peso == null){
            throw new IllegalArgumentException("El peso no puede ser nulo.");
        }

        if(peso < 0){
            throw new IllegalArgumentException("El peso no puede ser menor a 0.");
        }

    }

    private static void validarHistoria(String historia){

        if(historia.isBlank()){
            throw new IllegalArgumentException("La historia no puede ser nula o vacía.");
        }

    }

    public static boolean validarQueExistaPorId(List<Personaje> personajes, Long id){
        return personajes.stream().anyMatch(g -> g.getId().equals(id));
    }

}
