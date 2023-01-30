package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.domain.PeliculaSerie;
import com.besysoft.bootcamp.service.IPeliculaSerieService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/peliculas-series")
public class PeliculaSerieController {

    private final IPeliculaSerieService peliculaSerieService;

    public PeliculaSerieController(IPeliculaSerieService peliculaSerieService) {
        this.peliculaSerieService = peliculaSerieService;
    }

    @GetMapping
    public ResponseEntity<?> buscarPorFiltros(@RequestParam(required = false) String titulo,
                                             @RequestParam(required = false) String nombreGenero){

        try {
            return ResponseEntity.ok
                    (this.peliculaSerieService.buscarPorFiltros(titulo, nombreGenero));
        } catch(RuntimeException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @GetMapping("/fechas")
    public ResponseEntity<?> buscarPorFechas(@RequestParam String desde,
                                             @RequestParam String hasta){

        try {
            return ResponseEntity.ok(this.peliculaSerieService.buscarPorFechas(desde, hasta));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @GetMapping("/calificaciones")
    public ResponseEntity<?> buscarPorCalificaciones(@RequestParam Byte desde,
                                                     @RequestParam Byte hasta){

        try {
            return ResponseEntity.ok(this.peliculaSerieService.buscarPorCalificaciones(desde, hasta));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody PeliculaSerie peliculaSerie){

        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(this.peliculaSerieService.crear(peliculaSerie));

        } catch (IllegalArgumentException ex){

            return ResponseEntity.badRequest().body(ex.getMessage());

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody PeliculaSerie peliculaSerie){

        try {

            return ResponseEntity.ok(this.peliculaSerieService.actualizar(id, peliculaSerie));

        } catch (IllegalArgumentException ex){

            return ResponseEntity.badRequest().body(ex.getMessage());

        }

    }

}
