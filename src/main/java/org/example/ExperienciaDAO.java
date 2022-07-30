package org.example;

import org.example.util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExperienciaDAO extends DataAccessObject<Experiencia> {

    private static final String INSERT = "INSERT INTO experiencia (puesto, fechaIng, fechaEg, logo, descripcion) VALUES (?,?,?,?,?)";

    private static final String GET_ONE = "SELECT id,puesto, fechaIng, fechaEg, logo, descripcion FROM experiencia WHERE id=?";

    public ExperienciaDAO(Connection connection) {
        super(connection);
    }

    private static final String UPDATE = "UPDATE experiencia SET puesto = ?,  " +
            "fechaIng = ?, fechaEg = ?, logo = ?, descripcion = ? WHERE id = ?";

    private static final String DELETE = "DELETE FROM experiencia WHERE id = ?";

    private static final String GET_ALL = "SELECT id,puesto, fechaIng, fechaEg, logo, descripcion FROM experiencia";

    @Override
    public  Experiencia findById(long expid) {
        Experiencia experiencia = new Experiencia();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE);){
            statement.setLong(1, expid);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                experiencia.setId(rs.getLong("id"));
                experiencia.setPuesto(rs.getString("puesto"));
                experiencia.setFechaIng(rs.getString("fechaIng"));
                experiencia.setFechaEg(rs.getString("fechaEg"));
                experiencia.setLogo(rs.getString("logo"));
                experiencia.setDescripcion(rs.getString("descripcion"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return experiencia;
    }



    @Override
    public List<Experiencia> findAll() {
        List<Experiencia> list = new ArrayList<Experiencia>();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ALL);){
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Experiencia experiencia = new Experiencia();
                experiencia.setId(rs.getLong("id"));
                experiencia.setPuesto(rs.getString("puesto"));
                experiencia.setFechaIng(rs.getString("fechaIng"));
                experiencia.setFechaEg(rs.getString("fechaEg"));
                experiencia.setLogo(rs.getString("logo"));
                experiencia.setDescripcion(rs.getString("descripcion"));
                list.add(experiencia);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Experiencia update(Experiencia dto) {
        Experiencia experiencia = null;
        try(PreparedStatement statement = this.connection.prepareStatement(UPDATE);){
            statement.setString(1, dto.getPuesto());
            statement.setString(2, dto.getFechaIng());
            statement.setString(3, dto.getFechaEg());
            statement.setString(4, dto.getLogo());
            statement.setString(5, dto.getDescripcion());
            statement.setLong(6, dto.getId());
            statement.execute();
            experiencia = this.findById(dto.getId());
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return experiencia;
    }

    @Override
    public Experiencia create(Experiencia dto) {
        try(PreparedStatement statement = this.connection.prepareStatement(INSERT);){
            statement.setString(1, dto.getPuesto());
            statement.setString(2, dto.getFechaIng());
            statement.setString(3, dto.getFechaEg());
            statement.setString(4, dto.getLogo());
            statement.setString(5, dto.getDescripcion());

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
