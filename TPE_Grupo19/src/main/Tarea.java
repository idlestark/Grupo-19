package main;

public class Tarea implements Comparable<Tarea>{

    private String id;
    private String nombre;
    private Integer tiempo; //en segundos
    private boolean critica;
    private Integer prioridad;

    public Tarea (String id, String nombre, Integer tiempo, Boolean critica, Integer prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.critica = critica;
        this.prioridad = prioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTiempo() {
        return this.tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public boolean isCritica() {
        return critica;
    }

    public void setCritica(boolean critica) {
        this.critica = critica;
    }

    public String getId() {
        return id;
    }

    public void setId(Integer String) {
        this.id = id;
    }


    public String toString() {
        return "main.Tarea [id=" + id + ", nombre=" + nombre + ", tiempo=" + tiempo + ", critica=" + critica + ", prioridad="
                + prioridad + "]";
    }

    @Override
    public int compareTo(Tarea o) {
        return this.getId().compareTo(o.getId());
    }

}
