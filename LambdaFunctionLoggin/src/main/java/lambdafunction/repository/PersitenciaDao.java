package lambdafunction.repository;

import lambdafunction.modelo.InputUsuario;
import lambdafunction.modelo.UserCredentials;
import org.springframework.stereotype.Repository;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class PersitenciaDao {

    public static Boolean crearUsuarios(InputUsuario inputUsuario, UserCredentials userCredentials) {
        Boolean respueta = false;
        Connection connection = null;
        CallableStatement stmt = null;
        try {

            connection = ConexionDao.getConnection(userCredentials.getCadenaConexion(), userCredentials.getUsuario(), userCredentials.getContrasena());


            String storedProcCommand = "{CALL InsertarUsuario(?, ?,?,?)}";
            stmt = connection.prepareCall(storedProcCommand);


            stmt.setString(1, inputUsuario.getNombre());
            stmt.setString(2, inputUsuario.getCorreo());
            stmt.setString(3, inputUsuario.getPassword());
            stmt.setString(4, inputUsuario.getDireccion());


            boolean hasResultSet =  stmt.execute();
            if(hasResultSet){
                try(ResultSet rs = stmt.getResultSet()){
                    while(rs.next()){
                        String message = rs.getString("message");
                        respueta= message.equals("1") ? true: false;
                    }
                }
            }


            System.out.println("Procedimiento almacenado InsertarUsuario ejecutado con éxito");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al preparar/ejecutar el procedimiento almacenado: " + e.getMessage());
        } finally {
            try {

                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                se2.printStackTrace();
            }

            ConexionDao.closeConnection(connection);
        }
        return respueta;
    }

    public static Boolean updateUsuarios(InputUsuario inputUsuario, UserCredentials userCredentials) {
        Boolean respueta = false;
        Connection connection = null;
        CallableStatement stmt = null;
        try {

            connection = ConexionDao.getConnection(userCredentials.getCadenaConexion(), userCredentials.getUsuario(), userCredentials.getContrasena());


            String storedProcCommand = "{CALL ActualizarUsuario(?,?,?,?,?)}";
            stmt = connection.prepareCall(storedProcCommand);

            stmt.setLong(    1, inputUsuario.getId());
            stmt.setString(2, inputUsuario.getNombre());
            stmt.setString(3, inputUsuario.getCorreo());
            stmt.setString(4, inputUsuario.getPassword());
            stmt.setString(5, inputUsuario.getDireccion());


            boolean hasResultSet =  stmt.execute();
            if(hasResultSet){
                try(ResultSet rs = stmt.getResultSet()){
                    while(rs.next()){
                        String message = rs.getString("message");
                        respueta= message.equals("1") ? true: false;
                    }
                }
            }


            System.out.println("Procedimiento almacenado actualizar ejecutado con éxito");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al preparar/ejecutar el procedimiento almacenado: " + e.getMessage());
        } finally {
            try {

                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                se2.printStackTrace();
            }

            ConexionDao.closeConnection(connection);
        }
        return respueta;
    }

    public static Boolean deleteUsuarios(InputUsuario inputUsuario, UserCredentials userCredentials) {
        Boolean respueta = false;
        Connection connection = null;
        CallableStatement stmt = null;
        try {

            connection = ConexionDao.getConnection(userCredentials.getCadenaConexion(), userCredentials.getUsuario(), userCredentials.getContrasena());


            String storedProcCommand = "{CALL BorrarUsuario(?)}";
            stmt = connection.prepareCall(storedProcCommand);

            stmt.setLong(    1, inputUsuario.getId());


            boolean hasResultSet =  stmt.execute();
            if(hasResultSet){
                try(ResultSet rs = stmt.getResultSet()){
                    while(rs.next()){
                        String message = rs.getString("message");
                        respueta= message.equals("1") ? true: false;
                    }
                }
            }


            System.out.println("Procedimiento almacenado borrado ejecutado con éxito");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al preparar/ejecutar el procedimiento almacenado: " + e.getMessage());
        } finally {
            try {

                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                se2.printStackTrace();
            }

            ConexionDao.closeConnection(connection);
        }
        return respueta;
    }

    public static Boolean validar(InputUsuario inputUsuario, UserCredentials userCredentials) {
        Boolean respueta = false;
        Connection connection = null;
        CallableStatement stmt = null;
        try {

            connection = ConexionDao.getConnection(userCredentials.getCadenaConexion(), userCredentials.getUsuario(), userCredentials.getContrasena());


            String storedProcCommand = "{CALL ConsultarUsuario(?,?)}";
            stmt = connection.prepareCall(storedProcCommand);

            stmt.setString(    1, inputUsuario.getCorreo());
            stmt.setString(    2, inputUsuario.getPassword());

            boolean hasResultSet =  stmt.execute();
            if(hasResultSet){
                try(ResultSet rs = stmt.getResultSet()){
                    while(rs.next()){
                        String message = rs.getString("message");
                        respueta= message.equals("1") ? true: false;
                    }
                }
            }


            System.out.println("Procedimiento validar ejecutado con éxito");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al preparar/ejecutar el procedimiento almacenado: " + e.getMessage());
        } finally {
            try {

                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                se2.printStackTrace();
            }

            ConexionDao.closeConnection(connection);
        }
        return respueta;
    }
}
