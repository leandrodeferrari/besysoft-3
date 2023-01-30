package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.domain.PeliculaSerie;
import com.besysoft.bootcamp.repository.IPeliculaSerieRepository;
import com.besysoft.bootcamp.service.IGeneroService;
import com.besysoft.bootcamp.service.IPeliculaSerieService;
import com.besysoft.bootcamp.util.FechaUtil;
import com.besysoft.bootcamp.util.PeliculaSerieUtil;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaSerieServiceImpl implements IPeliculaSerieService {

    private final IGeneroService generoService;
    private final IPeliculaSerieRepository peliculaSerieRepository;

    public PeliculaSerieServiceImpl(IGeneroService generoService,
                                    IPeliculaSerieRepository peliculaSerieRepository) {
        this.generoService = generoService;
        this.peliculaSerieRepository = peliculaSerieRepository;
    }

    @Override
    public List<PeliculaSerie> buscarPorFiltros(String titulo, String nombreGenero) {
        return this.peliculaSerieRepository.buscarPorFiltros(titulo, nombreGenero);
    }

    @Override
    public List<PeliculaSerie> buscarPorFechas(String desde, String hasta) {

        LocalDate fechaInicio = FechaUtil.formatear(desde);
        LocalDate fechaFinal = FechaUtil.formatear(hasta);
        FechaUtil.validarRango(fechaInicio, fechaFinal);

        return this.peliculaSerieRepository.buscarPorFechas(fechaInicio, fechaFinal);

    }

    @Override
    public List<PeliculaSerie> buscarPorCalificaciones(Byte desde, Byte hasta) {

        PeliculaSerieUtil.validarCalificacion(desde);
        PeliculaSerieUtil.validarCalificacion(hasta);
        ValidacionGeneralUtil.validarRangoDeNumeros(desde, hasta);

        return this.peliculaSerieRepository.buscarPorCalificaciones(desde, hasta);

    }

    @Override
    public PeliculaSerie crear(PeliculaSerie peliculaSerie) {

        PeliculaSerieUtil.validar(peliculaSerie);

        if(this.peliculaSerieRepository.existePorTitulo(peliculaSerie.getTitulo())){
            throw new IllegalArgumentException("La pelicula/serie ya existe.");
        }

        Optional<Genero> optionalGenero = this.generoService.buscarPorNombre(peliculaSerie.getGenero().getNombre());

        if(optionalGenero.isPresent()){
            peliculaSerie.setGenero(optionalGenero.get());
        } else {
            throw new IllegalArgumentException("No existe genero con ese nombre.");
        }

        return this.peliculaSerieRepository.crear(peliculaSerie);

    }

    @Override
    public PeliculaSerie actualizar(Long id, PeliculaSerie peliculaSerie) {

        ValidacionGeneralUtil.validarId(id);
        PeliculaSerieUtil.validar(peliculaSerie);

        if(this.peliculaSerieRepository.existePorTitulo(peliculaSerie.getTitulo())){
            throw new IllegalArgumentException("Ya existe una pelicula/serie con ese título.");
        }

        Optional<Genero> optionalGenero = this.generoService.buscarPorNombre(peliculaSerie.getGenero().getNombre());

        if(optionalGenero.isPresent()){
            peliculaSerie.setGenero(optionalGenero.get());
        } else {
            throw new IllegalArgumentException("No existe genero con ese nombre.");
        }

        peliculaSerie.setId(id);

        return this.peliculaSerieRepository.actualizar(id, peliculaSerie);

    }

}
