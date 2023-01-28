package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.domain.PeliculaSerie;
import com.besysoft.bootcamp.util.FechaUtil;
import com.besysoft.bootcamp.util.PeliculaSerieUtil;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/peliculas-series")
public class PeliculaSerieController {

    private List<Genero> generos;
    private List<PeliculaSerie> peliculasSeries;

    public PeliculaSerieController() {

        this.generos = new ArrayList<>(
                Arrays.asList(
                        new Genero(1L, "Terror"),
                        new Genero(2L, "Suspenso"),
                        new Genero(3L, "Policial"),
                        new Genero(4L, "Romance")
                )
        );

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

    @GetMapping
    public ResponseEntity<?> buscarPorFiltros(@RequestParam(required = false) String titulo,
                                             @RequestParam(required = false) String nombreGenero){

        try {
            return ResponseEntity.ok
                    (PeliculaSerieUtil.buscarPorFiltros(this.peliculasSeries, titulo, nombreGenero));
        } catch(RuntimeException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @GetMapping("/fechas")
    public ResponseEntity<?> buscarPorFechas(@RequestParam String desde,
                                             @RequestParam String hasta){

        try {
            return ResponseEntity.ok(PeliculaSerieUtil.buscarPorFechas(this.peliculasSeries, desde, hasta));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @GetMapping("/calificaciones")
    public ResponseEntity<?> buscarPorCalificaciones(@RequestParam Byte desde,
                                                     @RequestParam Byte hasta){

        try {
            return ResponseEntity.ok(PeliculaSerieUtil.buscarPorCalificaciones(this.peliculasSeries, desde, hasta));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody PeliculaSerie peliculaSerie){

        try {

            PeliculaSerieUtil.validar(peliculaSerie);
            validarGenero(peliculaSerie);
            peliculaSerie.setId(this.peliculasSeries.size()+1L);

            this.peliculasSeries.add(peliculaSerie);

            return ResponseEntity.status(HttpStatus.CREATED).body(peliculaSerie);

        } catch (IllegalArgumentException ex){

            return ResponseEntity.badRequest().body(ex.getMessage());

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody PeliculaSerie peliculaSerie){

        try {

            ValidacionGeneralUtil.validarId(id);
            PeliculaSerieUtil.validar(peliculaSerie);
            validarGenero(peliculaSerie);
            peliculaSerie.setId(id);

            if(PeliculaSerieUtil.validarQueExistaPorId(this.peliculasSeries, id)){

                for (PeliculaSerie ps : this.peliculasSeries) {

                    if(ps.getId().equals(id)){

                        ps.setTitulo(peliculaSerie.getTitulo());
                        ps.setCalificacion(peliculaSerie.getCalificacion());
                        ps.setFechaDeCreacion(peliculaSerie.getFechaDeCreacion());
                        ps.setGenero(peliculaSerie.getGenero());

                    }

                }

            } else {

                throw new IllegalArgumentException("No existe pelicula/serie con ese ID.");

            }

            return ResponseEntity.ok(peliculaSerie);

        } catch (IllegalArgumentException ex){

            return ResponseEntity.badRequest().body(ex.getMessage());

        }

    }

    private void validarGenero(PeliculaSerie peliculaSerie){

        Optional<Genero> optionalGenero = this.generos.stream()
                .filter(g -> g.getNombre().equalsIgnoreCase(peliculaSerie.getGenero().getNombre())).findFirst();

        if(optionalGenero.isPresent()){

            peliculaSerie.getGenero().setId(optionalGenero.get().getId());

        } else {

            throw new IllegalArgumentException("No existe género con ese nombre.");

        }

    }

}
