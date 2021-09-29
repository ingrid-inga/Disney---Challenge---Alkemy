package ar.com.api.disneychallenge.disneychallenge.models.request;

import java.util.Set;
import ar.com.api.disneychallenge.disneychallenge.entities.Pelicula;

public class EdicionRequest {

    public String nombre;
    public String imagen;
    public Integer edad;
    public Integer peso;
    public String historia;
    public Set<Pelicula> peliculas;
    
}
