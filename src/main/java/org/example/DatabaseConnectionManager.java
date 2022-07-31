package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private final String url;
    private final Properties properties;

   public DatabaseConnectionManager(String host, String databaseName,
                                     String username, String password){
        this.url = "jdbc:mysql://"+host+"/"+databaseName;
        this.properties = new Properties();
        this.properties.setProperty("user", username);
        this.properties.setProperty("password", password);


    }

    public DatabaseConnectionManager(String host, String databaseName,
                                     String username, String password, String port){
//        this.url = "jdbc:mysql://"+host+"/"+databaseName;
        this.url = "jdbc:mysql://"+"ujehqablm9ytytri:mYEFSr3NAaDKXMRZ7q6N@bfrk7qvzp2jjvtgiz55k-mysql.services.clever-cloud.com:3306/bfrk7qvzp2jjvtgiz55k";
        this.properties = new Properties();
        this.properties.setProperty("MYSQL_ADDON_USER", username);
        this.properties.setProperty("MYSQL_ADDON_PASSWORD", password);
        this.properties.setProperty("MYSQL_ADDON_PORT", port);

    }

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(this.url, this.properties);
    }
}
