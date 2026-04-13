package com.taller.model;

public class ItemTrabajo {
    private String nombre;
    private double monto;
    private String tipo;

    // Getters y Setters
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public double getMonto(){
        return monto;
    }
    public void setMonto(double monto){
        this.monto = monto;
    }
    
    public String getTipo(){
        return tipo;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    //

    public ItemTrabajo(String nombre, double monto){
        this.nombre = nombre;
        this.monto = monto;
    }
    
}
