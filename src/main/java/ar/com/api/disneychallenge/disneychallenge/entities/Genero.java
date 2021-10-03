package ar.com.api.disneychallenge.disneychallenge.entities;

import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "genero")

public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genero_id")
    private Integer generoId;

    private String nombre;

    @Column(name = "imageng")
    private String imagenG;

    @JsonIgnore
    @OneToMany(mappedBy = "genero", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pelicula> peliculas = new ArrayList<>();

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public Integer getGeneroId() {
        return generoId;
    }

    public void setGeneroId(Integer generoId) {
        this.generoId = generoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagenG() {
        return imagenG;
    }

    public void setImagenG(String imagenG) {
        this.imagenG = imagenG;
    }

    public void agregarPelicula(Pelicula pelicula) {
        this.peliculas.add(pelicula);
        pelicula.setGenero(this);
    }

}
