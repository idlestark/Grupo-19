package main;

import java.util.ArrayList;

public class Procesador {

    private String id;
    private String codigo;
    private boolean refrigerado;
    private Integer anio;
    //AGREGO NUEVO
    private ArrayList<Tarea> listaTareas;

    public Procesador(String id, String codigo, boolean refrigerado, Integer anio) {
        this.id = id;
        this.codigo = codigo;
        this.refrigerado = refrigerado;
        this.anio = anio;
        this.listaTareas = new ArrayList<>();
    }
    /*EMPIEZAN METODOS NUEVOS*/

    public int getTiempo(){
        int tiempoProcesador = 0;

        for(Tarea t: listaTareas){
            tiempoProcesador+=t.getTiempo();
        }

        return tiempoProcesador;
    }

    public ArrayList<Tarea> getTareas(){return listaTareas;}

    public void asignarTarea(Tarea t){
        if(!listaTareas.contains(t)){
            listaTareas.add(t);
        }
    }

    public void vaciarTareas(){
        this.listaTareas.clear();
    }

    /*TERMINAN METODOS NUEVOS*/

    public String getId() {return id;}

    public void setId(String id) {
        this.id = id;
    }

    public boolean isRefrigerado() {return refrigerado;}

    public void setRefrigerado(boolean refrigerado) {
        this.refrigerado = refrigerado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

}
