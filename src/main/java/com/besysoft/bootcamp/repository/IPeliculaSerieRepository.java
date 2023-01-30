package com.besysoft.bootcamp.repository;

import com.besysoft.bootcamp.domain.PeliculaSerie;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;

@Repository
public interface IPeliculaSerieRepository {

    List<PeliculaSerie> buscarPorFiltros(String titulo, String nombreGenero);
    List<PeliculaSerie> buscarPorFechas(LocalDate desde, LocalDate hasta);
    List<PeliculaSerie> buscarPorCalificaciones(Byte desde, Byte hasta);
    PeliculaSerie crear(PeliculaSerie peliculaSerie);
    PeliculaSerie actualizar(Long id, PeliculaSerie peliculaSerie);
    boolean existePorId(Long id);
    boolean existePorTitulo(String titulo);

}
