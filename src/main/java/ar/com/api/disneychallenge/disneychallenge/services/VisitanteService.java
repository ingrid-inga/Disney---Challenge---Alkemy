package ar.com.api.disneychallenge.disneychallenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.api.disneychallenge.disneychallenge.entities.*;
import ar.com.api.disneychallenge.disneychallenge.repos.VisitanteRepository;

@Service
public class VisitanteService {
    @Autowired
    VisitanteRepository repository;

    public void crearVisitante(Visitante visitante) {
        repository.save(visitante);

    }
    public Visitante buscarPorId(Integer id) {
        return repository.findByVisitanteId(id);
    }

} 