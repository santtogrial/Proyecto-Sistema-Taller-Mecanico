package com.taller.model;

import java.util.ArrayList;
import java.util.List;

public class Vehiculo {
    private String dominio;
    private Cliente dueño;
    private String marca;
    private String modelo;
    private int año;
    private int kilometraje;
    private List<OrdenDeTrabajo> historial;

    // Getters y Setters
    public String getDominio(){
        return dominio;
    }
    public void setDominio(String dominio){
        if (!dominio.matches("[A-Z]{2}\\d{3}[A-Z]{2}|[A-Z]{3}\\d{3}")){
            throw new IllegalArgumentException("El dominio ingreso no coincide con el formato esperado: " + dominio);
        }
        this.dominio = dominio;
    }

    public Cliente getDueño(){
        return dueño;
    }
    public void setDueño(Cliente dueño){
        this.dueño = dueño;
    }

    public String getMarca(){
        return marca;
    }
    public void setMarca(String marca){
        this.marca = marca;
    }
    
    public String getModelo(){
        return modelo;
    }
    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    public int getAño(){
        return año;
    }
    public void setAño(int año){
        this.año = año;
    }

    public int getKilometraje(){
        return kilometraje;
    }
    public void setKilometraje(int kilometraje){
        this.kilometraje = kilometraje;
    }

    public List<OrdenDeTrabajo> getHistorial(){
        return historial;
    }
    public void agregarOrden(OrdenDeTrabajo orden){
        historial.add(orden);
    }

    // Constructor
    public Vehiculo(String dominio, String marca, String modelo, int año, Cliente dueño){
        setDominio(dominio);
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.dueño = dueño;
        historial = new ArrayList<OrdenDeTrabajo>();
    }

    @Override
    public String toString(){
        return dominio + " | " + marca + " " + modelo + " " + año + " | " + kilometraje + " km";
    }
    
    
    
}
