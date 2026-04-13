package com.taller.service;
import com.taller.model.*;
import java.util.ArrayList;
import java.util.List;

public class Taller {
    private String nombre;
    private List<Cliente> clientes;

    // Getters y Setters
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    // Constructor
    public Taller(String nombre){
        this.nombre = nombre;
        clientes = new ArrayList<Cliente>();
    }

    public void agregarCliente(Cliente cliente){
        clientes.add(cliente);
    }

    public Vehiculo buscarVehiculoPorDominio(String dominio){
        for (Cliente c : clientes){
            for (Vehiculo v : c.getVehiculos()){
                if (v.getDominio().equals(dominio)){
                    return v;
                }
            }
        }
        throw new IllegalArgumentException("El dominio ingresado no se encuentra en la base de datos: " + dominio);
    }

    public void crearOrdenDeTrabajo(String dominio, int kilometraje){
        Vehiculo vehiculo = buscarVehiculoPorDominio(dominio);
        OrdenDeTrabajo orden = new OrdenDeTrabajo(vehiculo, kilometraje);
        vehiculo.agregarOrden(orden);
    }

}
