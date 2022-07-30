package org.example;

import org.example.util.DataTransferObject;

public class Educacion implements DataTransferObject {

    private long id;

    private String institucion;

    private String carrera;

    private String fechaIng;

    private String fechaEg;

    private String logo;

    public Educacion() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
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

    @Override
    public String toString() {
        return "Educacion{" +
                "id=" + id +
                ", institucion='" + institucion + '\'' +
                ", carrera='" + carrera + '\'' +
                ", fechaIng='" + fechaIng + '\'' +
                ", fechaEg='" + fechaEg + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
