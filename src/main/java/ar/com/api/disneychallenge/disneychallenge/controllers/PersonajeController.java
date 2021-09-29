package ar.com.api.disneychallenge.disneychallenge.controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ar.com.api.disneychallenge.disneychallenge.entities.Personaje;
import ar.com.api.disneychallenge.disneychallenge.models.request.EdicionRequest;
import ar.com.api.disneychallenge.disneychallenge.models.response.*;
import ar.com.api.disneychallenge.disneychallenge.services.*;

@RestController
public class PersonajeController {

    @Autowired
    PersonajeService service;

    @Autowired
    PeliculaService peliculaService;

    @PostMapping("/api/characters")
    public ResponseEntity<GenericResponse> crearPersonaje(@RequestBody Personaje personaje) {
        GenericResponse r = new GenericResponse();

        service.crear(personaje);

        r.id = personaje.getPersonajeId();
        r.isOk = true;
        r.message = "Personaje creado con éxito";

        return ResponseEntity.ok(r);
    }

    @PutMapping("/characters/{id}")
    public ResponseEntity<GenericResponse> editar(@PathVariable("id") Integer id, @RequestBody EdicionRequest eR) {
        GenericResponse r = new GenericResponse();

        Personaje p = service.traerById(id);

        if (p == null) {
            r.isOk = false;
            r.message = "El id ingresado no existe";
            return ResponseEntity.badRequest().body(r);
        } else {

            p.setImagen(eR.imagen);
            p.setNombre(eR.nombre);
            p.setEdad(eR.edad);
            p.setPeso(eR.peso);
            p.setHistoria(eR.historia);
            p.setPeliculas(eR.peliculas);

            service.editarById(p);

            r.isOk = true;
            r.message = "Edición del personaje realizada con éxito";
            return ResponseEntity.ok(r);
        }
    }

    @GetMapping("/characters")
    public ResponseEntity<List<CharacterResponse>> traerPersonajes() {

        List<Personaje> p = service.traerPersonajes();

        List<CharacterResponse> lista = new ArrayList<>();
        for (Personaje personaje : p) {
            CharacterResponse cR = new CharacterResponse();
            cR.imagen = personaje.getImagen();
            cR.nombre = personaje.getNombre();
            lista.add(cR);
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping(value = "/characters/nombre")
    public ResponseEntity<List<String>> traerpersonajesByString() {
        // String result = String.format("name=%s", new String[]{nombre});
        return ResponseEntity.ok(service.traerPersonajesByString());
    }

    @GetMapping("/characters/imagen")
    public ResponseEntity<List<String>> traerpersonajesByTwo() {
        return ResponseEntity.ok(service.traerPersonajesByTwo());
    }

    @GetMapping("/characters/edad")
    public ResponseEntity<List<Integer>> traerpersonajesByEdad() {
        return ResponseEntity.ok(service.traerPersonajesByEdad());
    }

    @GetMapping("/characters/peso")
    public ResponseEntity<List<Integer>> traerpersonajesByPeso() {
        return ResponseEntity.ok(service.traerPersonajesByPeso());
    }

    @GetMapping("/characters/{id}")
    public ResponseEntity<?> traerById(@PathVariable Integer id) {
        GenericResponse r = new GenericResponse();

        if (service.traerById(id) == null) {
            r.isOk = false;
            r.message = "El id ingresado no existe";
            return ResponseEntity.badRequest().body(r);
        } else {

        }
        return ResponseEntity.ok(service.traerById(id));

    }

    @GetMapping("/characters/movies/{peliculaId}")
    public ResponseEntity<Set<Personaje>> pByPeliculaId(@PathVariable Integer peliculaId) {
        return ResponseEntity.ok(service.pByPeliculaId(peliculaId));
    }

    @DeleteMapping("/characters/{id}")
    public ResponseEntity<?> eliminarById(@PathVariable Integer id) {
        GenericResponse r = new GenericResponse();

        if (service.traerById(id) == null) {
            r.isOk = false;
            r.message = "El id ingresado no existe";
            return ResponseEntity.badRequest().body(r);

        } else {

            service.eliminarById(id);
            r.isOk = true;
            r.message = "El personaje ha sido eliminado";
            return ResponseEntity.ok(r);

        }
    }
}