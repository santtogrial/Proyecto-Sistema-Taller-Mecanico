package com.taller.model;

import com.taller.enums.TipoItem;

public class ItemTrabajo {
    private int id;
    private String nombre;
    private double monto;
    private TipoItem tipo;
    private OrdenDeTrabajo ordenDeTrabajo;

    // Getters y Setters
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

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
    
    public TipoItem getTipo(){
        return tipo;
    }
    public void setTipo(TipoItem tipo){
        this.tipo = tipo;
    }

    public OrdenDeTrabajo getOrdenDeTrabajo(){
        return ordenDeTrabajo;
    }

    public void setOrdenDeTrabajo(OrdenDeTrabajo ordenDeTrabajo){
        this.ordenDeTrabajo = ordenDeTrabajo;
    }
    //

    public ItemTrabajo(String nombre, double monto, OrdenDeTrabajo ordenDeTrabajo){
        this.nombre = nombre;
        this.monto = monto;
        this.ordenDeTrabajo = ordenDeTrabajo;
    }


    @Override
    public String toString(){
        return tipo + " | " + nombre + " | $" + monto;
    }
    
}
