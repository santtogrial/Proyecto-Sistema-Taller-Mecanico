package com.taller.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseManager {
    private static final String URL = "jdbc:sqlite:taller.db";

    public static Connection conectar(){
        try {
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar a la Base de Datos");
        }
    }

    public void crearTablas(){
        var sqlCliente = "CREATE TABLE IF NOT EXISTS clientes (" + 
        " ID INTEGER PRIMARY KEY AUTOINCREMENT," + " Nombre text NOT NULL," + " CuitCuil text NOT NULL," + " Telefono text NOT NULL)";

        var sqlItemTrabajo = "CREATE TABLE IF NOT EXISTS itemTrabajo (" + 
        " ID INTEGER PRIMARY KEY AUTOINCREMENT," + " ID_OrdenDeTrabajo INTEGER NOT NULL," + " Nombre text NOT NULL," + " Monto REAL NOT NULL," + "Tipo text NOT NULL)";

        var sqlOrdenDeTrabajo = "CREATE TABLE IF NOT EXISTS ordenDeTrabajo (" + 
        "ID INTEGER PRIMARY KEY AUTOINCREMENT," + " Dominio_Vehiculo text NOT NULL," + " Kilometraje INTEGER NOT NULL," + " FechaRecepcion text NOT NULL," + " FechaFinalizacion text," + " Estado text NOT NULL)";

        var sqlPago = "CREATE TABLE IF NOT EXISTS pago (" + 
        " ID INTEGER PRIMARY KEY AUTOINCREMENT," + " ID_OrdenDeTrabajo INTEGER NOT NULL," + " Monto REAL NOT NULL," + " Fecha text NOT NULL," + " Tipo text NOT NULL," + " NumeroComprobante text," + " TipoTarjeta text," + " Ultimos4Digitos text)";

        var sqlVehiculo = "CREATE TABLE IF NOT EXISTS vehiculo (" + 
        " Dominio text PRIMARY KEY," + " ID_Cliente_Dueño INTEGER NOT NULL," + " Marca text NOT NULL," + " Modelo text NOT NULL," + " Año INTEGER NOT NULL," + " Kilometraje INTEGER)";
        try (var dec = conectar().createStatement()){
            dec.execute(sqlCliente);
            dec.execute(sqlItemTrabajo);
            dec.execute(sqlOrdenDeTrabajo);
            dec.execute(sqlPago);
            dec.execute(sqlVehiculo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
