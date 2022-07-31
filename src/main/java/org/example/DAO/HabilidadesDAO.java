package org.example.DAO;

import org.example.model.Habilidades;
import org.example.util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabilidadesDAO extends DataAccessObject<Habilidades> {

    private static final String INSERT = "INSERT INTO habilidades (nombre, porcentaje) VALUES (?,?)";

    private static final String GET_ONE = "SELECT id, nombre, porcentaje FROM habilidades WHERE id=?";
    public HabilidadesDAO(Connection connection) {
        super(connection);
    }

    private static final String UPDATE = "UPDATE habilidades SET nombre = ?, porcentaje = ? " +
            " WHERE id = ?";

    private static final String DELETE = "DELETE FROM habilidades WHERE id = ?";

    private static final String GET_ALL = "SELECT id, nombre, porcentaje FROM habilidades";

    @Override
    public Habilidades findById(long id) {
        Habilidades habilidades = new Habilidades();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE);){
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                habilidades.setId(rs.getLong("id"));
                habilidades.setNombre(rs.getString("nombre"));
                habilidades.setPorcentaje(rs.getInt("porcentaje"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return habilidades;
    }



    @Override
    public List<Habilidades> findAll() {

        List<Habilidades> list = new ArrayList<Habilidades>();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ALL);){
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Habilidades habilidades = new Habilidades();
                habilidades.setId(rs.getLong("id"));
                habilidades.setNombre(rs.getString("nombre"));
                habilidades.setPorcentaje(rs.getInt("porcentaje"));
                list.add(habilidades);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }


    @Override
    public Habilidades update(Habilidades dto) {
        Habilidades habilidades = null;
        try(PreparedStatement statement = this.connection.prepareStatement(UPDATE);){
            statement.setString(1, dto.getNombre());
            statement.setInt(2, dto.getPorcentaje());
            statement.setLong(3, dto.getId());
            statement.execute();
            habilidades = this.findById(dto.getId());
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return habilidades;
    }

    @Override
    public Habilidades create(Habilidades dto) {
        try(PreparedStatement statement = this.connection.prepareStatement(INSERT);){
            statement.setString(1, dto.getNombre());
            statement.setInt(2, dto.getPorcentaje());

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
