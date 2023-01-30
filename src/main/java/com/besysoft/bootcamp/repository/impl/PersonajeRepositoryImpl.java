package com.besysoft.bootcamp.repository.impl;

import com.besysoft.bootcamp.domain.Personaje;
import com.besysoft.bootcamp.repository.IPersonajeRepository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonajeRepositoryImpl implements IPersonajeRepository {

    private List<Personaje> personajes;

    public PersonajeRepositoryImpl() {

        this.personajes = new ArrayList<>(
                Arrays.asList(
                        new Personaje(1L, "Jacqueline", (byte) 26, 55.7f, "Es una actriz canadiense. Protagonizó la serie Salvation de CBS."),
                        new Personaje(2L, "Vera", (byte) 86, 70.0f, "Supermodelo que enamoró a Coco Chanel y ahora ha conquistado a Paco Plaza."),
                        new Personaje(3L, "Christian", (byte) 35, 79.5f, "Es actor, escritor, director, productor y músico. Trabaja en el teatro, peliculas y televisión."),
                        new Personaje(4L, "Joel", (byte) 48, 90.2f, "Es actor, director y guionista australiano conocido por haber participado en la serie televisiva Teh secret life of us."),
                        new Personaje(5L, "Sofia", (byte) 29, 69.5f, "Nació en Lauderdale, Florida. Hijan de José F. Daccarett y de Laura Char Canson."),
                        new Personaje(6L, "Jeremy", (byte) 52, 80.5f, "Es actor, actor de voz, productor y músico estadounidense.")
                )
        );

    }

    @Override
    public List<Personaje> buscarPorFiltros(String nombre, Byte edad) {

        if(nombre == null && edad == null){
            return this.personajes;
        }

        if (nombre != null && edad != null){
            return this.personajes.stream()
                    .filter(p -> p.getEdad().equals(edad) && p.getNombre().equalsIgnoreCase(nombre))
                    .collect(Collectors.toList());
        }

        if(nombre != null){
            return this.personajes.stream()
                    .filter(p -> p.getNombre().equalsIgnoreCase(nombre)).collect(Collectors.toList());
        } else {
            return this.personajes.stream()
                    .filter(p -> p.getEdad().equals(edad)).collect(Collectors.toList());
        }

    }

    @Override
    public List<Personaje> buscarPorEdades(Byte desde, Byte hasta) {
        return personajes.stream()
                .filter(p -> p.getEdad() >= desde && p.getEdad() <= hasta)
                .collect(Collectors.toList());
    }

    @Override
    public Personaje crear(Personaje personaje) {

        personaje.setId(this.personajes.size()+1L);

        this.personajes.add(personaje);

        return personaje;

    }

    @Override
    public Personaje actualizar(Long id, Personaje personaje) {

        if(existePorId(id)){

            this.personajes.forEach((Personaje p) -> {

                if(p.getId().equals(id)){

                    p.setEdad(personaje.getEdad());
                    p.setNombre(personaje.getNombre());
                    p.setPeso(personaje.getPeso());
                    p.setHistoria(personaje.getHistoria());

                }

            });

            return personaje;

        } else {

            throw new IllegalArgumentException("No existe personaje con ese ID.");

        }

    }

    @Override
    public boolean existePorId(Long id) {
        return this.personajes.stream().anyMatch(g -> g.getId().equals(id));
    }

}
