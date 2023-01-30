package com.besysoft.bootcamp.repository.impl;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.repository.IGeneroRepository;
import com.besysoft.bootcamp.util.GeneroUtil;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class GeneroRepositoryImpl implements IGeneroRepository {

    private List<Genero> generos;

    public GeneroRepositoryImpl() {

        this.generos = new ArrayList<>(
                Arrays.asList(
                        new Genero(1L, "Terror"),
                        new Genero(2L, "Suspenso"),
                        new Genero(3L, "Policial"),
                        new Genero(4L, "Romance")
                )
        );

    }

    @Override
    public List<Genero> obtenerTodos() {
        return this.generos;
    }

    @Override
    public Genero crear(Genero genero) {

        genero.setId(this.generos.size()+1L);

        this.generos.add(genero);

        return genero;

    }

    @Override
    public Genero actualizar(Long id, Genero genero) {

        if(GeneroUtil.validarQueExistaPorId(this.generos, id)){

            this.generos.stream()
                    .filter(g -> g.getId().equals(id)).findFirst().get().setNombre(genero.getNombre());

        } else {

            throw new IllegalArgumentException("No existe genero con ese ID.");

        }

        return genero;

    }

    @Override
    public Optional<Genero> buscarPorNombre(String nombre) {

        return this.generos.stream()
                .filter(g -> g.getNombre().equalsIgnoreCase(nombre)).findFirst();

    }

}
