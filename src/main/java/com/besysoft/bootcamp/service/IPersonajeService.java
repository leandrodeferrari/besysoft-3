package com.besysoft.bootcamp.service;

import com.besysoft.bootcamp.domain.Personaje;

import java.util.List;

public interface IPersonajeService {

    List<Personaje> buscarPorFiltros(String nombre, Byte edad);
    List<Personaje> buscarPorEdades(Byte desde, Byte hasta);
    Personaje crear(Personaje personaje);
    Personaje actualizar(Long id, Personaje personaje);

}
