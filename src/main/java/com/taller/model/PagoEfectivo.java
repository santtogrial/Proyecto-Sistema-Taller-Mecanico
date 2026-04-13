package com.taller.model;

public class PagoEfectivo extends Pago{
    
    public PagoEfectivo(double monto){
        super(monto);
        super.setTipo("Efectivo");
    }
}
