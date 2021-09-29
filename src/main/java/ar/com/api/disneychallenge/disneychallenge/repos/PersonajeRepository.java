package ar.com.api.disneychallenge.disneychallenge.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ar.com.api.disneychallenge.disneychallenge.entities.Personaje;

@Repository

public interface PersonajeRepository  extends JpaRepository<Personaje, Integer> {
    
    
    Personaje findByPersonajeId(Integer personajeId);

    void save(Integer id);

    Personaje findByNombre(String nombre);
    Personaje findByEdad(Integer edad);

    @Query("select p from Personaje p where p.nombre = ?1")
    Personaje findNombre(String nombre);

    @Query("select p from Personaje p where p.edad = ?1")
    Personaje findEdad(Integer edad);

    
    
}
