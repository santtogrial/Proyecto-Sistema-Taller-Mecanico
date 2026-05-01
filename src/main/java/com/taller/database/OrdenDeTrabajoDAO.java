package com.taller.database;

import com.taller.enums.EstadoOrden;
import com.taller.model.OrdenDeTrabajo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrdenDeTrabajoDAO {

    private VehiculoDAO vehiculoDAO;

    public OrdenDeTrabajoDAO(VehiculoDAO vehiculoDAO){
        this.vehiculoDAO = vehiculoDAO;
    }

    public void insertarOrdenDeTrabajo(OrdenDeTrabajo ordenDeTrabajo){
        String sql = "INSERT INTO ordenDeTrabajo(Dominio_Vehiculo,Kilometraje,FechaRecepcion,Estado) VALUES(?,?,?,?)";
        try (PreparedStatement ps = DataBaseManager.conectar().prepareStatement(sql)){
            ps.setString(1, ordenDeTrabajo.getVehiculo().getDominio());
            ps.setInt(2, ordenDeTrabajo.getKilometraje());
            ps.setString(3, ordenDeTrabajo.getFechaRecepcion().toString());
            ps.setString(4, ordenDeTrabajo.getEstadoOrden().toString());
            ps.executeUpdate();
            var keys = ps.getGeneratedKeys();
            if (keys.next()){
                ordenDeTrabajo.setId(keys.getInt(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<OrdenDeTrabajo> obtenerOrdenesDeTrabajo(){
        ArrayList<OrdenDeTrabajo> ordenes = new ArrayList<>();
        String sql = "SELECT * FROM ordenDeTrabajo";
        try (ResultSet rs = DataBaseManager.conectar().createStatement().executeQuery(sql)){
            while(rs.next()){
                OrdenDeTrabajo o = new OrdenDeTrabajo(vehiculoDAO.obtenerVehiculoPorDominio(rs.getString("Dominio_Vehiculo")), rs.getInt("Kilometraje"));
                o.setId(rs.getInt("ID"));
                o.setFechaRecepcion(LocalDate.parse(rs.getString("FechaRecepcion")));
                o.setEstadoOrden(EstadoOrden.valueOf(rs.getString("Estado")));
                String fechaFin = rs.getString("FechaFinalizacion");
                if (fechaFin != null){
                    o.setFechaFinalizacion(LocalDate.parse(rs.getString("FechaFinalizacion")));
                }
                ordenes.add(o);
            }
            return ordenes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public OrdenDeTrabajo obtenerOrdenDeTrabajoPorId(int id){
        String sql = "SELECT * FROM ordenDeTrabajo WHERE ID = ?";
        try (PreparedStatement ps = DataBaseManager.conectar().prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            OrdenDeTrabajo o = null;
            if (rs.next()){
                o = new OrdenDeTrabajo(vehiculoDAO.obtenerVehiculoPorDominio(rs.getString("Dominio_Vehiculo")), rs.getInt("Kilometraje"));
                o.setId(id);
                o.setFechaRecepcion(LocalDate.parse(rs.getString("FechaRecepcion")));
                o.setEstadoOrden(EstadoOrden.valueOf(rs.getString("Estado")));
                String fechaFin = rs.getString("FechaFinalizacion");
                if (fechaFin != null){
                    o.setFechaFinalizacion(LocalDate.parse(rs.getString("FechaFinalizacion")));
                }
            }
            return o;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void actualizarEstadoOrden(int idOrden, EstadoOrden estado){
        String sql = "UPDATE ordenDeTrabajo SET Estado = ? WHERE ID = ?";
        try (PreparedStatement ps = DataBaseManager.conectar().prepareStatement(sql)){
            ps.setString(1, estado.toString());
            ps.setInt(2, idOrden);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void actualizarFechaFinalizacionOrden(int idOrden, LocalDate fecha){
        String sql = "UPDATE ordenDeTrabajo SET FechaFinalizacion = ? WHERE ID = ?";
        try (PreparedStatement ps = DataBaseManager.conectar().prepareStatement(sql)){
            ps.setString(1, fecha.toString());
            ps.setInt(2, idOrden);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
