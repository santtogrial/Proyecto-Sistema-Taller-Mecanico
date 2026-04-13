package com.taller.model;

public class PagoTransferencia extends Pago{
    private String numeroComprobante;
    
    public PagoTransferencia(double monto, String numeroComprobante){
        super(monto);
        super.setTipo("Transferencia");
        this.numeroComprobante = numeroComprobante;
    }
}
