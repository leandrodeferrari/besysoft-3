package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.domain.Personaje;
import com.besysoft.bootcamp.service.IPersonajeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personajes")
public class PersonajeController {

    private final IPersonajeService personajeService;

    public PersonajeController(IPersonajeService personajeService) {
        this.personajeService = personajeService;
    }

    @GetMapping
    public ResponseEntity<?> buscarPorFiltros(@RequestParam(required = false) String nombre,
                                              @RequestParam(required = false) Byte edad){

        try {
            return ResponseEntity.ok(this.personajeService.buscarPorFiltros(nombre, edad));
        } catch(RuntimeException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @GetMapping("/edades")
    public ResponseEntity<?> buscarPorEdades(@RequestParam Byte desde,
                                             @RequestParam Byte hasta){

        try {
            return ResponseEntity.ok(this.personajeService.buscarPorEdades(desde, hasta));
        }catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Personaje personaje){

        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(this.personajeService.crear(personaje));

        } catch (IllegalArgumentException ex){

            return ResponseEntity.badRequest().body(ex.getMessage());

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Personaje personaje){

        try {

            return ResponseEntity.ok(this.personajeService.actualizar(id, personaje));

        } catch (IllegalArgumentException ex){

            return ResponseEntity.badRequest().body(ex.getMessage());

        }

    }

}
