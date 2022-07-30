package org.example;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCExecutor {

    public static void main(String... args){
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "portfolio", "root", "");
        try{
            Connection connection = dcm.getConnection();
            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
            Usuario usuario = new Usuario();
            usuario.setUsername("LucasLenz");
            usuario.setContrasena("portfolio1234");
            Usuario dbUsuario = usuarioDAO.create(usuario);
            System.out.println(dbUsuario);
//            dbCustomer = customerDAO.findById(dbCustomer.getaccountId());
//            System.out.println(dbCustomer);
//
//            dbCustomer = customerDAO.update(dbCustomer);
//            System.out.println(dbCustomer);
//            customerDAO.delete(dbCustomer.getaccountId());




        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

