package ar.com.api.disneychallenge.disneychallenge.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.api.disneychallenge.disneychallenge.entities.Visitante;

@Repository
public interface VisitanteRepository extends JpaRepository<Visitante, Integer> {
    Visitante findByVisitanteId(Integer id);
}
