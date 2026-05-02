package com.taller.model;

import com.taller.enums.TipoPago;

public class PagoTransferencia extends Pago{
    private String numeroComprobante;
    
    public PagoTransferencia(double monto, OrdenDeTrabajo ordenDeTrabajo, String numeroComprobante){
        super(monto, ordenDeTrabajo);
        super.setTipo(TipoPago.TRANSFERENCIA);
        this.numeroComprobante = numeroComprobante;
    }

    public String getNumeroComprobante(){
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante){
        this.numeroComprobante = numeroComprobante;
    }
}
