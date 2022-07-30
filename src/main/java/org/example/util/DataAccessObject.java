package org.example.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class DataAccessObject <T extends DataTransferObject> {

    protected final Connection connection;
    //protected final static String LAST_VAL = "SELECT next_not_cached_value FROM ";

    protected final static String LAST_VAL = "SELECT LAST_INSERT_ID()";
    protected final static String USUARIO_SEQUENCE = "hp_usuario_seq";

    public DataAccessObject(Connection connection){
        super();
        this.connection = connection;
    }

    public abstract T findById(long accountId);
    public abstract List<T> findAll();
    public abstract T update(T dto);
    public abstract T create(T dto);
    public abstract void delete(long accountId);

    protected int getLastVal(String sequence){
        int key = 0;
        String sql = LAST_VAL; //+ sequence;
        try(Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                key=rs.getInt(1);
            }
            return key;
        }catch (SQLException e ){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
