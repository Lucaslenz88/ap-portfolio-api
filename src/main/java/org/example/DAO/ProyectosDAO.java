package org.example.DAO;

import org.example.model.Proyectos;
import org.example.util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProyectosDAO extends DataAccessObject<Proyectos> {

    private static final String INSERT = "INSERT INTO proyectos (nombre, fecha, descripcion, imagen, link) VALUES (?,?,?,?,?)";

    private static final String GET_ONE = "SELECT id, nombre, fecha, descripcion, imagen, link FROM proyectos WHERE id=?";

    public ProyectosDAO(Connection connection) {
        super(connection);
    }

    private static final String UPDATE = "UPDATE proyectos SET nombre = ?,  " +
            "fecha = ?, descripcion = ?, imagen = ?, link = ? WHERE id = ?";

    private static final String DELETE = "DELETE FROM proyectos WHERE id = ?";

    private static final String GET_ALL = "SELECT id, nombre,  fecha, descripcion, imagen, link FROM proyectos";

    @Override
    public  Proyectos findById(long id) {
        Proyectos proyectos = new Proyectos();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE);){
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                proyectos.setId(rs.getLong("id"));
                proyectos.setNombre(rs.getString("nombre"));

                proyectos.setFecha(rs.getString("fecha"));
                proyectos.setDescripcion(rs.getString("descripcion"));
                proyectos.setImagen(rs.getString("imagen"));
                proyectos.setLink(rs.getString("link"));


            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return proyectos;
    }



    @Override
    public List<Proyectos> findAll() {
        List<Proyectos> list = new ArrayList<Proyectos>();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ALL);){
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Proyectos proyectos = new Proyectos();
                proyectos.setId(rs.getLong("id"));
                proyectos.setNombre(rs.getString("nombre"));

                proyectos.setFecha(rs.getString("fecha"));
                proyectos.setDescripcion(rs.getString("descripcion"));
                proyectos.setImagen(rs.getString("imagen"));
                proyectos.setLink(rs.getString("link"));
                list.add(proyectos);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Proyectos update(Proyectos dto) {
        Proyectos proyectos = null;
        try(PreparedStatement statement = this.connection.prepareStatement(UPDATE);){
            statement.setString(1, dto.getNombre());
            statement.setString(2, dto.getFecha());
            statement.setString(3, dto.getDescripcion());
            statement.setString(4, dto.getImagen());
            statement.setString(5, dto.getLink());
            statement.setLong(6, dto.getId());
            statement.execute();
            proyectos = this.findById(dto.getId());
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return proyectos;
    }

    @Override
    public Proyectos create(Proyectos dto) {
        try(PreparedStatement statement = this.connection.prepareStatement(INSERT);){
            statement.setString(1, dto.getNombre());
            statement.setString(2, dto.getFecha());
            statement.setString(3, dto.getDescripcion());
            statement.setString(4, dto.getImagen());
            statement.setString(5, dto.getLink());

            statement.execute();
            long id = this.getLastVal(USUARIO_SEQUENCE);
            return this.findById(id);
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try(PreparedStatement statement = this.connection.prepareStatement(DELETE);){
            statement.setLong(1, id);
            statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
