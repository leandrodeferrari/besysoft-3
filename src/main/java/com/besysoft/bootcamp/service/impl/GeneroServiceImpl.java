package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.repository.IGeneroRepository;
import com.besysoft.bootcamp.service.IGeneroService;
import com.besysoft.bootcamp.util.GeneroUtil;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroServiceImpl implements IGeneroService {

    private final IGeneroRepository generoRepository;

    public GeneroServiceImpl(IGeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @Override
    public List<Genero> obtenerTodos() {
        return this.generoRepository.obtenerTodos();
    }

    @Override
    public Genero crear(Genero genero) {

        GeneroUtil.validarNombre(genero.getNombre());

        Optional<Genero> optionalGenero = buscarPorNombre(genero.getNombre());

        if(optionalGenero.isEmpty()){
            return this.generoRepository.crear(genero);
        } else {
            throw new IllegalArgumentException("El genero ya existe.");
        }

    }

    @Override
    public Genero actualizar(Long id, Genero genero) {

        ValidacionGeneralUtil.validarId(id);
        GeneroUtil.validarNombre(genero.getNombre());
        genero.setId(id);

        return this.generoRepository.actualizar(id, genero);

    }

    @Override
    public  Optional<Genero> buscarPorNombre(String nombre) {
        return this.generoRepository.buscarPorNombre(nombre);
    }

}
