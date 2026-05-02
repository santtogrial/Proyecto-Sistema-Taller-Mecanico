package com.taller.model;

import com.taller.enums.EstadoOrden;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int id;
    private String nombre;
    private String cuitcuil;
    private String telefono;
    private List<Vehiculo> vehiculos;

    // Getters y Setters
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getCuitCuil(){
        return cuitcuil;
    }
    public void setCuitCuil(String cuitcuil){
        this.cuitcuil = cuitcuil;
    }

    public String getTelefono(){
        return telefono;
    }
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    public List<Vehiculo> getVehiculos(){
        return vehiculos;
    }

    // Constructor
    public Cliente(String nombre, String cuitcuil, String telefono){
        this.nombre = nombre;
        this.cuitcuil = cuitcuil;
        this.telefono = telefono;
        vehiculos = new ArrayList<Vehiculo>();
    }

    @Override
    public String toString(){
        return "[" + id + "] " + nombre + " | CUIT/CUIL: " + cuitcuil + " | Tel: " + telefono;
    }


    public void agregarVehiculo(Vehiculo vehiculo){
        vehiculos.add(vehiculo);
    }

    public double calcularDeudaTotal(){
        double deuda = 0;
        for (Vehiculo v : vehiculos){
            for (OrdenDeTrabajo o : v.getHistorial()){
                if (o.getEstadoOrden() == EstadoOrden.RETIRADO){
                    deuda = deuda + o.calcularSaldo();
                }
            }
        }
        return deuda;
    }
    
}
