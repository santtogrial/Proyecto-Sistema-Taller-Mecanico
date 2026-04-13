package com.taller.model;

import java.time.LocalDate;

public class Pago {
    private double monto;
    private LocalDate fecha;
    private String tipo;

    // Getters y Setters
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

    public Pago(double monto){
        this.monto = monto;
        fecha = LocalDate.now();
    }
    
}
