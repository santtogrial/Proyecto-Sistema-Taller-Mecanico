package com.taller.model;

public class PagoEfectivo extends Pago{
    
    public PagoEfectivo(double monto, OrdenDeTrabajo ordenDeTrabajo){
        super(monto, ordenDeTrabajo);
        super.setTipo("Efectivo");
    }
}
