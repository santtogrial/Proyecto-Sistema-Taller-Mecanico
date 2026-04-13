package com.taller.model;

import java.util.List;

public class Cliente {
    private int id;
    private String nombre;
    private String cuitcuil;
    private String telefono;
    private List<Vehiculo> vehiculos;




    public void agregarVehiculo(Vehiculo vehiculo){
        // Armar un if que busque el vehiculo en la base de datos por dominio y si no lo encuentra manejar el error.
        vehiculos.add(vehiculo);
    }

    public double calcularDeudaTotal(){
        return 1;
    }
    
}
