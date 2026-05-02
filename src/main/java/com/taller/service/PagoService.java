package com.taller.service;

import com.taller.database.PagoDAO;
import com.taller.model.Pago;

public class PagoService {

    private PagoDAO pagoDAO;

    public PagoService(PagoDAO pagoDAO){
        this.pagoDAO = pagoDAO;
    }


    public void agregarPago(Pago pago){
        pagoDAO.insertarPago(pago);
    }
    
}
