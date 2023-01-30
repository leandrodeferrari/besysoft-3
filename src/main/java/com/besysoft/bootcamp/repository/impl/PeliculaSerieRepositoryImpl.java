package com.besysoft.bootcamp.repository.impl;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.domain.PeliculaSerie;
import com.besysoft.bootcamp.repository.IGeneroRepository;
import com.besysoft.bootcamp.repository.IPeliculaSerieRepository;
import com.besysoft.bootcamp.util.FechaUtil;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PeliculaSerieRepositoryImpl implements IPeliculaSerieRepository {

    private final IGeneroRepository generoRepository;
    private List<Genero> generos;
    private List<PeliculaSerie> peliculasSeries;

    public PeliculaSerieRepositoryImpl(IGeneroRepository generoRepository) {

        this.generoRepository = generoRepository;
        this.generos = generoRepository.obtenerTodos();

        this.peliculasSeries = new ArrayList<>(
                Arrays.asList(
                        new PeliculaSerie(1L, "Chucky", FechaUtil.formatear("12-12-2022"), (byte) 4, generos.get(0)),
                        new PeliculaSerie(2L, "Annabelle", FechaUtil.formatear("10-01-2020"), (byte) 3, generos.get(0)),
                        new PeliculaSerie(3L, "Jaula", FechaUtil.formatear("11-03-2021"), (byte) 4, generos.get(1)),
                        new PeliculaSerie(4L, "Culpable", FechaUtil.formatear("25-07-2019"), (byte) 2, generos.get(2)),
                        new PeliculaSerie(5L, "Viejos", FechaUtil.formatear("24-01-2023"), (byte) 5, generos.get(1)),
                        new PeliculaSerie(6L, "CODA", FechaUtil.formatear("15-02-2020"), (byte) 1, generos.get(3))
                )
        );

    }

    @Override
    public List<PeliculaSerie> buscarPorFiltros(String titulo, String nombreGenero) {

        if(titulo == null && nombreGenero == null){
            return peliculasSeries;
        }

        if(titulo != null && nombreGenero != null){
            return peliculasSeries.stream()
                    .filter(ps -> ps.getTitulo().equalsIgnoreCase(titulo) &&
                            ps.getGenero().getNombre().equalsIgnoreCase(nombreGenero))
                    .collect(Collectors.toList());
        }

        if(titulo != null){
            return peliculasSeries.stream()
                    .filter(ps -> ps.getTitulo().equalsIgnoreCase(titulo)).collect(Collectors.toList());
        } else {
            return peliculasSeries.stream()
                    .filter(ps -> ps.getGenero().getNombre().equalsIgnoreCase(nombreGenero)).collect(Collectors.toList());
        }

    }

    @Override
    public List<PeliculaSerie> buscarPorFechas(LocalDate desde, LocalDate hasta) {
        return peliculasSeries.stream()
                .filter(ps -> ps.getFechaDeCreacion().isAfter(desde.minusDays(1)) && ps.getFechaDeCreacion().isBefore(hasta.plusDays(1)))
                .collect(Collectors.toList());
    }

    @Override
    public List<PeliculaSerie> buscarPorCalificaciones(Byte desde, Byte hasta) {
        return peliculasSeries.stream()
                .filter(ps -> ps.getCalificacion() >= desde && ps.getCalificacion()<= hasta)
                .collect(Collectors.toList());
    }

    @Override
    public PeliculaSerie crear(PeliculaSerie peliculaSerie) {

        peliculaSerie.setId(this.peliculasSeries.size()+1L);

        this.peliculasSeries.add(peliculaSerie);

        return peliculaSerie;

    }

    @Override
    public PeliculaSerie actualizar(Long id, PeliculaSerie peliculaSerie) {

        if(existePorId(id)){

            this.peliculasSeries.forEach((PeliculaSerie ps) -> {

                if(ps.getId().equals(id)){

                    ps.setTitulo(peliculaSerie.getTitulo());
                    ps.setCalificacion(peliculaSerie.getCalificacion());
                    ps.setFechaDeCreacion(peliculaSerie.getFechaDeCreacion());
                    ps.setGenero(peliculaSerie.getGenero());

                }

            });

            return peliculaSerie;

        } else {

            throw new IllegalArgumentException("No existe pelicula/serie con ese ID.");

        }

    }

    @Override
    public Optional<PeliculaSerie> buscarPorTitulo(String titulo) {
        return this.peliculasSeries.stream().filter(ps -> ps.getTitulo().equalsIgnoreCase(titulo)).findFirst();
    }

    @Override
    public boolean existePorId(Long id) {
        return this.peliculasSeries.stream().anyMatch(g -> g.getId().equals(id));
    }

}
