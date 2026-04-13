package com.taller.model;

import java.time.LocalDate;

public class Pago {
    private double monto;
    private LocalDate fecha;
    private String tipo;

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public Pago(double monto){
        this.monto = monto;
        fecha = LocalDate.now();
    }
    
}
