package ar.com.api.disneychallenge.disneychallenge.models.request;

import java.util.*;

import ar.com.api.disneychallenge.disneychallenge.entities.*;

public class EdiMoviRequest {
    public String imagenpeli;
    public String titulo;
    public Date fechaDeCreacion;
    public Integer calificacion;
    public Set<Personaje> personajes;
    public Integer generoId;

    
}
