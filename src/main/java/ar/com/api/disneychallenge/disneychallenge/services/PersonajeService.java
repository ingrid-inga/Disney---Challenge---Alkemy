package ar.com.api.disneychallenge.disneychallenge.services;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.api.disneychallenge.disneychallenge.entities.*;
import ar.com.api.disneychallenge.disneychallenge.models.response.CharacterResponse;
import ar.com.api.disneychallenge.disneychallenge.repos.*;

@Service
public class PersonajeService {

    @Autowired
    private PersonajeRepository repository;

    @Autowired
    private PeliculaRepository pelirepo;

    @Autowired
    private PeliculaService peliculaService;

    public Personaje crear(String imagen, String nombre) {

        Personaje personaje = new Personaje();
        personaje.setNombre(imagen);
        personaje.setImagen(nombre);

        repository.save(personaje);

        return personaje;
    }

    public void crear(Personaje personaje) {
        repository.save(personaje);
    }

    public List<String> traerPersonajesByString() {

        return this.traerPersonajes().stream().map(per -> per.getNombre()).collect(Collectors.toList());
    }

    public List<String> traerPersonajesByTwo() {

        return this.traerPersonajes().stream().map(per -> per.getImagen()).collect(Collectors.toList());
    }

    public List<Personaje> traerPersonajes() {
        return repository.findAll();
    }

    public Personaje traerById(Integer personajeId) {
        // pelicula.agregarPersonaje(personajeId);
        return repository.findByPersonajeId(personajeId);
    }

    public void eliminarById(Integer personajeId) {
        repository.deleteById(personajeId);
    }

    public void editarById(Personaje p) {
        repository.save(p);
    }

    public List<Personaje> traerPersonajesByA(String imagen, String nombre) {
        return repository.findAll();
    }

    public List<Integer> traerPersonajesByEdad() {
        return this.traerPersonajes().stream().map(per -> per.getEdad()).collect(Collectors.toList());
    }

    public List<Integer> traerPersonajesByPeso() {
        return this.traerPersonajes().stream().map(per -> per.getPeso()).collect(Collectors.toList());
    }

    public Set<Personaje> pByPeliculaId(Integer peliculaId) {
        return buscarPorId(peliculaId).getPersonajes();
    }

    public Pelicula buscarPorId(Integer peliculaId) {
        Optional<Pelicula> opPelicula = pelirepo.findById(peliculaId);

        if (opPelicula.isPresent())
            return opPelicula.get();
        else
            return null;
    }

}