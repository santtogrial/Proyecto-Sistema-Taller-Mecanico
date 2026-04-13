package com.taller.model;

public class PagoTarjeta extends Pago{
    private String tipoTarjeta;
    private String ultimos4Digitos;

    public PagoTarjeta(double monto, String tipoTarjeta, String ultimos4Digitos){
        super(monto);
        super.setTipo("Tarjeta");
        this.tipoTarjeta = tipoTarjeta;
        this.ultimos4Digitos = ultimos4Digitos;
    }
    
}
