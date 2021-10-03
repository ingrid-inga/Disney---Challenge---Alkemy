package ar.com.api.disneychallenge.disneychallenge.services;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.api.disneychallenge.disneychallenge.entities.*;
import ar.com.api.disneychallenge.disneychallenge.repos.*;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository repository;

    @Autowired
    private GeneroRepository generoRepo;
    
    @Autowired
    private PersonajeService service;

	public boolean crearPelicula(Pelicula p) {
        if(existe(p.getTitulo()))
        return false;
        repository.save(p);
        return true;
	}

    public boolean existe(String titulo) {
        Pelicula pelicula = repository.findByTitulo(titulo);
        return pelicula != null;
    }

    public List<Pelicula> traerPeliculas() {
        return repository.findAll();
    }

    public boolean genero(Genero genero) {
        Pelicula pelicula = repository.findByGenero(genero);
        return  genero!= null;
    }

    public List<Pelicula> mByGeneroId(Integer generoId) {

        return buscarPorId(generoId).getPeliculas();
    }


    public Genero buscarPorId(Integer generoId) {
        Optional<Genero> opGenero = generoRepo.findById(generoId);

        if (opGenero.isPresent())
            return opGenero.get();
        else
        return null;
    }

    public Pelicula traerById(Integer peliculaId) {
        return repository.findByPeliculaId(peliculaId);
    }

    public void eliminarById(Integer id) {
        repository.deleteById(id);
    }

    public void editarById(Pelicula p) {
        repository.save(p);
    }

    public List<String> obtenerTitulos() {
        return this.traerPeliculas().stream().map(cat ->cat.getTitulo()).collect(Collectors.toList());
    }

	public List<Pelicula> moviesByOrder() {
	return this.traerPeliculas().stream().sorted(Comparator.comparing(Pelicula::getFechaDeCreacion).reversed()).collect(Collectors.toList());
    }

    public List<Pelicula> moviesByOrderasc() {
	return this.traerPeliculas().stream().sorted(Comparator.comparing(Pelicula::getFechaDeCreacion)).collect(Collectors.toList());
    }

    public Pelicula findByTitulo(String titulo) {
        return repository.findByTitulo(titulo);
    }

	/*public List<Pelicula> findByName(String name) {
        return repository.findByTitulo(titulo);
	}*/

   /* public Pelicula findByName(String name){
		//List<Pelicula> lista = repository.findAll();
		
		return repository.findByName(name);
        //lista.stream().filter((p)->p.getTitulo().equals(name)).collect(Collectors.toList());
		
	} */
}
