package com.taller.model;

public class PagoTarjeta extends Pago{
    private String tipoTarjeta;
    private String ultimos4Digitos;

    public PagoTarjeta(double monto, OrdenDeTrabajo ordenDeTrabajo, String tipoTarjeta, String ultimos4Digitos){
        super(monto, ordenDeTrabajo);
        super.setTipo("Tarjeta");
        this.tipoTarjeta = tipoTarjeta;
        this.ultimos4Digitos = ultimos4Digitos;
    }

    public String getTipoTarjeta(){
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta){
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getUltimos4Digitos(){
        return ultimos4Digitos;
    }

    public void setUltimos4Digitos(String ultimos4Digitos){
        this.ultimos4Digitos = ultimos4Digitos;
    }

    
}
