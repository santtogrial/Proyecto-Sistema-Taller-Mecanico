package com.taller.database;

import com.taller.enums.TipoItem;
import com.taller.model.ItemTrabajo;
import com.taller.model.OrdenDeTrabajo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemTrabajoDAO {

    private OrdenDeTrabajoDAO ordenDeTrabajoDAO;

    public ItemTrabajoDAO(OrdenDeTrabajoDAO ordenDeTrabajoDAO){
        this.ordenDeTrabajoDAO = ordenDeTrabajoDAO;
    }

    public void insertarItemTrabajo(ItemTrabajo itemTrabajo){
        String sql = "INSERT INTO itemTrabajo(ID_OrdenDeTrabajo,Nombre,Monto,Tipo) VALUES(?,?,?,?)";
        try (Connection conn = DataBaseManager.conectar();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, itemTrabajo.getOrdenDeTrabajo().getId());
            ps.setString(2, itemTrabajo.getNombre());
            ps.setDouble(3, itemTrabajo.getMonto());
            ps.setString(4, itemTrabajo.getTipo().toString());
            ps.executeUpdate();
            var keys = ps.getGeneratedKeys();
            if (keys.next()){
                itemTrabajo.setId(keys.getInt(1));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar item de trabajo", e);
        }
    }

    public ArrayList<ItemTrabajo> obtenerItemsTrabajo(){
        ArrayList<ItemTrabajo> items = new ArrayList<>();
        String sql = "SELECT * FROM itemTrabajo";
        try (Connection conn = DataBaseManager.conectar();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql)){
            while (rs.next()){
                ItemTrabajo i = new ItemTrabajo(rs.getString("Nombre"),rs.getDouble("Monto"),ordenDeTrabajoDAO.obtenerOrdenDeTrabajoPorId(rs.getInt("ID_OrdenDeTrabajo")));
                i.setId(rs.getInt("ID"));
                i.setTipo(TipoItem.valueOf(rs.getString("Tipo")));
                items.add(i);
            }
            return items;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener items de trabajo", e);
        }
    }

    public ArrayList<ItemTrabajo> obtenerItemsTrabajoPorOrden(int OrdenId){
        ArrayList<ItemTrabajo> items = new ArrayList<>();
        String sql = "SELECT * FROM itemTrabajo WHERE ID_OrdenDeTrabajo = ?";
        OrdenDeTrabajo o = ordenDeTrabajoDAO.obtenerOrdenDeTrabajoPorId(OrdenId);
        try (Connection conn = DataBaseManager.conectar();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, OrdenId);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    ItemTrabajo i = new ItemTrabajo(rs.getString("Nombre"),rs.getDouble("Monto"), o);
                    i.setId(rs.getInt("ID"));
                    i.setTipo(TipoItem.valueOf(rs.getString("Tipo")));
                    items.add(i);
                }
                return items;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener item de trabajo", e);
        }
    }
    
}
