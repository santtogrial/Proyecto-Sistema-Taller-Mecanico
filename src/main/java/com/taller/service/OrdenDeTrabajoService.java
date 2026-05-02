package com.taller.service;

import com.taller.database.OrdenDeTrabajoDAO;
import com.taller.model.OrdenDeTrabajo;

public class OrdenDeTrabajoService {

    private OrdenDeTrabajoDAO ordenDeTrabajoDAO;
    private VehiculoService vehiculoService;

    public OrdenDeTrabajoService(OrdenDeTrabajoDAO ordenDeTrabajoDAO, VehiculoService vehiculoService){
        this.ordenDeTrabajoDAO = ordenDeTrabajoDAO;
        this.vehiculoService = vehiculoService;
    }

    public void agregarOrdenDeTrabajo(OrdenDeTrabajo ordenDeTrabajo){
        if (vehiculoService.dominioExiste(ordenDeTrabajo.getVehiculo().getDominio())){
            ordenDeTrabajoDAO.insertarOrdenDeTrabajo(ordenDeTrabajo);
        }
        else{
            throw new IllegalArgumentException("El dominio ingresado no coincide con ningun dominio registrado en la base de datos: " + ordenDeTrabajo.getVehiculo().getDominio());
        }
    }
    
}
