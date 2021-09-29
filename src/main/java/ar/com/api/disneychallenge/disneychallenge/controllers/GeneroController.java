package ar.com.api.disneychallenge.disneychallenge.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ar.com.api.disneychallenge.disneychallenge.entities.Genero;
import ar.com.api.disneychallenge.disneychallenge.models.response.GenericResponse;
import ar.com.api.disneychallenge.disneychallenge.services.GeneroService;

@RestController

public class GeneroController {

    @Autowired
    GeneroService service;

    @PostMapping("/genres")
    public ResponseEntity<?> crearGenero(@RequestBody Genero genero) {
        GenericResponse r = new GenericResponse();

        if (service.crearGenero(genero)) {
            r.id = genero.getGeneroId();
            r.isOk = true;
            r.message = "Género creado con éxito";
            return ResponseEntity.ok(r);
        } else {
            r.isOk = false;
            r.message = "El Género ya ha sido creado";
            return ResponseEntity.badRequest().body(r);
        }
    }

    @GetMapping("/genres")
    public ResponseEntity<List<Genero>> traerGeneros() {
        return ResponseEntity.ok(service.traerGeneros());
    }

    @GetMapping("/genres/{id}")
    public ResponseEntity<?> traerById(@PathVariable Integer id) {
        GenericResponse r = new GenericResponse();

        if (service.traerById(id) == null) {
            r.isOk = false;
            r.message = "El id ingresado no existe";
            return ResponseEntity.badRequest().body(r);
        }
        return ResponseEntity.ok(service.traerById(id));
    }
}