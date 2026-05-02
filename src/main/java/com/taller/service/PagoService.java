package com.taller.service;

import com.taller.database.PagoDAO;
import com.taller.model.OrdenDeTrabajo;
import com.taller.model.Pago;
import java.util.ArrayList;

public class PagoService {

    private PagoDAO pagoDAO;

    public PagoService(PagoDAO pagoDAO){
        this.pagoDAO = pagoDAO;
    }


    public void agregarPago(Pago pago){
        pagoDAO.insertarPago(pago);
    }

    public ArrayList<Pago> buscarPagosPorOrden(OrdenDeTrabajo ordenDeTrabajo){
        return pagoDAO.obtenerPagosPorOrdenId(ordenDeTrabajo.getId());
    }
    
}
