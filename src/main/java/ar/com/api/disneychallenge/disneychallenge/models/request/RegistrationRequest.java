package ar.com.api.disneychallenge.disneychallenge.models.request;

import java.util.Date;

import ar.com.api.disneychallenge.disneychallenge.entities.Usuario.TipoUsuarioEnum;
import ar.com.api.disneychallenge.disneychallenge.entities.Pais.TipoDocuEnum;
public class RegistrationRequest {

    public String fullName; // Nombre persona
    public int country; // pais del usuario
    public TipoDocuEnum identificationType; // Tipo Documento
    public String identification; // nro documento
    public Date birthDate; // fechaNacimiento
    public String email; // email
    public TipoUsuarioEnum userType;
    public String password; // contraseña elegida por el usuario.
}