package com.taller.model;

public class ItemRepuesto extends ItemTrabajo{

    public ItemRepuesto(String nombre, double monto){
        super(nombre, monto);
        super.setTipo("Repuesto");
    }
}
