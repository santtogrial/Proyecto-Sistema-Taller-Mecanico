package com.taller.model;

import com.taller.enums.TipoItem;

public class ItemManoDeObra extends ItemTrabajo{

    public ItemManoDeObra(String nombre, double monto, OrdenDeTrabajo ordenDeTrabajo){
        super(nombre, monto, ordenDeTrabajo);
        super.setTipo(TipoItem.MANO_DE_OBRA);
    }
}
