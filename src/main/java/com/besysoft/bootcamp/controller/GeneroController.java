package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.util.GeneroUtil;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    private List<Genero> generos;

    public GeneroController() {

        this.generos = new ArrayList<>(
                Arrays.asList(
                        new Genero(1L, "Terror"),
                        new Genero(2L, "Suspenso"),
                        new Genero(3L, "Policial"),
                        new Genero(4L, "Romance")
                )
        );

    }

    /* No lo pide el ejercicio, pero lo hice para verificar el endpoint de crear Genero */
    @GetMapping
    public ResponseEntity<List<Genero>> obtenerTodos(){
        return ResponseEntity.ok(this.generos);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Genero genero){

        try {

            GeneroUtil.validarNombre(genero.getNombre());
            genero.setId(this.generos.size()+1L);

            this.generos.add(genero);

            return ResponseEntity.status(HttpStatus.CREATED).body(genero);

        } catch (IllegalArgumentException ex){

            return ResponseEntity.badRequest().body(ex.getMessage());

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Genero genero){

        try {

            ValidacionGeneralUtil.validarId(id);
            GeneroUtil.validarNombre(genero.getNombre());
            genero.setId(id);

            if(GeneroUtil.validarQueExistaPorId(this.generos, id)){

                this.generos.stream()
                        .filter(g -> g.getId().equals(id)).findFirst().get().setNombre(genero.getNombre());

            } else {

                throw new IllegalArgumentException("No existe genero con ese ID.");

            }

            return ResponseEntity.ok(genero);

        } catch (IllegalArgumentException ex){

            return ResponseEntity.badRequest().body(ex.getMessage());

        }

    }

}
