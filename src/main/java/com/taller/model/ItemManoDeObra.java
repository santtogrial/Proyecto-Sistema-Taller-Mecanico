package com.taller.model;

public class ItemManoDeObra extends ItemTrabajo{

    public ItemManoDeObra(String nombre, double monto){
        super(nombre, monto);
        super.setTipo("Mano de Obra");
    }
}
