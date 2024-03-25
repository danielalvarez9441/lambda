package lambdafunction.modelo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserCredentials {

    @JsonProperty("token")
    private String token;

    @JsonProperty("usuario")
    private String usuario;

    @JsonProperty("contrasena")
    private String contrasena;

    @JsonProperty("endpointRest")
    private String endpointRest;

    @JsonProperty("cadenaConexion")
    private String cadenaConexion;

}
