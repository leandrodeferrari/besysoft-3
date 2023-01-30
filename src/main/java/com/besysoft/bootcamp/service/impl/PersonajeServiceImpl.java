package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.Personaje;
import com.besysoft.bootcamp.repository.IPersonajeRepository;
import com.besysoft.bootcamp.service.IPersonajeService;
import com.besysoft.bootcamp.util.PersonajeUtil;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonajeServiceImpl implements IPersonajeService {

    private final IPersonajeRepository personajeRepository;

    public PersonajeServiceImpl(IPersonajeRepository personajeRepository) {
        this.personajeRepository = personajeRepository;
    }

    @Override
    public List<Personaje> buscarPorFiltros(String nombre, Byte edad) {
        return this.personajeRepository.buscarPorFiltros(nombre, edad);
    }

    @Override
    public List<Personaje> buscarPorEdades(Byte desde, Byte hasta) {

        PersonajeUtil.validarEdad(desde);
        PersonajeUtil.validarEdad(hasta);
        ValidacionGeneralUtil.validarRangoDeNumeros(desde, hasta);

        return this.personajeRepository.buscarPorEdades(desde, hasta);

    }

    @Override
    public Personaje crear(Personaje personaje) {

        PersonajeUtil.validar(personaje);

        return this.personajeRepository.crear(personaje);

    }

    @Override
    public Personaje actualizar(Long id, Personaje personaje) {

        ValidacionGeneralUtil.validarId(id);
        PersonajeUtil.validar(personaje);
        personaje.setId(id);

        return this.personajeRepository.actualizar(id, personaje);

    }

}
