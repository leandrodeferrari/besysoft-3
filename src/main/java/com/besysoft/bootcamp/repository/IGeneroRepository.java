package com.besysoft.bootcamp.repository;

import com.besysoft.bootcamp.domain.Genero;

import java.util.List;
import java.util.Optional;

public interface IGeneroRepository {

    List<Genero> obtenerTodos();
    Genero crear(Genero genero);
    Genero actualizar(Long id, Genero genero);
    Optional<Genero> buscarPorNombre(String nombre);
    boolean existePorId(Long id);
    boolean existePorNombre(String nombre);

}
