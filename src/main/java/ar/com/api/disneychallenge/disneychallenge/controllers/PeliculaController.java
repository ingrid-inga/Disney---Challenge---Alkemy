package ar.com.api.disneychallenge.disneychallenge.controllers;

import java.util.*;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ar.com.api.disneychallenge.disneychallenge.entities.Genero;
import ar.com.api.disneychallenge.disneychallenge.entities.Pelicula;
import ar.com.api.disneychallenge.disneychallenge.models.request.EdiMoviRequest;
import ar.com.api.disneychallenge.disneychallenge.models.response.GenericResponse;
import ar.com.api.disneychallenge.disneychallenge.models.response.MovieResponse;
import ar.com.api.disneychallenge.disneychallenge.services.PeliculaService;

@RestController
public class PeliculaController {

    @Autowired
    PeliculaService service;

    @PostMapping("/api/movies")
    public ResponseEntity<?> crearPelicula(@RequestBody Pelicula pelicula) {
        GenericResponse r = new GenericResponse();

        if (service.crearPelicula(pelicula)) {
            r.id = pelicula.getPeliculaId();
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
            p.setGenero(mR.genero);

            service.editarById(p);

            r.isOk = true;
            r.message = "Edición del personaje realizada con éxito";
            return ResponseEntity.ok(r);
        }
    }

    @GetMapping("/movies")
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

    @GetMapping("/movies/nombres")
    public ResponseEntity<List<String>> obtenerTitulos() {
        return ResponseEntity.ok(service.obtenerTitulos());
    }

    // ?genre=idGenero")
    @GetMapping("/movies/genre/{generoId}")
    public ResponseEntity<List<Pelicula>> mByGeneroId(@PathVariable Integer generoId) {
        return ResponseEntity.ok(service.mByGeneroId(generoId));
    }

    // ?order=ASC | DESC
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

}
