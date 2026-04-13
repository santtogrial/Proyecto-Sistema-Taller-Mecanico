package com.taller.model;

public class ItemTrabajo {
    private String nombre;
    private double monto;
    private String tipo;

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public ItemTrabajo(String nombre, double monto){
        this.nombre = nombre;
        this.monto = monto;
    }
    
}
