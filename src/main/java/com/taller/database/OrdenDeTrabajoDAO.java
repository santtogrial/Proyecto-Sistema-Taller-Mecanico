package com.taller.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taller.enums.EstadoOrden;
import com.taller.model.OrdenDeTrabajo;
import com.taller.model.Vehiculo;

@Repository
public class OrdenDeTrabajoDAO {

    private VehiculoDAO vehiculoDAO;

    @Autowired
    public OrdenDeTrabajoDAO(VehiculoDAO vehiculoDAO){
        this.vehiculoDAO = vehiculoDAO;
    }

    public void insertarOrdenDeTrabajo(OrdenDeTrabajo ordenDeTrabajo){
        String sql = "INSERT INTO ordenDeTrabajo(Dominio_Vehiculo,Kilometraje,Descripcion,FechaRecepcion,Estado) VALUES(?,?,?,?,?)";
        try (Connection conn = DataBaseManager.conectar();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, ordenDeTrabajo.getVehiculo().getDominio());
            ps.setInt(2, ordenDeTrabajo.getKilometraje());
            ps.setString(3, ordenDeTrabajo.getDescripcion());
            ps.setString(4, ordenDeTrabajo.getFechaRecepcion().toString());
            ps.setString(5, ordenDeTrabajo.getEstadoOrden().toString());
            ps.executeUpdate();
            var keys = ps.getGeneratedKeys();
            if (keys.next()){
                ordenDeTrabajo.setId(keys.getInt(1));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar orden de trabajo", e);
        }
    }

    public ArrayList<OrdenDeTrabajo> obtenerOrdenesDeTrabajo(){
        ArrayList<OrdenDeTrabajo> ordenes = new ArrayList<>();
        String sql = "SELECT * FROM ordenDeTrabajo";
        try (Connection conn = DataBaseManager.conectar();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                OrdenDeTrabajo o = new OrdenDeTrabajo(vehiculoDAO.obtenerVehiculoPorDominio(rs.getString("Dominio_Vehiculo")), rs.getInt("Kilometraje"), rs.getString("Descripcion"));
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
            throw new RuntimeException("Error al obtener ordenes de trabajo", e);
        }
    }

    public OrdenDeTrabajo obtenerOrdenDeTrabajoPorId(int id){
        String sql = "SELECT * FROM ordenDeTrabajo WHERE ID = ?";
        try (Connection conn = DataBaseManager.conectar();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                OrdenDeTrabajo o = null;
                if (rs.next()){
                    o = new OrdenDeTrabajo(vehiculoDAO.obtenerVehiculoPorDominio(rs.getString("Dominio_Vehiculo")), rs.getInt("Kilometraje"), rs.getString("Descripcion"));
                    o.setId(id);
                    o.setFechaRecepcion(LocalDate.parse(rs.getString("FechaRecepcion")));
                    o.setEstadoOrden(EstadoOrden.valueOf(rs.getString("Estado")));
                    String fechaFin = rs.getString("FechaFinalizacion");
                    if (fechaFin != null){
                        o.setFechaFinalizacion(LocalDate.parse(rs.getString("FechaFinalizacion")));
                    }
                }
                return o;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener orden de trabajo", e);
        }
    }

    public ArrayList<OrdenDeTrabajo> obtenerOrdenesDeTrabajoPorDominio(String dominio){
        ArrayList<OrdenDeTrabajo> ordenes = new ArrayList<>();
        String sql = "SELECT * FROM ordenDeTrabajo WHERE Dominio_Vehiculo = ?";
        try (Connection conn = DataBaseManager.conectar();
        PreparedStatement ps = conn.prepareStatement(sql)){
            Vehiculo vehiculo = vehiculoDAO.obtenerVehiculoPorDominio(dominio);
            ps.setString(1, dominio);
            try (ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    OrdenDeTrabajo o = new OrdenDeTrabajo(vehiculo, rs.getInt("Kilometraje"), rs.getString("Descripcion"));
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
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener ordenes de trabajo", e);
        }
    }

    public ArrayList<OrdenDeTrabajo> obtenerOrdenesDeTrabajoPorEstado(EstadoOrden estado){
        ArrayList<OrdenDeTrabajo> ordenes = new ArrayList<>();
        String sql = "SELECT * FROM ordenDeTrabajo WHERE Estado = ?";
        try (Connection conn = DataBaseManager.conectar();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, estado.toString());
            try (ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    OrdenDeTrabajo o = new OrdenDeTrabajo(vehiculoDAO.obtenerVehiculoPorDominio(rs.getString("Dominio_Vehiculo")), rs.getInt("Kilometraje"), rs.getString("Descripcion"));
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
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener ordenes de trabajo por estado", e);
        }
    }
    

    public void actualizarEstadoOrden(int idOrden, EstadoOrden estado){
        String sql = "UPDATE ordenDeTrabajo SET Estado = ? WHERE ID = ?";
        try (Connection conn = DataBaseManager.conectar();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, estado.toString());
            ps.setInt(2, idOrden);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar estado de orden de trabajo", e);
        }
    }

    public void actualizarFechaFinalizacionOrden(int idOrden, LocalDate fecha){
        String sql = "UPDATE ordenDeTrabajo SET FechaFinalizacion = ? WHERE ID = ?";
        try (Connection conn = DataBaseManager.conectar();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, fecha.toString());
            ps.setInt(2, idOrden);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar fecha de finalizacion de orden de trabajo", e);
        }
    }
    
}
