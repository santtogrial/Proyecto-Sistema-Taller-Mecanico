package com.taller.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller.database.VehiculoDAO;
import com.taller.model.Cliente;
import com.taller.model.OrdenDeTrabajo;
import com.taller.model.Vehiculo;

@Service
public class VehiculoService {

    private VehiculoDAO vehiculoDAO;
    private OrdenDeTrabajoService ordenDeTrabajoService;

    @Autowired
    public VehiculoService(VehiculoDAO vehiculoDAO, OrdenDeTrabajoService ordenDeTrabajoService){
        this.vehiculoDAO = vehiculoDAO;
        this.ordenDeTrabajoService = ordenDeTrabajoService;
    }



    public Vehiculo buscarVehiculoPorDominio(String dominio){
        return vehiculoDAO.obtenerVehiculoPorDominio(dominio);
    }

    public boolean dominioExiste(String dominio){
        return vehiculoDAO.obtenerVehiculoPorDominio(dominio) != null;
    }

    public void agregarVehiculo(Vehiculo vehiculo){
        if (dominioExiste(vehiculo.getDominio())){
            throw new IllegalArgumentException("El dominio ingresado ya esta registrado: " + vehiculo.getDominio());
        }
        else {
            vehiculoDAO.insertarVehiculo(vehiculo);
        }
    }

    public ArrayList<Vehiculo> buscarVehiculosPorCliente(Cliente cliente){
        return vehiculoDAO.obtenerVehiculosPorClienteId(cliente.getId());
    }

    public double obtenerDeudaVehiculo(Vehiculo vehiculo){
        ArrayList<OrdenDeTrabajo> ordenes = ordenDeTrabajoService.buscarOrdenesDeTrabajoPorVehiculo(vehiculo);
        double deuda = 0;
        for (OrdenDeTrabajo o : ordenes){
            deuda = deuda + ordenDeTrabajoService.obtenerSaldoOrden(o);
        }
        return deuda;
    }

    public void actualizarKilometrajeVehiculo(Vehiculo vehiculo, int kilometraje){
        vehiculoDAO.actualizarKilometrajeVehiculo(vehiculo.getDominio(), kilometraje);
    }

    public void actualizarDueñoVehiculo(Vehiculo vehiculo, Cliente cliente){
        vehiculoDAO.actualizarDueñoVehiculo(vehiculo.getDominio(), cliente.getId());
    }

    
    
}
