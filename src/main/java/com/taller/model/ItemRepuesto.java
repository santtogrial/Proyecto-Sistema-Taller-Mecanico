package com.taller.model;

import com.taller.enums.TipoItem;

public class ItemRepuesto extends ItemTrabajo{

    public ItemRepuesto(String nombre, double monto, OrdenDeTrabajo ordenDeTrabajo){
        super(nombre, monto, ordenDeTrabajo);
        super.setTipo(TipoItem.REPUESTO);
    }
}
