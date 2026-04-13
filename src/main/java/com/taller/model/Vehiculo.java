package com.taller.model;

import java.util.List;

public class Vehiculo {
    private String dominio;
    private Cliente dueño;
    private String marca;
    private String modelo;
    private int año;
    private int kilometraje; // que se acutalize cuando una orden de reparacion de este vehiculo pasa a estar en listo para retirar.
    private List<OrdenDeTrabajo> historial;
    
}
