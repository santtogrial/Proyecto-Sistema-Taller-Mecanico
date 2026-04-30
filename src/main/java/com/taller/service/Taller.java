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

    // Funciones

    public Cliente buscarClientePorId(int id){
        for (Cliente c : clientes){
            if (c.getId() == id){
                return c;
            }
        }
        throw new IllegalArgumentException("El ID ingresado no se encuentra en la base de datos: " + id);
    }

    public Cliente buscarClienteporCuitCuil(String cuitcuil){
        for (Cliente c : clientes){
            if (c.getCuitCuil().equals(cuitcuil)){
                return c;
            }
        }
        throw new IllegalArgumentException("El Cuit/Cuil ingresado no se encuentra en la base de datos: " + cuitcuil);
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

    public boolean cuitcuilExiste(String cuitcuil){
        try {
            buscarClienteporCuitCuil(cuitcuil);
            return true;
            }
        catch (IllegalArgumentException e) {
            return false;
        }
    }


    public boolean dominioExiste(String dominio){
        try {
            buscarVehiculoPorDominio(dominio);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public void agregarCliente(String nombre, String cuitcuil, String telefono){
        if (!cuitcuilExiste(cuitcuil)){
            clientes.add(new Cliente(nombre, cuitcuil,telefono));
        }
        else{
            throw new IllegalArgumentException("El Cuit/Cuil ingresado ya pertenece a un cliente en la base de datos: " + cuitcuil);
        }
    }

    // Modificar 
    public void agregarVehiculo(int idCliente, String dominio, String marca, String modelo, int año){
        Cliente cliente = buscarClientePorId(idCliente);
        if (!dominioExiste(dominio)){
            cliente.agregarVehiculo(new Vehiculo(dominio, marca, modelo, año, cliente));
        }
        else{
            throw new IllegalArgumentException("El dominio ingresado ya pertenece a un vehiculo en la base de datos: " + dominio);
        }
    }
    

    public void crearOrdenDeTrabajo(String dominio, int kilometraje){
        Vehiculo vehiculo = buscarVehiculoPorDominio(dominio);
        vehiculo.agregarOrden(new OrdenDeTrabajo(vehiculo, kilometraje));
    }

}
