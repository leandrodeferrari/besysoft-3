package com.besysoft.bootcamp.repository;

import com.besysoft.bootcamp.domain.Personaje;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonajeRepository {

    List<Personaje> buscarPorFiltros(String nombre, Byte edad);
    List<Personaje> buscarPorEdades(Byte desde, Byte hasta);
    Personaje crear(Personaje personaje);
    Personaje actualizar(Long id, Personaje personaje);
    boolean existePorId(Long id);

}
