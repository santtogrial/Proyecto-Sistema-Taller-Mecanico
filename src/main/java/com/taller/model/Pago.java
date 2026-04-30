package com.taller.model;

import java.time.LocalDate;

public class Pago {
    private int id;
    private double monto;
    private LocalDate fecha;
    private String tipo;
    private OrdenDeTrabajo ordenDeTrabajo;

    // Getters y Setters
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public double getMonto(){
        return monto;
    }
    public void setMonto(double monto){
        this.monto = monto;
    }

    public LocalDate getFecha(){
        return fecha;
    }
    public void setFecha(LocalDate fecha){
        this.fecha = fecha;
    }

    public String getTipo(){
        return tipo;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public OrdenDeTrabajo getOrdenDeTrabajo(){
        return ordenDeTrabajo;
    }

    public void setOrdenDeTrabajo(OrdenDeTrabajo ordenDeTrabajo){
        this.ordenDeTrabajo = ordenDeTrabajo;
    }

    public Pago(double monto, OrdenDeTrabajo ordenDeTrabajo){
        this.monto = monto;
        this.ordenDeTrabajo = ordenDeTrabajo;
        fecha = LocalDate.now();
    }
    
}
