package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.domain.Personaje;
import com.besysoft.bootcamp.util.PersonajeUtil;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/personajes")
public class PersonajeController {

    private List<Personaje> personajes;

    public PersonajeController() {

        this.personajes = new ArrayList<>(
                Arrays.asList(
                        new Personaje(1L, "Jacqueline", (byte) 26, 55.7f, "Es una actriz canadiense. Protagonizó la serie Salvation de CBS."),
                        new Personaje(2L, "Vera", (byte) 86, 70.0f, "Supermodelo que enamoró a Coco Chanel y ahora ha conquistado a Paco Plaza."),
                        new Personaje(3L, "Christian", (byte) 35, 79.5f, "Es actor, escritor, director, productor y músico. Trabaja en el teatro, peliculas y televisión."),
                        new Personaje(4L, "Joel", (byte) 48, 90.2f, "Es actor, director y guionista australiano conocido por haber participado en la serie televisiva Teh secret life of us."),
                        new Personaje(5L, "Sofia", (byte) 29, 69.5f, "Nació en Lauderdale, Florida. Hijan de José F. Daccarett y de Laura Char Canson."),
                        new Personaje(6L, "Jeremy", (byte) 52, 80.5f, "Es actor, actor de voz, productor y músico estadounidense.")
                )
        );

    }

    @GetMapping
    public ResponseEntity<?> buscarPorFiltros(@RequestParam(required = false) String nombre,
                                              @RequestParam(required = false) Byte edad){

        try {
            return ResponseEntity.ok(PersonajeUtil.buscarPorFiltros(this.personajes, nombre, edad));
        } catch(RuntimeException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @GetMapping("/edades")
    public ResponseEntity<?> buscarPorEdades(@RequestParam Byte desde,
                                             @RequestParam Byte hasta){

        try {
            return ResponseEntity.ok(PersonajeUtil.buscarPorEdades(this.personajes, desde, hasta));
        }catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Personaje personaje){

        try {

            PersonajeUtil.validar(personaje);
            personaje.setId(this.personajes.size()+1L);

            this.personajes.add(personaje);

            return ResponseEntity.status(HttpStatus.CREATED).body(personaje);

        } catch (IllegalArgumentException ex){

            return ResponseEntity.badRequest().body(ex.getMessage());

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Personaje personaje){

        try {

            ValidacionGeneralUtil.validarId(id);
            PersonajeUtil.validar(personaje);
            personaje.setId(id);

            if(PersonajeUtil.validarQueExistaPorId(this.personajes, id)){

                for (Personaje p : this.personajes) {

                    if(p.getId().equals(id)){

                        p.setEdad(personaje.getEdad());
                        p.setNombre(personaje.getNombre());
                        p.setPeso(personaje.getPeso());
                        p.setHistoria(personaje.getHistoria());

                    }

                }

            } else {

                throw new IllegalArgumentException("No existe personaje con ese ID.");

            }

            return ResponseEntity.ok(personaje);

        } catch (IllegalArgumentException ex){

            return ResponseEntity.badRequest().body(ex.getMessage());

        }

    }

}
