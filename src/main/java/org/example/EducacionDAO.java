package org.example;

import org.example.util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EducacionDAO extends DataAccessObject<Educacion> {

    private static final String INSERT = "INSERT INTO educacion (institucion, carrera, fechaIng, fechaEg, logo) VALUES (?,?,?,?,?)";

    private static final String GET_ONE = "SELECT id, institucion, carrera, fechaIng, fechaEg, logo FROM educacion WHERE id=?";

    public EducacionDAO(Connection connection) {
        super(connection);
    }

    private static final String UPDATE = "UPDATE educacion SET institucion = ?, carrera = ?, " +
            "fechaIng = ?, fechaEg = ?, logo = ? WHERE id = ?";

    private static final String DELETE = "DELETE FROM educacion WHERE id = ?";

    private static final String GET_ALL = "SELECT id, institucion, carrera, fechaIng, fechaEg, logo FROM educacion";

    @Override
    public Educacion findById(long id) {
        Educacion educacion = new Educacion();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE);){
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                educacion.setId(rs.getLong("id"));
                educacion.setInstitucion(rs.getString("institucion"));
                educacion.setCarrera(rs.getString("carrera"));
                educacion.setFechaIng(rs.getString("fechaIng"));
                educacion.setFechaEg(rs.getString("fechaEg"));
                educacion.setLogo(rs.getString("logo"));

            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return educacion;
    }



    @Override
    public List<Educacion> findAll() {
        List<Educacion> list = new ArrayList<Educacion>();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ALL);){
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Educacion educacion = new Educacion();
                educacion.setId(rs.getLong("id"));
                educacion.setInstitucion(rs.getString("institucion"));
                educacion.setCarrera(rs.getString("carrera"));
                educacion.setFechaIng(rs.getString("fechaIng"));
                educacion.setFechaEg(rs.getString("fechaEg"));
                educacion.setLogo(rs.getString("logo"));
                list.add(educacion);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Educacion update(Educacion dto) {
        Educacion educacion = null;
        try(PreparedStatement statement = this.connection.prepareStatement(UPDATE);){
            statement.setString(1, dto.getInstitucion());
            statement.setString(2, dto.getCarrera());
            statement.setString(3, dto.getFechaIng());
            statement.setString(4, dto.getFechaEg());
            statement.setString(5, dto.getLogo());
            statement.setLong(6, dto.getId());
            statement.execute();
            educacion = this.findById(dto.getId());
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return educacion;
    }

    @Override
    public Educacion create(Educacion dto) {
        try(PreparedStatement statement = this.connection.prepareStatement(INSERT);){
            statement.setString(1, dto.getInstitucion());
            statement.setString(2, dto.getCarrera());
            statement.setString(3, dto.getFechaIng());
            statement.setString(4, dto.getFechaEg());
            statement.setString(5, dto.getLogo());

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
