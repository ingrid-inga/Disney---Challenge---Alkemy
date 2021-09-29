package ar.com.api.disneychallenge.disneychallenge.entities;

import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pelicula")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pelicula_id")
    private Integer peliculaId;

    @Column(name = "imagenpeli_id")
    private String imagenpeli;

    private String titulo;

    @Column(name = "fecha_de_creacion")
    private Date fechaDeCreacion;
    private Integer calificacion;

    @JsonIgnore
    @ManyToMany(mappedBy = "peliculas")
    private Set<Personaje> personajes = new HashSet<Personaje>();

    @ManyToOne
    @JoinColumn(name = "genero_id", referencedColumnName = "genero_id")
    private Genero genero;

    
    public String getImagenpeli() {
        return imagenpeli;
    }
    public void setImagenpeli(String imagenpeli) {
        this.imagenpeli = imagenpeli;
    }
    public Integer getPeliculaId() {
        return peliculaId;
    }
    public void setPeliculaId(Integer peliculaId) {
        this.peliculaId = peliculaId;
    }     
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }
    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }
    public Integer getCalificacion() {
        return calificacion;
    }
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public Set<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(Set<Personaje> personajes) {
        this.personajes = personajes;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
 

    

    
}
