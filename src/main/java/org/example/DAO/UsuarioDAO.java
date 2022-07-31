package org.example.DAO;

import org.example.model.Usuario;
import org.example.util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class UsuarioDAO extends DataAccessObject<Usuario> {



    private static final String INSERT = "INSERT INTO usuario (username, contrasena) VALUES (?,?)";

    private static final String GET_ONE = "SELECT id, username, contrasena FROM usuario WHERE id=?";
    public UsuarioDAO(Connection connection) {
        super(connection);
    }

    private static final String UPDATE = "UPDATE usuario SET username = ?, contrasena = ? " +
            " WHERE id = ?";

    private static final String DELETE = "DELETE FROM usuario WHERE id = ?";

    @Override
    public Usuario findById(long id) {
        Usuario usuario = new Usuario();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE);){
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                usuario.setId(rs.getLong("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setContrasena(rs.getString("contrasena"));


            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return usuario;
    }



    @Override
    public List<Usuario> findAll() {
        return null;
    }

    @Override
    public Usuario update(Usuario dto) {
        Usuario usuario = null;
        try(PreparedStatement statement = this.connection.prepareStatement(UPDATE);){
            statement.setString(1, dto.getUsername());
            statement.setString(2, dto.getContrasena());

            statement.setLong(3, dto.getId());
            statement.execute();
            usuario = this.findById(dto.getId());
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return usuario;
    }

    @Override
    public Usuario create(Usuario dto) {
        try(PreparedStatement statement = this.connection.prepareStatement(INSERT);){
            statement.setString(1, dto.getUsername());
            statement.setString(2, dto.getContrasena());

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