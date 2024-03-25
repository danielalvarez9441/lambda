package lambdafunction.modelo;


import lombok.Getter;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static lambdafunction.utilidades.Constantes.*;

@Getter
@Setter
public class InputUsuario {
    private Long id;
    private String nombre;
    private String correo;
    private String password;
    private String direccion;

    public void validarDatos() throws IllegalArgumentException {
        if (this.nombre == null || this.nombre.isEmpty() || this.correo == null || this.correo.isEmpty() ||
                this.password == null || this.password.isEmpty() || this.direccion == null || this.direccion.isEmpty()) {
            throw new IllegalArgumentException(OBLIGATORIOS);
        }
    }

    public void validarUnDato() throws IllegalArgumentException, ContraseniaInvalidaException, FormatoCorreoInvalidoException {
        Boolean password = (this.password == null || this.password.isEmpty());
        Boolean correo = (this.correo == null || this.correo.isEmpty());
        if(this.id == null){
            throw new IllegalArgumentException(NOOBLIGATORIOS);
        }
        if (  (this.nombre == null || this.nombre.isEmpty())
                && correo
                && password
                && (this.direccion == null || this.direccion.isEmpty())) {

            throw new IllegalArgumentException(NOOBLIGATORIOS);
        }
        if (!correo)validarCorreo();
        if (!password) validarPassword();

    }




    public void validarCorreo() throws FormatoCorreoInvalidoException {
        if (this.correo == null || this.correo.isEmpty()) {
            throw new FormatoCorreoInvalidoException("El correo electrónico no puede estar vacío");
        }
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.correo);
        if (!matcher.matches()) {
            throw new FormatoCorreoInvalidoException(CORREO + this.correo);
        }
    }

    public void validarPassword() throws ContraseniaInvalidaException {
        if (this.password == null || this.password.isEmpty()) {
            throw new ContraseniaInvalidaException("La contraseña no puede estar vacía");
        }

        String passwordRegex = "(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,}";

        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(this.password);

        if (!passwordMatcher.matches()) {
            throw new ContraseniaInvalidaException(PASSWORD);
        }
    }

    public class ContraseniaInvalidaException extends Exception {
        public ContraseniaInvalidaException(String message) {
            super(message);
        }
    }

    public class FormatoCorreoInvalidoException extends Exception {
        public FormatoCorreoInvalidoException(String mensaje) {
            super(mensaje);
        }
    }
}
