package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.PeliculaSerie;

import java.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;

public class PeliculaSerieUtil {

    public static List<PeliculaSerie> buscarPorFiltros(List<PeliculaSerie> peliculasSeries,
                                                       String titulo, String nombreGenero){

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

    public static List<PeliculaSerie> buscarPorFechas(List<PeliculaSerie> peliculasSeries, String desde, String hasta){

        LocalDate fechaInicio = FechaUtil.formatear(desde);
        LocalDate fechaFinal = FechaUtil.formatear(hasta);
        FechaUtil.validarRango(fechaInicio, fechaFinal);

        return peliculasSeries.stream()
                .filter(ps -> ps.getFechaDeCreacion().isAfter(fechaInicio.minusDays(1)) && ps.getFechaDeCreacion().isBefore(fechaFinal.plusDays(1)))
                .collect(Collectors.toList());

    }

    public static List<PeliculaSerie> buscarPorCalificaciones(List<PeliculaSerie> peliculasSeries, Byte desde, Byte hasta){

        validarCalificacion(desde);
        validarCalificacion(hasta);
        ValidacionGeneralUtil.validarRangoDeNumeros(desde, hasta);

        return peliculasSeries.stream()
                .filter(ps -> ps.getCalificacion() >= desde && ps.getCalificacion()<= hasta)
                .collect(Collectors.toList());

    }

    public static void validar(PeliculaSerie peliculaSerie){

        validarTitulo(peliculaSerie.getTitulo());
        validarCalificacion(peliculaSerie.getCalificacion());
        FechaUtil.validar(peliculaSerie.getFechaDeCreacion());

    }

    private static void validarCalificacion(Byte calificacion){

        if(calificacion == null){
            throw new IllegalArgumentException("La calificación no puede ser nula.");
        }

        if(calificacion < 1 || calificacion > 5){
            throw new IllegalArgumentException("La calificación no puede ser menor a 1 o mayor a 5.");
        }

    }

    private static void validarTitulo(String titulo){

        if(titulo.isBlank()){
            throw new IllegalArgumentException("El título no puede ser nulo o vacío.");
        }

    }

    public static boolean validarQueExistaPorId(List<PeliculaSerie> peliculasSeries, Long id){
        return peliculasSeries.stream().anyMatch(g -> g.getId().equals(id));
    }

}
