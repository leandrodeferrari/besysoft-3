package com.besysoft.bootcamp.service;

import com.besysoft.bootcamp.domain.PeliculaSerie;

import java.util.List;

public interface IPeliculaSerieService {

    List<PeliculaSerie> buscarPorFiltros(String titulo, String nombreGenero);
    List<PeliculaSerie> buscarPorFechas(String desde, String hasta);
    List<PeliculaSerie> buscarPorCalificaciones(Byte desde, Byte hasta);
    PeliculaSerie crear(PeliculaSerie peliculaSerie);
    PeliculaSerie actualizar(Long id, PeliculaSerie peliculaSerie);

}
