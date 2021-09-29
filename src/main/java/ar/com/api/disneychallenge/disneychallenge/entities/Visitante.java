package ar.com.api.disneychallenge.disneychallenge.entities;

import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "visitante")
public class Visitante extends Persona {

    @Id
    @Column(name = "visitante_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer visitanteId;

    @OneToOne(mappedBy = "visitante", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Usuario usuario;

    public Integer getVisitanteId() {
        return visitanteId;
    }

    public void setVisitanteId(Integer visitanteId) {
        this.visitanteId = visitanteId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        usuario.setVisitante(this);
    }

}