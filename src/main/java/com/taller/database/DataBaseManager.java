package com.taller.database;

import com.taller.model.*;
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

    public void insertarCliente(Cliente cliente){
        String sql = "INSERT INTO clientes(Nombre,CuitCuil,Telefono) VALUES(?,?,?)";
        try (var dec = conectar().prepareStatement(sql)){
            dec.setString(1, cliente.getNombre());
            dec.setString(2, cliente.getCuitCuil());
            dec.setString(3, cliente.getTelefono());
            dec.executeUpdate();
            var keys = dec.getGeneratedKeys();
            if (keys.next()){
                cliente.setId(keys.getInt(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertarVehiculo(Vehiculo vehiculo){
        String sql = "INSERT INTO vehiculo(Dominio,ID_Cliente_Dueño,Marca,Modelo,Año) VALUES(?,?,?,?,?)";
        try (var dec = conectar().prepareStatement(sql)){
            dec.setString(1, vehiculo.getDominio());
            dec.setInt(2, vehiculo.getDueño().getId());
            dec.setString(3, vehiculo.getMarca());
            dec.setString(4, vehiculo.getModelo());
            dec.setInt(5, vehiculo.getAño());
            dec.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertarOrdenDeTrabajo(OrdenDeTrabajo ordenDeTrabajo){
        String sql = "INSERT INTO ordenDeTrabajo(Dominio_Vehiculo,Kilometraje,FechaRecepcion,Estado) VALUES(?,?,?,?)";
        try (var dec = conectar().prepareStatement(sql)){
            dec.setString(1, ordenDeTrabajo.getVehiculo().getDominio());
            dec.setInt(2, ordenDeTrabajo.getKilometraje());
            dec.setString(3, ordenDeTrabajo.getFechaRecepcion().toString());
            dec.setString(4, ordenDeTrabajo.getEstadoOrden().toString());
            dec.executeUpdate();
            var keys = dec.getGeneratedKeys();
            if (keys.next()){
                ordenDeTrabajo.setId(keys.getInt(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertarItemTrabajo(ItemTrabajo itemTrabajo){
        String sql = "INSERT INTO itemTrabajo(ID_OrdenDeTrabajo,Nombre,Monto,Tipo) VALUES(?,?,?,?)";
        try (var dec = conectar().prepareStatement(sql)){
            dec.setInt(1, itemTrabajo.getOrdenDeTrabajo().getId());
            dec.setString(2, itemTrabajo.getNombre());
            dec.setDouble(3, itemTrabajo.getMonto());
            dec.setString(4, itemTrabajo.getTipo());
            dec.executeUpdate();
            var keys = dec.getGeneratedKeys();
            if (keys.next()){
                itemTrabajo.setId(keys.getInt(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertarPago(Pago pago){
        String sql = "INSERT INTO pago(ID_OrdenDeTrabajo,Monto,Fecha,Tipo,NumeroComprobante,TipoTarjeta,Ultimos4Digitos) VALUES(?,?,?,?,?,?,?)";
        try (var dec = conectar().prepareStatement(sql)){
            dec.setInt(1, pago.getOrdenDeTrabajo().getId());
            dec.setDouble(2, pago.getMonto());
            dec.setString(3, pago.getFecha().toString());
            dec.setString(4, pago.getTipo());
            if (pago instanceof PagoTransferencia){
                dec.setString(5, ((PagoTransferencia) pago).getNumeroComprobante());
                dec.setNull(6, java.sql.Types.VARCHAR);
                dec.setNull(7, java.sql.Types.VARCHAR);
            } else if (pago instanceof PagoTarjeta){
                dec.setNull(5, java.sql.Types.VARCHAR);
                dec.setString(6, ((PagoTarjeta) pago).getTipoTarjeta());
                dec.setString(7, ((PagoTarjeta) pago).getUltimos4Digitos());
            } else {
                dec.setNull(5, java.sql.Types.VARCHAR);
                dec.setNull(6, java.sql.Types.VARCHAR);
                dec.setNull(7, java.sql.Types.VARCHAR);
            }
            dec.executeUpdate();
            var keys = dec.getGeneratedKeys();
            if (keys.next()){
                pago.setId(keys.getInt(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
