package lambdafunction.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lambdafunction.modelo.*;
import lambdafunction.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private Service service;





    @Operation(summary = "Generates a JWT token for the specified user")
    @PostMapping("/validar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JWT token generated successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> generar(@RequestBody InputUsuario crear) {
         return service.validar(crear);

    }

    @Operation(summary = "Consumes an API to retrieve country information and returns a response")
    @ApiResponse(
            responseCode = "200",
            description = "Successful Operation",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseCountries.class),
                            examples = {
                                    @ExampleObject(value = "{\n" +
                                            "    \"mensaje\": \"Successful\",\n" +
                                            "    \"countryInfos\": [\n" +
                                            "        {\n" +
                                            "            \"name\": {},\n" +
                                            "            \"currencies\": {},\n" +
                                            "            \"capital\": [],\n" +
                                            "            \"population\": 0,\n" +
                                            "            \"continents\": [\n" +
                                            "                \"Antarctica\"\n" +
                                            "            ]\n" +
                                            "        },\n" +
                                            "        {\n" +
                                            "            \"name\": {},\n" +
                                            "            \"currencies\": {},\n" +
                                            "            \"capital\": [],\n" +
                                            "            \"population\": 0,\n" +
                                            "            \"continents\": [\n" +
                                            "                \"Antarctica\"\n" +
                                            "            ]\n" +
                                            "        }\n" +
                                            "    ]\n" +
                                            "}\n")
                            }
                    )
            }
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error.class),
                            examples = {
                                    @ExampleObject(value = "{\"mensaje\":\"Internal Server Error\",\"countryInfos\":[]}")
                            }
                    )
            }
    )
    @PostMapping("/consumeApi")
    public ResponseEntity<?> consumeApi(@RequestBody  InputOrdenar desc) {
        return service.consumeApi(desc);
    }

    @PostMapping("/crearUsuario")
    public ResponseEntity<?> crearUsuario( @RequestBody InputUsuario crear) throws InputUsuario.FormatoCorreoInvalidoException {
        return service.crear(crear);
    }
    @PostMapping("/updateUsuario")
    public ResponseEntity<?> updateUsuario( @RequestBody InputUsuario crear) throws InputUsuario.FormatoCorreoInvalidoException {
        return service.update(crear);
    }
    @PostMapping("/deleteUsuario")
    public ResponseEntity<?> deleteUsuario( @RequestBody InputUsuario crear) throws InputUsuario.FormatoCorreoInvalidoException {
        return service.delete(crear);
    }
}
