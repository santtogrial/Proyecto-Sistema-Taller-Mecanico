package com.taller;

import com.taller.database.DataBaseManager;
import com.taller.model.*;

public class Main {
    public static void main(String[] args){
        DataBaseManager db = new DataBaseManager();
        db.crearTablas();

        Cliente cl1 = new Cliente("Joaquin Perez", "1234", "11551243");
        db.insertarCliente(cl1);
        Vehiculo v1 = new Vehiculo("AAA111", "Dodge", "Journey 2.4", 2012, cl1);
        db.insertarVehiculo(v1);
        OrdenDeTrabajo o1 = new OrdenDeTrabajo(v1, 100);
        db.insertarOrdenDeTrabajo(o1);
        ItemManoDeObra i1 = new ItemManoDeObra("Reemplazo Junta", 1000, o1);
        db.insertarItemTrabajo(i1);
        Pago p1 = new PagoTarjeta(500, o1, "Debito Master", "1111");
        db.insertarPago(p1);


    }
    
}
