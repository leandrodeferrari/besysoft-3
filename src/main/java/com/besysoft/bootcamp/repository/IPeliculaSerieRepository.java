package com.besysoft.bootcamp.repository;

import com.besysoft.bootcamp.domain.PeliculaSerie;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPeliculaSerieRepository {

    List<PeliculaSerie> buscarPorFiltros(String titulo, String nombreGenero);
    List<PeliculaSerie> buscarPorFechas(LocalDate desde, LocalDate hasta);
    List<PeliculaSerie> buscarPorCalificaciones(Byte desde, Byte hasta);
    PeliculaSerie crear(PeliculaSerie peliculaSerie);
    PeliculaSerie actualizar(Long id, PeliculaSerie peliculaSerie);
    Optional<PeliculaSerie> buscarPorTitulo(String titulo);
    boolean existePorId(Long id);

}
