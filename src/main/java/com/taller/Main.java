package com.taller;

import com.taller.database.*;
import com.taller.model.*;

public class Main {
    public static void main(String[] args){
        DataBaseManager db = new DataBaseManager();
        db.crearTablas();

        ClienteDAO clienteDAO = new ClienteDAO();
        VehiculoDAO vehiculoDAO = new VehiculoDAO(clienteDAO);
        OrdenDeTrabajoDAO ordenDeTrabajoDAO = new OrdenDeTrabajoDAO(vehiculoDAO);
        ItemTrabajoDAO itemTrabajoDAO = new ItemTrabajoDAO(ordenDeTrabajoDAO);
        PagoDAO pagoDAO = new PagoDAO(ordenDeTrabajoDAO);


        
        Cliente cl1 = new Cliente("Joaquin Perez", "1234", "11551243");
        clienteDAO.insertarCliente(cl1);
        Vehiculo v1 = new Vehiculo("AAA111", "Dodge", "Journey 2.4", 2012, cl1);
        vehiculoDAO.insertarVehiculo(v1);
        OrdenDeTrabajo o1 = new OrdenDeTrabajo(v1, 100);
        ordenDeTrabajoDAO.insertarOrdenDeTrabajo(o1);
        ItemManoDeObra i1 = new ItemManoDeObra("Reemplazo Junta", 1000, o1);
        itemTrabajoDAO.insertarItemTrabajo(i1);
        Pago p1 = new PagoTarjeta(500, o1, "Debito Master", "1111");
        pagoDAO.insertarPago(p1);
        vehiculoDAO.actualizarKilometrajeVehiculo("AAA111", 15000);

        for (Cliente c : clienteDAO.obtenerClientes()){
        System.out.println(c.getId() + " " + c.getNombre() + " " + c.getCuitCuil());
        }

        

    }
    
}
