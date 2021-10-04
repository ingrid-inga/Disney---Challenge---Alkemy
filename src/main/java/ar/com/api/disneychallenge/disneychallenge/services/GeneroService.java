package ar.com.api.disneychallenge.disneychallenge.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.api.disneychallenge.disneychallenge.entities.Genero;
import ar.com.api.disneychallenge.disneychallenge.repos.GeneroRepository;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository repository;

    @Autowired
    private PeliculaService PeliculaService;

    public boolean crearGenero(Genero genero) {
        if (existe(genero.getNombre()))
            return false;
        repository.save(genero);
        return true;
    }

    private boolean existe(String nombre) {
        Genero genero = repository.findByNombre(nombre);
        return genero != null;
    }

    public List<Genero> traerGeneros() {
        return repository.findAll();
    }

    public Genero traerById(Integer generoId) {
        return repository.findByGeneroId(generoId);
    }

}