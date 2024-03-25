package lambdafunction.modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseGeneral {
    private String mensaje;

    public ResponseGeneral(String mensaje) {
        this.mensaje = mensaje;
    }
}
