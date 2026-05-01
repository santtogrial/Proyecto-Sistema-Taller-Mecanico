package com.taller.database;

import com.taller.model.ItemTrabajo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ItemTrabajoDAO {

    private OrdenDeTrabajoDAO ordenDeTrabajoDAO;

    public ItemTrabajoDAO(OrdenDeTrabajoDAO ordenDeTrabajoDAO){
        this.ordenDeTrabajoDAO = ordenDeTrabajoDAO;
    }

    public void insertarItemTrabajo(ItemTrabajo itemTrabajo){
        String sql = "INSERT INTO itemTrabajo(ID_OrdenDeTrabajo,Nombre,Monto,Tipo) VALUES(?,?,?,?)";
        try (PreparedStatement ps = DataBaseManager.conectar().prepareStatement(sql)){
            ps.setInt(1, itemTrabajo.getOrdenDeTrabajo().getId());
            ps.setString(2, itemTrabajo.getNombre());
            ps.setDouble(3, itemTrabajo.getMonto());
            ps.setString(4, itemTrabajo.getTipo());
            ps.executeUpdate();
            var keys = ps.getGeneratedKeys();
            if (keys.next()){
                itemTrabajo.setId(keys.getInt(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<ItemTrabajo> obtenerItemsTrabajo(){
        ArrayList<ItemTrabajo> items = new ArrayList<>();
        String sql = "SELECT * FROM itemTrabajo";
        try (ResultSet rs = DataBaseManager.conectar().createStatement().executeQuery(sql)){
            while (rs.next()){
                ItemTrabajo i = new ItemTrabajo(rs.getString("Nombre"),rs.getDouble("Monto"),ordenDeTrabajoDAO.obtenerOrdenDeTrabajoPorId(rs.getInt("ID_OrdenDeTrabajo")));
                i.setId(rs.getInt("ID"));
                i.setTipo(rs.getString("Tipo"));
                items.add(i);
            }
            return items;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}
