package lambdafunction.service;


import com.google.gson.Gson;
import lambdafunction.modelo.*;
import lambdafunction.repository.PersitenciaDao;
import lambdafunction.utilidades.ClienteSecretsManager;
import lambdafunction.utilidades.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Comparator;
import java.util.List;

import static lambdafunction.utilidades.Constantes.*;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private PersitenciaDao persitenciaDao;

    public ResponseEntity<?> consumeApi(InputOrdenar desc) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseCountries respuestaGeneral = new ResponseCountries("Internal Server Erro", null);
        UserCredentials userCredentials = new Gson().fromJson(ClienteSecretsManager.getSecret(), UserCredentials.class);

        ResponseEntity<List<CountryInfo>> conuntry = RestTemplateUtil.restTemplatePlantilla(userCredentials.getEndpointRest(), HttpMethod.GET, null, CountryInfo[].class);

        if (conuntry.getStatusCode().is2xxSuccessful()) {
            List<CountryInfo> countryInfos = conuntry.getBody();
            if (desc.getOrdenar()) {
                countryInfos.sort(Comparator.comparingLong(CountryInfo::getPopulation)
                        .thenComparing(countryInfo -> countryInfo.getName().getCommon()).reversed());
            }
            respuestaGeneral.setMensaje("Successful");
            respuestaGeneral.setCountryInfos(countryInfos);
            httpStatus = conuntry.getStatusCode();
        }
        return new ResponseEntity<>(respuestaGeneral, httpStatus);
    }

    public ResponseEntity<?> crear(InputUsuario inputUsuario) throws InputUsuario.FormatoCorreoInvalidoException {
        try {
            inputUsuario.validarDatos();
            inputUsuario.validarCorreo();
            inputUsuario.validarPassword();
            UserCredentials userCredentials = new Gson().fromJson(ClienteSecretsManager.getSecret(), UserCredentials.class);
            Boolean respuesta = persitenciaDao.crearUsuarios(inputUsuario, userCredentials);
            return new ResponseEntity<>(respuesta ? "Usuario creado correctamente" : "Usuario ya existe", respuesta ? HttpStatus.CREATED : HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (IllegalArgumentException e) {
            // Captura la excepción de campos vacíos
            return ResponseEntity.badRequest().body(OBLIGATORIOS);
        } catch (InputUsuario.FormatoCorreoInvalidoException e) {
            // Captura la excepción de formato de correo electrónico inválido
            return ResponseEntity.badRequest().body(CORREO + e.getMessage());
        } catch (InputUsuario.ContraseniaInvalidaException e) {
            // Captura la excepción de campos vacíos
            return ResponseEntity.badRequest().body(PASSWORD);
        }

    }

    public ResponseEntity<?> update(InputUsuario inputUsuario) throws InputUsuario.FormatoCorreoInvalidoException {
        try {
            inputUsuario.validarUnDato();
            UserCredentials userCredentials = new Gson().fromJson(ClienteSecretsManager.getSecret(), UserCredentials.class);
            Boolean respuesta = persitenciaDao.updateUsuarios(inputUsuario, userCredentials);
            return new ResponseEntity<>(respuesta ? "Usuario actulizado correctamente" : "No fue actualizado", respuesta ? HttpStatus.CREATED : HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(NOOBLIGATORIOS);
        } catch (InputUsuario.FormatoCorreoInvalidoException e) {
            return ResponseEntity.badRequest().body(CORREO + e.getMessage());
        } catch (InputUsuario.ContraseniaInvalidaException e) {
            return ResponseEntity.badRequest().body(PASSWORD);
        }

    }

    public ResponseEntity<?> delete(InputUsuario inputUsuario) throws InputUsuario.FormatoCorreoInvalidoException {
        try {
            if(inputUsuario.getId() == null){
                throw new IllegalArgumentException(IDNECESARIO);
            }
            UserCredentials userCredentials = new Gson().fromJson(ClienteSecretsManager.getSecret(), UserCredentials.class);
            Boolean respuesta = persitenciaDao.deleteUsuarios(inputUsuario, userCredentials);
            return new ResponseEntity<>(respuesta ? "Usuario borrado correctamente" : "No fue borrado", respuesta ? HttpStatus.CREATED : HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(IDNECESARIO);
        }

    }

    public ResponseEntity<?> validar(InputUsuario inputUsuario)  {

        try {
            inputUsuario.validarCorreo();
            UserCredentials userCredentials = new Gson().fromJson(ClienteSecretsManager.getSecret(), UserCredentials.class);
            Boolean respuesta = persitenciaDao.validar(inputUsuario, userCredentials);
            //String token= JwtUtil.generateToken(inputUsuario.getCorreo(), userCredentials.getToken(),2l);
            ResponseToken responseToken = new ResponseToken();
            responseToken.setToken(respuesta ? "validado" : "No fue posible validar datos");
            return new ResponseEntity<>( responseToken , respuesta ? HttpStatus.OK : HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(NOOBLIGATORIOS);
        } catch (InputUsuario.FormatoCorreoInvalidoException e) {
            return ResponseEntity.badRequest().body(CORREO + e.getMessage());
        }
    }
}
