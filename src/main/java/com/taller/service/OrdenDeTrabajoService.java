package com.taller.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller.database.OrdenDeTrabajoDAO;
import com.taller.enums.EstadoOrden;
import com.taller.model.ItemTrabajo;
import com.taller.model.OrdenDeTrabajo;
import com.taller.model.Pago;
import com.taller.model.Vehiculo;

@Service
public class OrdenDeTrabajoService {

    private OrdenDeTrabajoDAO ordenDeTrabajoDAO;
    private ItemTrabajoService itemTrabajoService;
    private PagoService pagoService;

    @Autowired
    public OrdenDeTrabajoService(OrdenDeTrabajoDAO ordenDeTrabajoDAO, ItemTrabajoService itemTrabajoService, PagoService pagoService){
        this.ordenDeTrabajoDAO = ordenDeTrabajoDAO;
        this.itemTrabajoService = itemTrabajoService;
        this.pagoService = pagoService;
    }

    public void agregarOrdenDeTrabajo(OrdenDeTrabajo ordenDeTrabajo){
            ordenDeTrabajoDAO.insertarOrdenDeTrabajo(ordenDeTrabajo);
    }

    public OrdenDeTrabajo buscarOrdenDeTrabajoPorId(int id){
        return ordenDeTrabajoDAO.obtenerOrdenDeTrabajoPorId(id);
    }

    public ArrayList<OrdenDeTrabajo> buscarOrdenesDeTrabajoPorVehiculo(Vehiculo vehiculo){
        return ordenDeTrabajoDAO.obtenerOrdenesDeTrabajoPorDominio(vehiculo.getDominio());
    }

    public ArrayList<OrdenDeTrabajo> buscarOrdenesDeTrabajoPorEstado(EstadoOrden estado){
        return ordenDeTrabajoDAO.obtenerOrdenesDeTrabajoPorEstado(estado);
    }

    public ArrayList<OrdenDeTrabajo> buscarOrdenesDeudoras(){
        ArrayList<OrdenDeTrabajo> ordenes = ordenDeTrabajoDAO.obtenerOrdenesDeTrabajoPorEstado(EstadoOrden.RETIRADO);
        ArrayList<OrdenDeTrabajo> ordenesDeudoras = new ArrayList<>();
        for (OrdenDeTrabajo o : ordenes){
            if (obtenerSaldoOrden(o) > 0){
                ordenesDeudoras.add(o);
            }
        }
        return ordenesDeudoras;
    }

    public double obtenerCargosOrden(OrdenDeTrabajo orden){
        double cargos = 0;
        ArrayList<ItemTrabajo> items = itemTrabajoService.buscarItemsTrabajoPorOrden(orden);
        for (ItemTrabajo i : items){
            cargos = cargos + i.getMonto();
        }
        return cargos;
    }

    public double obtenerAbonosOrden(OrdenDeTrabajo orden){
        double abonos = 0;
        ArrayList<Pago> pagos = pagoService.buscarPagosPorOrden(orden);
        for (Pago p : pagos){
            abonos = abonos + p.getMonto();
        }
        return abonos;
    }

    public double obtenerSaldoOrden(OrdenDeTrabajo orden){
        return (obtenerCargosOrden(orden) - obtenerAbonosOrden(orden));
    }

    public void actualizarEstadoOrden(OrdenDeTrabajo orden, EstadoOrden estado){
        ordenDeTrabajoDAO.actualizarEstadoOrden(orden.getId(), estado);
    }

    public void registrarPagoActualizarEstado(Pago pago){
        pagoService.agregarPago(pago);
        if (obtenerSaldoOrden(pago.getOrdenDeTrabajo()) == 0){
            actualizarEstadoOrden(pago.getOrdenDeTrabajo(), EstadoOrden.FINALIZADO);
        }
    }
    
}
