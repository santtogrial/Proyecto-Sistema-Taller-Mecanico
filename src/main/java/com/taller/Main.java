package com.taller;

import com.taller.database.*;
import com.taller.model.*;
import com.taller.service.*;

public class Main {
    public static void main(String[] args){
        DataBaseManager db = new DataBaseManager();
        db.crearTablas();

        ClienteDAO clienteDAO = new ClienteDAO();
        VehiculoDAO vehiculoDAO = new VehiculoDAO(clienteDAO);
        OrdenDeTrabajoDAO ordenDeTrabajoDAO = new OrdenDeTrabajoDAO(vehiculoDAO);
        ItemTrabajoDAO itemTrabajoDAO = new ItemTrabajoDAO(ordenDeTrabajoDAO);
        PagoDAO pagoDAO = new PagoDAO(ordenDeTrabajoDAO);

        ClienteService clienteService = new ClienteService(clienteDAO);
        VehiculoService vehiculoService = new VehiculoService(vehiculoDAO);
        OrdenDeTrabajoService ordenDeTrabajoService = new OrdenDeTrabajoService(ordenDeTrabajoDAO, vehiculoService);
        ItemTrabajoService itemTrabajoService = new ItemTrabajoService(itemTrabajoDAO);
        PagoService pagoService = new PagoService(pagoDAO);
        


        
        Cliente cl1 = new Cliente("Joaquin Perez", "1234", "11551243");
        clienteService.agregarCliente(cl1);
        Vehiculo v1 = new Vehiculo("AAA111", "Dodge", "Journey 2.4", 2012, cl1);
        vehiculoService.agregarVehiculo(v1);
        OrdenDeTrabajo o1 = new OrdenDeTrabajo(v1, 100);
        ordenDeTrabajoService.agregarOrdenDeTrabajo(o1);
        ItemManoDeObra i1 = new ItemManoDeObra("Reemplazo Junta", 1000, o1);
        itemTrabajoService.agregarItemTrabajo(i1);
        Pago p1 = new PagoTarjeta(500, o1, "Debito Master", "1111");
        pagoService.agregarPago(p1);
        vehiculoService.actualizarKilometrajeVehiculo("AAA111", 15000);

    }
    
}
