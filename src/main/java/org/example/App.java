package org.example;

import org.example.DAO.UsuarioDAO;
import org.example.controller.DatabaseConnectionManager;
import org.example.model.Usuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@RestController
@RequestMapping(value="hello")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }


    @RequestMapping(value="/{id}",
            method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable("id") long id) {

        try{
            DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                    "portfolio", "root", "");
            Connection connection = dcm.getConnection();
            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
            Usuario usuario = usuarioDAO.findById(id);
            return usuario;
        }catch(SQLException e){
            e.printStackTrace();
        }


        return null;
    }
}

