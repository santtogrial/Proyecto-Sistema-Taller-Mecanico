package com.taller.service;

import com.taller.database.ItemTrabajoDAO;
import com.taller.model.ItemTrabajo;
import com.taller.model.OrdenDeTrabajo;
import java.util.ArrayList;

public class ItemTrabajoService {

    private ItemTrabajoDAO itemTrabajoDAO;

    public ItemTrabajoService(ItemTrabajoDAO itemTrabajoDAO){
        this.itemTrabajoDAO = itemTrabajoDAO;
    }


    public void agregarItemTrabajo(ItemTrabajo itemTrabajo){
        itemTrabajoDAO.insertarItemTrabajo(itemTrabajo);
    }
    
    public ArrayList<ItemTrabajo> buscarItemsTrabajoPorOrden(OrdenDeTrabajo orden){
        return itemTrabajoDAO.obtenerItemsTrabajoPorOrden(orden.getId());
    }
}
