package ar.com.api.disneychallenge.disneychallenge.controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ar.com.api.disneychallenge.disneychallenge.entities.Genero;
import ar.com.api.disneychallenge.disneychallenge.entities.Pelicula;
import ar.com.api.disneychallenge.disneychallenge.models.request.EdiMoviRequest;
import ar.com.api.disneychallenge.disneychallenge.models.response.GenericResponse;
import ar.com.api.disneychallenge.disneychallenge.models.response.MovieResponse;
import ar.com.api.disneychallenge.disneychallenge.services.GeneroService;
import ar.com.api.disneychallenge.disneychallenge.services.PeliculaService;

@RestController
public class PeliculaController {

    @Autowired
    PeliculaService service;

    @Autowired
    GeneroService generoService;

    @PostMapping("/api/movies")
    public ResponseEntity<?> crearPelicula(@RequestBody EdiMoviRequest mR) {   
        GenericResponse r = new GenericResponse();

        Pelicula p = new Pelicula();
        p.setImagenpeli(mR.imagenpeli);
        p.setTitulo(mR.titulo);
        p.setFechaDeCreacion(mR.fechaDeCreacion);
        p.setCalificacion(mR.calificacion);
        p.setPersonajes(mR.personajes);
        
        Genero genero = generoService.traerById(mR.generoId);
        genero.agregarPelicula(p);


        if (service.crearPelicula(p)) {
            r.id = p.getPeliculaId();
            r.isOk = true;
            r.message = "Película creada con éxito";
            return ResponseEntity.ok(r);
        } else {
            r.isOk = false;
            r.message = "La película ya ha sido creada";
            return ResponseEntity.badRequest().body(r);
        }
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<GenericResponse> editar(@PathVariable("id") Integer id, @RequestBody EdiMoviRequest mR) {

        GenericResponse r = new GenericResponse();

        Pelicula p = service.traerById(id);

        if (p == null) {
            r.isOk = false;
            r.message = "El id ingresado no existe";
            return ResponseEntity.badRequest().body(r);
        } else {

            p.setImagenpeli(mR.imagenpeli);
            p.setTitulo(mR.titulo);
            p.setFechaDeCreacion(mR.fechaDeCreacion);
            p.setCalificacion(mR.calificacion);
            p.setPersonajes(mR.personajes);
            
            Genero genero = generoService.traerById(mR.generoId);
            genero.agregarPelicula(p);

            service.editarById(p);

            r.isOk = true;
            r.message = "Edición de la película realizada con éxito";
            return ResponseEntity.ok(r);
        }
    }

    @GetMapping("/api/movies")
    public ResponseEntity<List<MovieResponse>> traerPeliculas() {

        List<Pelicula> p = service.traerPeliculas();

        List<MovieResponse> lista = new ArrayList<>();
        for (Pelicula pelicula : p) {
            MovieResponse mR = new MovieResponse();
            mR.imagen = pelicula.getImagenpeli();
            mR.titulo = pelicula.getTitulo();
            mR.fechaDeCreacion = pelicula.getFechaDeCreacion();
            lista.add(mR);
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<?> traerById(@PathVariable Integer id) {
        GenericResponse r = new GenericResponse();

        if (service.traerById(id) == null) {
            r.isOk = false;
            r.message = "El id ingresado no existe";
            return ResponseEntity.badRequest().body(r);
        }
        return ResponseEntity.ok(service.traerById(id));
    }

    @GetMapping("/api/movies/nombres")
    public ResponseEntity<List<String>> obtenerTitulos() {
        return ResponseEntity.ok(service.obtenerTitulos());
    }
  
    @GetMapping("/movies/genre/{generoId}")
    public ResponseEntity<List<Pelicula>> mByGeneroId(@PathVariable Integer generoId) {
        
        return ResponseEntity.ok(service.mByGeneroId(generoId));
    }

  
    @GetMapping("/movies/order/desc")
    public ResponseEntity<List<Pelicula>> moviesByOrder() {

        return ResponseEntity.ok(service.moviesByOrder());
    }

    @GetMapping("/movies/order/asc")
    public ResponseEntity<List<Pelicula>> moviesByOrderasc() {

        return ResponseEntity.ok(service.moviesByOrderasc());
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<?> eliminarById(@PathVariable Integer id) {
        GenericResponse r = new GenericResponse();

        if (service.traerById(id) == null) {
            r.isOk = false;
            r.message = "El id ingresado no existe";
            return ResponseEntity.badRequest().body(r);

        } else {

            service.eliminarById(id);
            r.isOk = true;
            r.message = "La película ha sido eliminada";
            return ResponseEntity.ok(r);

        }
    }


  /*  @GetMapping("/movies/name")
    public ResponseEntity<?>findByName(@RequestParam String name){
        return new ResponseEntity<>(service.findByName(name), HttpStatus.OK);
    }

   /* @GetMapping("/api/movies")
    @ResponseBody
    public String getIdGenero(@RequestParam String genre){
        return "genre: " + genre;
    }*/



}
