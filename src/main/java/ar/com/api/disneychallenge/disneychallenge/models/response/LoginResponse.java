package ar.com.api.disneychallenge.disneychallenge.models.response;

import ar.com.api.disneychallenge.disneychallenge.entities.Usuario.TipoUsuarioEnum;

public class LoginResponse {
    public Integer id;
    public String username;
    public String token;
    public String email;
    public TipoUsuarioEnum userType;
    public Integer entityId; // Si es un Pasajero, va el Id de Pasajero, si es staff Id Staff

} 