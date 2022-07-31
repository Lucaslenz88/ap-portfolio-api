package org.example.model;

import org.example.util.DataTransferObject;

public class Habilidades implements DataTransferObject {
    private long id;

    private String nombre;

    private int porcentaje;

    public Habilidades() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
        return "Habilidades{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", porcentaje=" + porcentaje +
                '}';
    }
}
