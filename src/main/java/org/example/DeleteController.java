package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/delete",
        method = RequestMethod.DELETE,
        produces="application/json"
)

public class DeleteController {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

//    @PostMapping("/postbody")
//    public String postBody(@RequestBody String fullName) {
//        return "Hello " + fullName;
//    }

//    @PostMapping("/postbody")
//    public void postBody(@Valid @RequestBody String jsonBody) {
//
//        System.out.println("data:" + jsonBody);
//    }


    @CrossOrigin
    @DeleteMapping("/dlExp/{id}")
    public ResponseEntity<?> deleteExp(@PathVariable Long id){
        Map<String, Object> mensajeBody = new HashMap<>();
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "portfolio", "root", "");

        try{
            Connection connection = dcm.getConnection();
            ExperienciaDAO experienciaDAO = new ExperienciaDAO(connection);
            Experiencia dbExperiencia = experienciaDAO.findById(id);
            experienciaDAO.delete(dbExperiencia.getId());
            mensajeBody.put("estado", Boolean.TRUE);
            return new ResponseEntity<>(mensajeBody, HttpStatus.OK);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin
    @DeleteMapping("/dlEd/{id}")
    public ResponseEntity<?> deleteEd(@PathVariable Long id){
        Map<String, Object> mensajeBody = new HashMap<>();
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "portfolio", "root", "");

        try{
            Connection connection = dcm.getConnection();
            EducacionDAO educacionDAO = new EducacionDAO(connection);
            Educacion dbEducacion = educacionDAO.findById(id);
            educacionDAO.delete(dbEducacion.getId());
            mensajeBody.put("estado", Boolean.TRUE);
            return new ResponseEntity<>(mensajeBody, HttpStatus.OK);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin
    @DeleteMapping("/dlPro/{id}")
    public ResponseEntity<?> deletePro(@PathVariable Long id){
        Map<String, Object> mensajeBody = new HashMap<>();
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "portfolio", "root", "");

        try{
            Connection connection = dcm.getConnection();
            ProyectosDAO proyectosDAO = new ProyectosDAO(connection);
            Proyectos dbProyectos = proyectosDAO.findById(id);
            proyectosDAO.delete(dbProyectos.getId());
            mensajeBody.put("estado", Boolean.TRUE);
            return new ResponseEntity<>(mensajeBody, HttpStatus.OK);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin
    @DeleteMapping("/dlHab/{id}")
    public ResponseEntity<?> deleteHab(@PathVariable Long id){
        Map<String, Object> mensajeBody = new HashMap<>();
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "portfolio", "root", "");

        try{
            Connection connection = dcm.getConnection();
            HabilidadesDAO habilidadesDAO = new HabilidadesDAO(connection);
            Habilidades dbHabilidades = habilidadesDAO.findById(id);
            habilidadesDAO.delete(dbHabilidades.getId());
            mensajeBody.put("estado", Boolean.TRUE);
            return new ResponseEntity<>(mensajeBody, HttpStatus.OK);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }




}

