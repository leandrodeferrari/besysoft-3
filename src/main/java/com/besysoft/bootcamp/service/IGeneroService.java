package com.besysoft.bootcamp.service;

import com.besysoft.bootcamp.domain.Genero;

import java.util.List;
import java.util.Optional;

public interface IGeneroService {

    List<Genero> obtenerTodos();
    Genero crear(Genero genero);
    Genero actualizar(Long id, Genero genero);
    Optional<Genero> buscarPorNombre(String nombre);

}
