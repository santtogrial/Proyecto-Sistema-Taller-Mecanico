package com.taller.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller.database.PagoDAO;
import com.taller.model.OrdenDeTrabajo;
import com.taller.model.Pago;

@Service
public class PagoService {

    private PagoDAO pagoDAO;

    @Autowired
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
