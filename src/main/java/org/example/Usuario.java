package org.example;

import org.example.util.DataTransferObject;

public class Usuario implements DataTransferObject {
    private long id;

    private String username;

    private String contrasena;

    public Usuario() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasena() {
        return contrasena;
    }


    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", usuario ='" + username + '\'' +
                ", contrasena='" + contrasena + '\'' +

                '}';
    }
}
