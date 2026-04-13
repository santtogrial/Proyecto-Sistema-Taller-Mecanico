package com.taller.model;

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
    //




    public void agregarVehiculo(Vehiculo vehiculo){
        // Armar un if que busque el vehiculo en la base de datos por dominio y si no lo encuentra manejar el error.
        vehiculos.add(vehiculo);
    }

    public double calcularDeudaTotal(){
        return 1;
    }
    
}
