package com.taller.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller.database.ItemTrabajoDAO;
import com.taller.model.ItemTrabajo;
import com.taller.model.OrdenDeTrabajo;

@Service
public class ItemTrabajoService {

    private ItemTrabajoDAO itemTrabajoDAO;

    @Autowired
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
