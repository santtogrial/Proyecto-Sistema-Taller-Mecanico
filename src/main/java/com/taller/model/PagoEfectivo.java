package com.taller.model;

import com.taller.enums.TipoPago;

public class PagoEfectivo extends Pago{
    
    public PagoEfectivo(double monto, OrdenDeTrabajo ordenDeTrabajo){
        super(monto, ordenDeTrabajo);
        super.setTipo(TipoPago.EFECTIVO);
    }
}
