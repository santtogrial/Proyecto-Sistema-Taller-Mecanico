package com.taller.service;

import com.taller.database.ItemTrabajoDAO;
import com.taller.model.ItemTrabajo;

public class ItemTrabajoService {

    private ItemTrabajoDAO itemTrabajoDAO;

    public ItemTrabajoService(ItemTrabajoDAO itemTrabajoDAO){
        this.itemTrabajoDAO = itemTrabajoDAO;
    }


    public void agregarItemTrabajo(ItemTrabajo itemTrabajo){
        itemTrabajoDAO.insertarItemTrabajo(itemTrabajo);
    }
    
}
