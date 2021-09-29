package ar.com.api.disneychallenge.disneychallenge.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.api.disneychallenge.disneychallenge.entities.*;
import org.springframework.stereotype.Repository;


@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer>{

    Genero findByNombre(String nombre);

    Genero findByGeneroId(Integer generoId);

}
