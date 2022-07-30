package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/response")

public class ApplicationController {

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
    @GetMapping("/experiencias")
    public List<Experiencia> getExperiencias() {

        try{
            DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                    "portfolio", "root", "");
            Connection connection = dcm.getConnection();
            ExperienciaDAO experienciaDAO = new ExperienciaDAO(connection);
            List<Experiencia> experiencias = experienciaDAO.findAll();
            return experiencias;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @CrossOrigin
    @PostMapping("/experiencia")
    public ResponseEntity<?> add(@RequestBody Experiencia experiencia){
        Map<String, Object> mensajeBody = new HashMap<>();

        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "portfolio", "root", "");

        try{
            Connection connection = dcm.getConnection();

           ExperienciaDAO experienciaDAO = new ExperienciaDAO(connection);
           Experiencia dbExperiencia = experienciaDAO.create(experiencia);

            mensajeBody.put("estado", Boolean.TRUE);
            mensajeBody.put("datos", experiencia);
            return new ResponseEntity<>(mensajeBody, HttpStatus.CREATED);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

//    @CrossOrigin
//    @DeleteMapping("/dlExp/")
//    public ResponseEntity<?> deleteExp(@PathVariable Long id){
//        Map<String, Object> mensajeBody = new HashMap<>();
//        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
//                "portfolio", "root", "");
//
//        try{
//            Connection connection = dcm.getConnection();
//            ExperienciaDAO experienciaDAO = new ExperienciaDAO(connection);
//            Experiencia dbExperiencia = experienciaDAO.findById(id);
//            experienciaDAO.delete(dbExperiencia.getId());
//            mensajeBody.put("estado", Boolean.TRUE);
//            return new ResponseEntity<>(mensajeBody, HttpStatus.OK);
//
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
//        return null;
//    }

    @CrossOrigin
    @GetMapping("/educaciones")
    public List<Educacion> getEducaciones() {

        try{
            DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                    "portfolio", "root", "");
            Connection connection = dcm.getConnection();
            EducacionDAO educacionDAO = new EducacionDAO(connection);
            List<Educacion> educaciones = educacionDAO.findAll();
            return educaciones;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    @CrossOrigin
    @GetMapping("/proyectos")
    public List<Proyectos> getProyectos() {

        try{
            DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                    "portfolio", "root", "");
            Connection connection = dcm.getConnection();
            ProyectosDAO proyectosDAO = new ProyectosDAO(connection);
            List<Proyectos> proyectos = proyectosDAO.findAll();
            return proyectos;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin
    @PutMapping("/experienciaUpdate")
    public ResponseEntity<?> update(@RequestBody Experiencia experiencia){
        Map<String, Object> mensajeBody = new HashMap<>();

        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "portfolio", "root", "");

        try{
            Connection connection = dcm.getConnection();
            ExperienciaDAO experienciaDAO = new ExperienciaDAO(connection);
            Experiencia dbExperiencia = experienciaDAO.update(experiencia);
            mensajeBody.put("estado", Boolean.TRUE);
            mensajeBody.put("datos", experiencia);
            return new ResponseEntity<>(mensajeBody, HttpStatus.OK);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin
    @PostMapping("/educacion")
    public ResponseEntity<?> addEd(@RequestBody Educacion educacion){
        Map<String, Object> mensajeBody = new HashMap<>();

        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "portfolio", "root", "");

        try{
            Connection connection = dcm.getConnection();

            EducacionDAO educacionDAO = new EducacionDAO(connection);
           Educacion dbEducacion = educacionDAO.create(educacion);

            mensajeBody.put("estado", Boolean.TRUE);
            mensajeBody.put("datos", educacion);
            return new ResponseEntity<>(mensajeBody, HttpStatus.CREATED);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin
    @PutMapping("/educacionUpdate")
    public ResponseEntity<?> updateEd(@RequestBody Educacion educacion){
        Map<String, Object> mensajeBody = new HashMap<>();

        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "portfolio", "root", "");

        try{
            Connection connection = dcm.getConnection();
            EducacionDAO educacionDAO = new EducacionDAO(connection);
            Educacion dbEducacion = educacionDAO.update(educacion);
            mensajeBody.put("estado", Boolean.TRUE);
            mensajeBody.put("datos", educacion);
            return new ResponseEntity<>(mensajeBody, HttpStatus.OK);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin
    @PostMapping("/proyecto")
    public ResponseEntity<?> addProyecto(@RequestBody Proyectos proyectos){
        Map<String, Object> mensajeBody = new HashMap<>();

        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "portfolio", "root", "");

        try{
            Connection connection = dcm.getConnection();

            ProyectosDAO proyectosDAO = new ProyectosDAO(connection);
            Proyectos dbProyectos = proyectosDAO.create(proyectos);

            mensajeBody.put("estado", Boolean.TRUE);
            mensajeBody.put("datos", proyectos);
            return new ResponseEntity<>(mensajeBody, HttpStatus.CREATED);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin
    @PutMapping("/proyectoUpdate")
    public ResponseEntity<?> updatePro(@RequestBody Proyectos proyectos){
        Map<String, Object> mensajeBody = new HashMap<>();

        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "portfolio", "root", "");

        try{
            Connection connection = dcm.getConnection();
            ProyectosDAO proyectosDAO = new ProyectosDAO(connection);
            Proyectos dbProyectos = proyectosDAO.update(proyectos);
            mensajeBody.put("estado", Boolean.TRUE);
            mensajeBody.put("datos", proyectos);
            return new ResponseEntity<>(mensajeBody, HttpStatus.OK);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin
    @GetMapping("/habilidades")
    public List<Habilidades> getHabilidades() {

        try{
            DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                    "portfolio", "root", "");
            Connection connection = dcm.getConnection();
            HabilidadesDAO habilidadesDAO = new HabilidadesDAO(connection);
            List<Habilidades> habilidades = habilidadesDAO.findAll();
            return habilidades;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin
    @PostMapping("/habilidades")
    public ResponseEntity<?> addHabilidad(@RequestBody Habilidades habilidades){
        Map<String, Object> mensajeBody = new HashMap<>();

        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "portfolio", "root", "");

        try{
            Connection connection = dcm.getConnection();

            HabilidadesDAO habilidadesDAO = new HabilidadesDAO(connection);
            Habilidades dbHabilidades = habilidadesDAO.create(habilidades);

            mensajeBody.put("estado", Boolean.TRUE);
            mensajeBody.put("datos", habilidades);
            return new ResponseEntity<>(mensajeBody, HttpStatus.CREATED);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin
    @PutMapping("/habilidadUpdate")
    public ResponseEntity<?> updatehab(@RequestBody Habilidades habilidades){
        Map<String, Object> mensajeBody = new HashMap<>();

        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "portfolio", "root", "");

        try{
            Connection connection = dcm.getConnection();
            HabilidadesDAO habilidadesDAO = new HabilidadesDAO(connection);
            Habilidades dbHabilidades = habilidadesDAO.update(habilidades);
            mensajeBody.put("estado", Boolean.TRUE);
            mensajeBody.put("datos", habilidades);
            return new ResponseEntity<>(mensajeBody, HttpStatus.OK);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }



}
