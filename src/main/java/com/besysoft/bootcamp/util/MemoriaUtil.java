package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.domain.PeliculaSerie;
import com.besysoft.bootcamp.domain.Personaje;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class MemoriaUtil {

    public static List<Genero> generarGeneros(){

        List<Genero> generos = new ArrayList<>();

        Genero genero1 = new Genero(1L, "Terror");
        Genero genero2 = new Genero(2L, "Suspenso");
        Genero genero3 = new Genero(3L, "Policial");
        Genero genero4 = new Genero(4L, "Romance");

        generos.add(genero1);
        generos.add(genero2);
        generos.add(genero3);
        generos.add(genero4);

        return generos;

    }

    public static List<PeliculaSerie> generarPeliculasSeries(List<Genero> generos){

        List<PeliculaSerie> peliculasSeries = new ArrayList<>();

        PeliculaSerie peliculaSerie1 = new PeliculaSerie
                (1L, "Chucky", LocalDate.now(), (byte) 4, generos.get(0));
        PeliculaSerie peliculaSerie2 = new PeliculaSerie
                (2L, "Annabelle", LocalDate.now(), (byte) 3, generos.get(0));
        PeliculaSerie peliculaSerie3 = new PeliculaSerie
                (3L, "Jaula", LocalDate.now(), (byte) 4, generos.get(1));
        PeliculaSerie peliculaSerie4 = new PeliculaSerie
                (4L, "Culpable", LocalDate.now(), (byte) 2, generos.get(2));
        PeliculaSerie peliculaSerie5 = new PeliculaSerie
                (5L, "Viejos", LocalDate.now(), (byte) 5, generos.get(1));
        PeliculaSerie peliculaSerie6 = new PeliculaSerie
                (6L, "CODA", LocalDate.now(), (byte) 1, generos.get(3));

        peliculasSeries.add(peliculaSerie1);
        peliculasSeries.add(peliculaSerie2);
        peliculasSeries.add(peliculaSerie3);
        peliculasSeries.add(peliculaSerie4);
        peliculasSeries.add(peliculaSerie5);
        peliculasSeries.add(peliculaSerie6);

        return peliculasSeries;

    }

    public static List<Personaje> generarPersonajes(){

        List<Personaje> personajes = new ArrayList<>();

        Personaje personaje1 = new Personaje
                (1L, "Jacqueline", (byte) 26, 55.7f, "Es una actriz canadiense. Protagonizó la serie Salvation de CBS.");
        Personaje personaje2 = new Personaje
                (2L, "Vera", (byte) 86, 70.0f, "Supermodelo que enamoró a Coco Chanel y ahora ha conquistado a Paco Plaza.");
        Personaje personaje3 = new Personaje
                (3L, "Christian", (byte) 35, 79.5f, "Es actor, escritor, director, productor y músico. Trabaja en el teatro, peliculas y televisión.");
        Personaje personaje4 = new Personaje
                (4L, "Joel", (byte) 48, 90.2f, "Es actor, director y guionista australiano conocido por haber participado en la serie televisiva Teh secret life of us.");
        Personaje personaje5 = new Personaje
                (5L, "Sofia", (byte) 29, 69.5f, "Nació en Lauderdale, Florida. Hijan de José F. Daccarett y de Laura Char Canson.");
        Personaje personaje6 = new Personaje
                (6L, "Jeremy", (byte) 52, 80.5f, "Es actor, actor de voz, productor y músico estadounidense.");

        personajes.add(personaje1);
        personajes.add(personaje2);
        personajes.add(personaje3);
        personajes.add(personaje4);
        personajes.add(personaje5);
        personajes.add(personaje6);

        return personajes;

    }

}
