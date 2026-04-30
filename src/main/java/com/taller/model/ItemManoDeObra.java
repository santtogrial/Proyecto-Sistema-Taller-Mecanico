package com.taller.model;

public class ItemManoDeObra extends ItemTrabajo{

    public ItemManoDeObra(String nombre, double monto, OrdenDeTrabajo ordenDeTrabajo){
        super(nombre, monto, ordenDeTrabajo);
        super.setTipo("Mano de Obra");
    }
}
