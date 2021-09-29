package ar.com.api.disneychallenge.disneychallenge.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.com.api.disneychallenge.disneychallenge.entities.*;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer>{

    Pelicula findByTitulo(String titulo);

    Pelicula findByPeliculaId(Integer peliculaId);

    Pelicula findByGenero(Genero genero);

  //  List<Pelicula> findByOrderByDateAsc();
    
}
