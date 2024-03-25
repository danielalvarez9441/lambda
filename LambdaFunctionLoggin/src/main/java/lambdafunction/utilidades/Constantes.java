package lambdafunction.utilidades;

import software.amazon.awssdk.services.secretsmanager.endpoints.internal.Value;

public class Constantes {
    public static String OBLIGATORIOS = "Todos los campos son obligatorios";
    public static String NOOBLIGATORIOS = "Al menos uno de los campos debe ser proporcionado";

    public static String PASSWORD = "La contraseña no cumple con los requisitos mínimos: debe contener al menos una letra mayúscula, un número, un caracter especial y al menos 8 caracteres";

    public static String CORREO = "Formato de correo electrónico inválido:";

    public static String IDNECESARIO = "Al menos uno de los campos debe ser proporcionado";
}
