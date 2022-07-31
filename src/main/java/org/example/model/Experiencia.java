package org.example.model;

import org.example.util.DataTransferObject;

public class Experiencia implements DataTransferObject {

    private long id;

    private String puesto;


    private String fechaIng;

    private String fechaEg;

    private String logo;

    private String descripcion;

    public Experiencia() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getFechaIng() {
        return fechaIng;
    }

    public void setFechaIng(String fechaIng) {
        this.fechaIng = fechaIng;
    }

    public String getFechaEg() {
        return fechaEg;
    }

    public void setFechaEg(String fechaEg) {
        this.fechaEg = fechaEg;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Experiencia{" +
                "id=" + id +
                ", puesto='" + puesto + '\'' +
                ", fechaIng='" + fechaIng + '\'' +
                ", fechaEg='" + fechaEg + '\'' +
                ", logo='" + logo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
