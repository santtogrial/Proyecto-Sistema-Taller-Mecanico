package com.taller.service;

import com.taller.database.VehiculoDAO;
import com.taller.model.Vehiculo;

public class VehiculoService {

    private VehiculoDAO vehiculoDAO;

    public VehiculoService(VehiculoDAO vehiculoDAO){
        this.vehiculoDAO = vehiculoDAO;
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

    public void actualizarKilometrajeVehiculo(String dominio, int kilometraje){
        vehiculoDAO.actualizarKilometrajeVehiculo(dominio, kilometraje);
    }

    
    
}
